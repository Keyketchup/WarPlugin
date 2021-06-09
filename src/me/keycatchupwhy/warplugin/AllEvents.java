package me.keycatchupwhy.warplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.onarandombox.MultiverseCore.MultiverseCore;

import me.keycatchupwhy.warplugin.guis.GuiManager;
import me.keycatchupwhy.warplugin.schedular.WarSchedular;
import me.keycatchupwhy.warplugin.schedular.WarStatus;
import me.keycatchupwhy.warplugin.teams.AllTeams;
import me.keycatchupwhy.warplugin.teams.Team;

public class AllEvents implements Listener {
	
	MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
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
				else if (ev.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
					WarSchedular.StartWar();
					Bukkit.broadcastMessage(ChatColor.YELLOW + "(������ ��� �����Ͽ����ϴ�, �߸� �����̴ٸ� �绡�� ������ �����ּ���");
				}
				else if (ev.getCurrentItem().getType() == Material.PAPER) {
					WarSchedular.StartCommunicate();
					Bukkit.broadcastMessage(ChatColor.YELLOW + "(ȸ�� �ð��� ��� �����Ͽ����ϴ�, �߸� �����̴ٸ� �绡�� ������ �����ּ���");
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
	
	@EventHandler
	public void playerHitPlayerEvent(EntityDamageByEntityEvent ev) {
		Entity damager = ev.getDamager(); 
		Entity damaged = ev.getEntity();
		if (damager instanceof Player) {
			Player damagerPlayer = (Player) damager;
			Player damagedPlayer = (Player) damaged;
			
			if(WarSchedular.warStatus == WarStatus.WAR) {
				ev.setCancelled(true);
			}
			else if(AllTeams.FindTeam(damagedPlayer) == AllTeams.FindTeam(damagerPlayer)) {
				ev.setCancelled(true);
			}
			else {
				damagedPlayer.sendMessage(ChatColor.BLUE + damagedPlayer.getDisplayName() + " ���� ü�¿��� " + ev.getDamage() + " �� ��ҽ��ϴ�");
			}
			
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent ev) {
		
		Player player = ev.getEntity();
		if(player.getGameMode() != GameMode.CREATIVE) {
			if(WarSchedular.warStatus == WarStatus.WAR) {
				Team team = AllTeams.FindTeam(player);
				if(team != null) {
					if(player.getKiller() != null) {
						AllTeams.SendTeamMessage(team, player.getDisplayName() + "���� " + player.getKiller().getDisplayName() + "�Կ��� �׾����ϴ�");
						Team killerTeam = AllTeams.FindTeam(player);
						if(killerTeam != null) {
							AllTeams.SendTeamMessage(killerTeam, player.getKiller().getDisplayName() + "���� " + player.getDisplayName() + "���� �׾����ϴ�");
							killerTeam.teamKills++;
						}
					} else {
						AllTeams.SendTeamMessage(team, player.getDisplayName() + "���� �׾����ϴ�");
					}
					team.teamDeaths++;
				}
			}
		}
		
	}
	
	/*
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent ev) {
		
		if(ev.getItemDrop().getItemStack().containsEnchantment(Enchantment.ARROW_INFINITE)) {
			
			if(ev.getItemDrop().getItemStack().getType() == Material.NETHER_STAR) {
				
				if(AllTeams.FindOwnersTeam(ev.getPlayer()) != null) {
					
					core.getMVWorldManager().getMVWorld(ev.getPlayer().getWorld());
					
					Team team = AllTeams.FindOwnersTeam(ev.getPlayer());
					Location loc = ev.getItemDrop().getLocation();
					Chunk chunk = loc.getChunk();
					
					if(team.chunks.size() >= team.maxChunks) {
						ev.getPlayer().sendMessage("���� ���� ûũ�� �ѵ��� �Ѿ���ϴ�");					
					} else {
						team.chunks.add(chunk);
					}
					
				} else {
					
					if(AllTeams.FindTeam(ev.getPlayer()) != null) {
						ev.getPlayer().sendMessage("�����常 �� ����� �� �� �ֽ��ϴ�");
					} else {
						ev.getPlayer().sendMessage("�Ҽӵ� ������ �����ϴ�");
					}
					
				}
				
			}
			
		}
		
	}
	*/
	
}
