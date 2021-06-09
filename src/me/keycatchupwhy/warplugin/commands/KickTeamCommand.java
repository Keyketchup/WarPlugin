package me.keycatchupwhy.warplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.keycatchupwhy.warplugin.teams.AllTeams;
import me.keycatchupwhy.warplugin.teams.Team;

public class KickTeamCommand implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
			if(sender instanceof Player) {	
				
				Player player = (Player)sender;
				
				if(args.length == 1) {
					Team team = AllTeams.FindOwnersTeam(player);
					if(team != null) {
						Player _p = Bukkit.getPlayer(args[0]);
						if(_p != null && _p != player) {
							Team _t = AllTeams.FindTeam(_p);
							if(_t != null) {
								if(_t == team) {
									team.playerUUIDs.remove(_p.getUniqueId());
								} else {
									player.sendMessage("플레이어가 당신의 국가에 들어가있지 않습니다");
								}
							} else {
								player.sendMessage("플레이어가 아무런 국가에 소속되어 있지 않습니다");
							}
						} else {
							player.sendMessage("일치하는 플레이어가 없습니다");
						}
					} else {
						player.sendMessage("국가장이 아닙니다");
					}
				} else {
					player.sendMessage("추방할 플레이어의 이름을 적으세요");
				}
				
			} else {
				return false;
			}
		
		return true;
		
	}
	
}

