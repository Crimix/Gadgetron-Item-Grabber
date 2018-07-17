package com.black_dog20.itemgrabber.item;

import java.util.List;

import com.black_dog20.gadgetron.api.ISpecialEquipment;
import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.itemgrabber.client.settings.Keybindings;
import com.black_dog20.itemgrabber.reference.Constants;
import com.black_dog20.itemgrabber.reference.NBTTags;
import com.black_dog20.itemgrabber.utility.MagnetHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ItemMagnet extends ItemBase implements ISpecialEquipment{

	private int tier;
	
	public ItemMagnet(String name, int tier){
		super(name);
		this.tier = tier;
		this.setMaxStackSize(1);
		MinecraftForge.EVENT_BUS.register(this);
		
		this.addPropertyOverride(new ResourceLocation("state"), new IItemPropertyGetter() {
			
			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
				if(entityIn != null){
					IBeltHandler mh = entityIn.getCapability(BeltHandler.CAP, null);
					if(mh != null && !mh.getHasMagnetOn()){
						return 0;
					}
				}
				else if(stack.hasTagCompound()){
					NBTTagCompound nbt = stack.getTagCompound();
					if(nbt.hasKey(NBTTags.MAGNET_ACTIVE)) {
						if(!nbt.getBoolean(NBTTags.MAGNET_ACTIVE))
							return 0;
					}
				}
				
				return -1;
			}
		});
	}
	
	public int getTier(){
		return tier;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		TextComponentTranslation press = new TextComponentTranslation("tooltip.gadgetronig:press");
		
		TextComponentTranslation activate = new TextComponentTranslation("tooltip.gadgetronig:activate");
		TextComponentTranslation range = new TextComponentTranslation("tooltip.gadgetronig:range");
		TextComponentTranslation blocks = new TextComponentTranslation("tooltip.gadgetronig:blocks");
		blocks.getStyle().setColor(TextFormatting.WHITE);
		TextComponentTranslation active = new TextComponentTranslation("tooltip.gadgetronig:active");
		TextComponentTranslation sneak = new TextComponentTranslation("tooltip.gadgetronig:sneak");
		TextComponentTranslation on = new TextComponentTranslation("tooltip.gadgetronig:on");
		on.getStyle().setColor(TextFormatting.GREEN);
		TextComponentTranslation off = new TextComponentTranslation("tooltip.gadgetronig:off");
		off.getStyle().setColor(TextFormatting.RED);
		
		tooltip.add(press.getFormattedText() + "�9" + Keybindings.ON.getDisplayName() + "�r �7" + activate.getFormattedText()+ "�r");
		tooltip.add("");
		
		IBeltHandler mh = playerIn.getCapability(BeltHandler.CAP, null);
		if(mh != null){
			TextComponentTranslation activeState = mh.getHasMagnetOn() ? on : off;
			TextComponentTranslation sneakState = mh.getSneakDeactivate() ? on : off;
			tooltip.add(active.getFormattedText() + ": " + activeState.getFormattedText());
			tooltip.add(sneak.getFormattedText() + ": " + sneakState.getFormattedText());
		}
		tooltip.add(range.getFormattedText() + ": " + MagnetHelper.getRange(tier) + " " + blocks.getFormattedText());

	}

	@Override
	public SpecialEquipmentType getype() {
		return SpecialEquipmentType.MAGNET;
	}	
	
	
	@SubscribeEvent
	public void onTossItem(ItemTossEvent event){
		if(event.getPlayer().world.isRemote) return;
		event.getEntityItem().getEntityData().setInteger(NBTTags.PICKUP_IN, Constants.PICK_UP_DELAY);
		event.getEntityItem().getEntityData().setString(NBTTags.DROPPED_BY, event.getPlayer().getName());
	}
	
	@Override
	public void onWornTick(ItemStack stack, EntityPlayer player) {
		if(stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		NBTTagCompound nbt = stack.getTagCompound();
		IBeltHandler bh = BeltHandler.instanceFor(player);
		if(bh != null)
			nbt.setBoolean(NBTTags.MAGNET_ACTIVE, bh.getHasMagnetOn());
		
	}
	
	@SubscribeEvent
	public void onEnterBlockedArray(LivingUpdateEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if(player.world.isRemote) return;
			NBTTagCompound nbt = player.getEntityData();
			IBeltHandler mh = player.getCapability(BeltHandler.CAP, null);
			if(mh != null && (MagnetHelper.hasMagnetInInventory(player))) {
				MagnetHelper.checkIfMagnetShouldBeTempOff(player);
				if(nbt.getInteger(NBTTags.BLOCKED) <= 0){
					nbt.removeTag(NBTTags.BLOCKED);
					if(mh != null) {
						mh.setTempOff(false);
					}	
				}else{
					nbt.setInteger(NBTTags.BLOCKED, (nbt.getInteger(NBTTags.BLOCKED))-1);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onMagnetTryToAttractItem(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			EntityPlayer player = event.player;
			if(player.world.isRemote) return;
			IBeltHandler mh = player.getCapability(BeltHandler.CAP, null);
			int range = 0;
			if (mh!= null && mh.getHasMagnetOn() && !(mh.getSneakDeactivate() && player.isSneaking()) && !mh.getTempOff()) {	
				range = MagnetHelper.getRange(player);
				double playerX = player.posX;
				double playerY = (player.posY + 0.75);
				double playerZ = player.posZ;
				if(range != 0){
					List<EntityItem> floatingItems = player.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, 
									new AxisAlignedBB(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range), MagnetHelper.floatingItemsToPickUp(player));
					int pulledItems = 0;
						for (EntityItem entityItem : floatingItems) {
							if(pulledItems > 200) break;
							MagnetHelper.setEntityMotion(entityItem, playerX, playerY, playerZ, 0.45F);
							entityItem.getEntityData().setString(NBTTags.TRACKED_BY, player.getName());
							pulledItems++;
						}
					List<EntityXPOrb> floatingXP = player.getEntityWorld().getEntitiesWithinAABB(EntityXPOrb.class, 
							new AxisAlignedBB(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range));
						for (EntityXPOrb entityXP : floatingXP) {
							MagnetHelper.setEntityMotion(entityXP, playerX, playerY, playerZ, 0.45F);
						}
				}
			}
		}
	}
}
