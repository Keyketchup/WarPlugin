package me.keycatchupwhy.warplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.keycatchupwhy.warplugin.teams.AllTeams;
import me.keycatchupwhy.warplugin.teams.Team;

public class MakeTeamCommand implements CommandExecutor  {
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(args.length == 1) {
				
				Team newTeam = new Team(args[0], player);
				AllTeams.addTeam(newTeam, player);
				
			} else {
				return false;
			}
		} else {
			return false;
		}
		
		return true;
		
	}
	
}
