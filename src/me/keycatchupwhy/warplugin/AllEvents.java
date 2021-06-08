package me.keycatchupwhy.warplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.keycatchupwhy.warplugin.guis.GuiManager;
import me.keycatchupwhy.warplugin.schedular.WarSchedular;
import me.keycatchupwhy.warplugin.schedular.WarStatus;

public class AllEvents implements Listener {
		
	GuiManager guiManager = new GuiManager();
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent ev) {
		
		//WarUI
		if(ev.getView().getTitle() == "War Manager") {
			ev.setCancelled(true);
			if(ev.getCurrentItem().containsEnchantment(Enchantment.ARROW_INFINITE)) {
				if(ev.getCurrentItem().getType() == Material.BIRCH_DOOR) {
					//Start War
					WarSchedular.DeclareWar();
					WarSchedular.isPaused = false;
				}
				else if (ev.getCurrentItem().getType() == Material.BARRIER) {
					WarSchedular.isPaused = true;
					Bukkit.broadcastMessage(ChatColor.YELLOW + "������ ����ϴ�");
				}
				else if (ev.getCurrentItem().getType() == Material.JUKEBOX) {
					WarSchedular.isPaused = false;
					Bukkit.broadcastMessage(ChatColor.YELLOW + "������ �簳�մϴ�");
				}
				else if (ev.getCurrentItem().getType() == Material.BLAZE_ROD) {
					if(WarSchedular.warStatus == WarStatus.NONE) {
						ev.getWhoClicked().sendMessage(ChatColor.YELLOW + "�������� ������ �����ϴ�, ���߿� �ٽ� �õ��� �ֽʽÿ�");
					} else if(WarSchedular.isPaused) {
						ev.getWhoClicked().sendMessage(ChatColor.YELLOW + "������ �ߴܵǾ� �ֽ��ϴ�, ������ ����ϱ� ���ؼ��� '���� ����ϱ�'�� �����ּ���");
					} else {
						ev.getWhoClicked().sendMessage(ChatColor.YELLOW + "���� �̺�Ʈ���� " + WarSchedular.timeLeft/60 + "�� " + WarSchedular.timeLeft%60 + "�ʰ� ���ҽ��ϴ�");
					}
				}
				ev.getView().close();
			}
		}
		
	}
	
}
