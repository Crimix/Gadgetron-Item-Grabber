package com.black_dog20.itemgrabber.init;

import com.black_dog20.itemgrabber.item.ItemBelt;
import com.black_dog20.itemgrabber.item.ItemMagnet;
import com.black_dog20.itemgrabber.item.ItemBase;
import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	
	public static ItemBelt belt;
	public static ItemMagnet magnetT1;
	
	public static void init() {
		belt = new ItemBelt();
		magnetT1 = new ItemMagnet("magnetT1",5,0.02);
	}
	
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	belt.initModel();
    	magnetT1.initModel();
    }

}
