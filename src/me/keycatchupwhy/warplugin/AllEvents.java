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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.onarandombox.MultiverseCore.api.MultiverseWorld;

import me.keycatchupwhy.warplugin.castles.Castle;
import me.keycatchupwhy.warplugin.guis.GuiManager;
import me.keycatchupwhy.warplugin.schedular.WarSchedular;
import me.keycatchupwhy.warplugin.schedular.WarStatus;
import me.keycatchupwhy.warplugin.teams.AllTeams;
import me.keycatchupwhy.warplugin.teams.Team;

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
				else if (ev.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
					WarSchedular.StartWar();
					ev.getWhoClicked().sendMessage(ChatColor.YELLOW + "전쟁을 즉시 시작하였습니다, 잘못 누르셨다면 재빨리 전쟁을 멈줘주세요");
				}
				else if (ev.getCurrentItem().getType() == Material.PAPER) {
					WarSchedular.StartCommunicate();
					ev.getWhoClicked().sendMessage(ChatColor.YELLOW + "회의 시간을 즉시 시작하였습니다, 잘못 누르셨다면 재빨리 전쟁을 멈줘주세요");
				}
				ev.getView().close();
			}
		}
		
		//CastleUI
		if(ev.getView().getTitle() == "Castle Manager") {
			ev.setCancelled(true);
			if(ev.getCurrentItem().containsEnchantment(Enchantment.ARROW_INFINITE)) {
				if(ev.getCurrentItem().getType() == Material.ANVIL) {
					Team team = AllTeams.FindTeam((Player)ev.getWhoClicked());
					if(!team.hasCastle) {
						MultiverseWorld mvWorld = Main.core.getMVWorldManager().getMVWorld(ev.getWhoClicked().getWorld());
						Castle castle = AllWorlds.getCastle(mvWorld);
						castle.team = team;
						team.hasCastle = true;
						ev.getWhoClicked().sendMessage("성을 성공적으로 차지하였습니다");
					} else {
						ev.getWhoClicked().sendMessage("이미 성이 있습니다");
					}
				}
				if(ev.getCurrentItem().getType() == Material.BARRIER) {
					Team team = AllTeams.FindTeam((Player)ev.getWhoClicked());
					if(!team.hasCastle) {
						MultiverseWorld mvWorld = Main.core.getMVWorldManager().getMVWorld(ev.getWhoClicked().getWorld());
						Castle castle = AllWorlds.getCastle(mvWorld);
						if(castle != null) {
							if(castle.team == team) {
								castle.team = null;
								ev.getWhoClicked().sendMessage("성의 소유권을 성공적으로 박탈하였습니다");
							}
						}
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
				damagedPlayer.sendMessage(ChatColor.BLUE + damagedPlayer.getDisplayName() + " 님의 체력에서 " + ev.getDamage() + " 를 깎았습니다");
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
						AllTeams.SendTeamMessage(team, player.getDisplayName() + "님이 " + player.getKiller().getDisplayName() + "님에게 죽었습니다");
						Team killerTeam = AllTeams.FindTeam(player);
						if(killerTeam != null) {
							AllTeams.SendTeamMessage(killerTeam, player.getKiller().getDisplayName() + "님이 " + player.getDisplayName() + "님을 죽었습니다");
							killerTeam.teamKills++;
						}
					} else {
						AllTeams.SendTeamMessage(team, player.getDisplayName() + "님이 죽었습니다");
					}
					team.teamDeaths++;
				}
			}
		}
		
	}
	
	@EventHandler
    public void onTeleport(PlayerTeleportEvent ev){
        if(ev.getCause() == TeleportCause.ENDER_PEARL){
            ev.setCancelled(true);
        }
    }
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent ev) {
		Player player = ev.getPlayer();
		MultiverseWorld mvWorld = Main.core.getMVWorldManager().getMVWorld(player.getWorld());
		Castle castle = AllWorlds.getCastle(mvWorld);
		if(ev.getPlayer().getGameMode() == GameMode.CREATIVE) {
			return;
		}
		if(castle != null) {
			if(castle.allowableBlocks.contains(ev.getBlock())) {
				castle.allowableBlocks.remove(ev.getBlock());
				return;
			}
		} else {
			player.sendMessage("여기에는 블럭을 부실 수 없습니다");
		}
		ev.setCancelled(true);
		
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent ev) {
		Player player = ev.getPlayer();
		MultiverseWorld mvWorld = Main.core.getMVWorldManager().getMVWorld(player.getWorld());
		Castle castle = AllWorlds.getCastle(mvWorld);
		if(ev.getPlayer().getGameMode() == GameMode.CREATIVE) {
			return;
		}
		if(castle != null) {
			if(castle.team == AllTeams.FindTeam(player) || WarSchedular.warStatus == WarStatus.WAR) {
				castle.allowableBlocks.add(ev.getBlock());
				return;
			} else {
				player.sendMessage("여기에는 블럭을 설치할 수 없습니다");
			}
		} else {
			player.sendMessage("여기에는 블럭을 설치할 수 없습니다");
		}
		ev.setCancelled(true);
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
						ev.getPlayer().sendMessage("보유 가능 청크의 한도를 넘어섰습니다");					
					} else {
						team.chunks.add(chunk);
					}
					
				} else {
					
					if(AllTeams.FindTeam(ev.getPlayer()) != null) {
						ev.getPlayer().sendMessage("국가장만 이 기능을 쓸 수 있습니다");
					} else {
						ev.getPlayer().sendMessage("소속된 국가가 없습니다");
					}
					
				}
				
			}
			
		}
		
	}
	*/
	
}
