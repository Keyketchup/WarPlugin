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
					Bukkit.broadcastMessage(ChatColor.YELLOW + "전쟁을 멈춤니다");
				}
				else if (ev.getCurrentItem().getType() == Material.JUKEBOX) {
					WarSchedular.isPaused = false;
					Bukkit.broadcastMessage(ChatColor.YELLOW + "전쟁을 재개합니다");
				}
				else if (ev.getCurrentItem().getType() == Material.BLAZE_ROD) {
					if(WarSchedular.warStatus == WarStatus.NONE) {
						ev.getWhoClicked().sendMessage(ChatColor.YELLOW + "진행중인 전쟁이 없습니다, 나중에 다시 시도해 주십시오");
					} else if(WarSchedular.isPaused) {
						ev.getWhoClicked().sendMessage(ChatColor.YELLOW + "전쟁이 중단되어 있습니다, 전쟁을 계속하기 위해서는 '전쟁 계속하기'를 눌러주세요");
					} else {
						ev.getWhoClicked().sendMessage(ChatColor.YELLOW + "다음 이벤트까지 " + WarSchedular.timeLeft/60 + "분 " + WarSchedular.timeLeft%60 + "초가 남았습니다");
					}
				}
				ev.getView().close();
			}
		}
		
	}
	
}
