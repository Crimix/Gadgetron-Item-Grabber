package com.black_dog20.itemgrabber.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.black_dog20.itemgrabber.init.ModItems;
import com.black_dog20.itemgrabber.reference.Reference;

public class CreativeTabGTIG{

	public static final CreativeTabs TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.magnetT1);
		}

		@Override
		public String getTranslatedTabLabel() {
			return Reference.MOD_NAME;
		}
	};

}