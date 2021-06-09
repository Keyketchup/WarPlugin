package me.keycatchupwhy.warplugin.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.keycatchupwhy.warplugin.schedular.WarSchedular;
import me.keycatchupwhy.warplugin.schedular.WarStatus;

public class GuiManager {
	
	public static void WarUI(Player player) {
		
		Inventory inventory = Bukkit.createInventory(player, 9, "War Manager");
		
		ItemStack itemstack1 = new ItemStack(Material.BIRCH_DOOR, 1);
		ItemMeta meta1 = itemstack1.getItemMeta();
		meta1.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		meta1.setDisplayName("���� �����ϱ�");
		itemstack1.setItemMeta(meta1);
		
		inventory.setItem(2, itemstack1);
		
		ItemStack itemstack3 = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta meta3 = itemstack3.getItemMeta();
		meta3.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		meta3.setDisplayName("������� ���� �ð�");
		itemstack3.setItemMeta(meta3);
		
		inventory.setItem(3, itemstack3);
		
		ItemStack itemstack2 = null;
		
		if(WarSchedular.warStatus == WarStatus.NONE) {
			itemstack2 = new ItemStack(Material.DIRT, 1);
			ItemMeta meta2 = itemstack2.getItemMeta();
			meta2.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			meta2.setDisplayName("(������ �����ϼž� ���� �� �ֽ��ϴ�)");
			itemstack2.setItemMeta(meta2);
		} else {
			if(WarSchedular.isPaused == true) {
				itemstack2 = new ItemStack(Material.JUKEBOX, 1);
				ItemMeta meta2 = itemstack2.getItemMeta();
				meta2.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				meta2.setDisplayName("���� ����ϱ�");
				itemstack2.setItemMeta(meta2);
			}
			else {
				itemstack2 = new ItemStack(Material.BARRIER, 1);
				ItemMeta meta2 = itemstack2.getItemMeta();
				meta2.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				meta2.setDisplayName("���� ���߱�");
				itemstack2.setItemMeta(meta2);
			}
		}
		
		inventory.setItem(4, itemstack2);
		
		ItemStack itemstack4 = new ItemStack(Material.PAPER, 1);
		ItemMeta meta4 = itemstack4.getItemMeta();
		meta4.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		meta4.setDisplayName("ȸ�� �ð����� ��ŵ�ϱ�");
		itemstack4.setItemMeta(meta4);
		
		inventory.setItem(6, itemstack4);
		
		ItemStack itemstack5 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta meta5 = itemstack5.getItemMeta();
		meta5.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		meta5.setDisplayName("���� �ð����� ��ŵ�ϱ�");
		itemstack5.setItemMeta(meta5);
		
		inventory.setItem(7, itemstack5);
		
		player.openInventory(inventory);
		
	}
	
}
