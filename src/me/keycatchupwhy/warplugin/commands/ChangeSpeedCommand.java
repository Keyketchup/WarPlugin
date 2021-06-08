package me.keycatchupwhy.warplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.keycatchupwhy.warplugin.schedular.WarSchedular;

public class ChangeSpeedCommand implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
			if(sender instanceof Player) {	
				
				if(args.length == 1) {
					
					try {
						
						int numl = Integer.parseInt(args[0]);
						
						WarSchedular.speed = numl;
						
					} catch (NumberFormatException ex) {
		                return false;
		            }
					
				}
				
			} else {
				return false;
			}
		
		return true;
		
	}
	
}

