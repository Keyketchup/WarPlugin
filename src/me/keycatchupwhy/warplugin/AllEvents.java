package me.keycatchupwhy.warplugin;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.keycatchupwhy.warplugin.guis.GuiManager;
import me.keycatchupwhy.warplugin.schedular.WarSchedular;

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
				}
				else if (ev.getCurrentItem().getType() == Material.JUKEBOX) {
					WarSchedular.isPaused = false;
				}
			}
		}
		
	}
	
}
