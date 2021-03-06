package me.keycatchupwhy.warplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.keycatchupwhy.warplugin.teams.AllTeams;

public class JoinTeamCommand implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 1) {
			if(sender instanceof Player) {	
				
				Player player = (Player)sender;
				
				AllTeams.joinTeam(args[0], player);
				
				
			} else {
				return false;
			}
		} else {
			return false;
		}
		
		return true;
		
	}
	
}
