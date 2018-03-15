package com.black_dog20.itemgrabber.utility;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class InventoryHelper {

	public static int findItem(Item item, InventoryPlayer iplayer) {
		int res = 0;
		for (int i = 0; i < 36; i++) {
			ItemStack testItem = iplayer.getStackInSlot(i);
			if (testItem != null && testItem.getItem() == item) {
				res += iplayer.getStackInSlot(i).getCount();
			}
		}

		return res;
	}

	public static int getArmorPosition(ItemArmor item) {
		int armor = 0;
		switch (item.armorType.getIndex()) {
		case 0:
			armor = 3;
			break;
		case 1:
			armor = 2;
			break;
		case 2:
			armor = 1;
			break;
		case 3:
			armor = 0;
			break;
		}
		return armor;
	}

}
