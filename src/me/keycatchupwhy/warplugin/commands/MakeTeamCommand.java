package me.keycatchupwhy.warplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.keycatchupwhy.warplugin.schedular.WarSchedular;
import me.keycatchupwhy.warplugin.schedular.WarStatus;
import me.keycatchupwhy.warplugin.teams.AllTeams;
import me.keycatchupwhy.warplugin.teams.Team;

public class MakeTeamCommand implements CommandExecutor  {
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(WarSchedular.warStatus == WarStatus.NONE || WarSchedular.isPaused == true) {
				if(args.length == 1) {
					
					if(AllTeams.FindTeam(player) == null) {
						Team newTeam = new Team(args[0], player);
						AllTeams.addTeam(newTeam, player);
					} else {
						player.sendMessage("이미 팀에 들어가 있습니다");
					}
					
				} else {
					player.sendMessage("팀의 이름을 적으세요");
					return false;
				}
			} else {
				player.sendMessage("아직 전쟁중입니다, 나중에 다시 시도하세요");
			}
		} else {
			return false;
		}
		
		return true;
		
	}
	
}
