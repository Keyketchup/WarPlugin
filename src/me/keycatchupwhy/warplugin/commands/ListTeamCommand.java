package me.keycatchupwhy.warplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.keycatchupwhy.warplugin.teams.AllTeams;
import me.keycatchupwhy.warplugin.teams.Team;

public class ListTeamCommand implements CommandExecutor  {
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player)sender;
			String finalword = "";
			
			for(int i=0; i<AllTeams.allTeams.size(); i++) {
				
				Team team = AllTeams.allTeams.get(i);
				String word = team.founderName + "   " + team.name + "   " + team.playerUUIDs.size() + "Έν\n";
				finalword += word;
				
			}
			
			player.sendMessage(finalword);
			
		} else {
			return false;
		}
		
		return true;
		
	}
	
}
