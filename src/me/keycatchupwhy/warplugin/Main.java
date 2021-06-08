package me.keycatchupwhy.warplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.keycatchupwhy.warplugin.commands.ChangeSpeedCommand;
import me.keycatchupwhy.warplugin.commands.CheckTeamCommand;
import me.keycatchupwhy.warplugin.commands.JoinTeamCommand;
import me.keycatchupwhy.warplugin.commands.ListTeamCommand;
import me.keycatchupwhy.warplugin.commands.MakeTeamCommand;
import me.keycatchupwhy.warplugin.commands.WarManagerCommand;
import me.keycatchupwhy.warplugin.schedular.WarSchedular;
import me.keycatchupwhy.warplugin.schedular.WarStatus;

public class Main extends JavaPlugin {
	
	public static Plugin instance;
	
	public void onEnable() {
		instance = this;
		Bukkit.getServer().getPluginManager().registerEvents(new AllEvents(), this);
		this.getCommand("maketeam").setExecutor(new MakeTeamCommand());
		this.getCommand("listteam").setExecutor(new ListTeamCommand());
		this.getCommand("jointeam").setExecutor(new JoinTeamCommand());
		this.getCommand("checkteam").setExecutor(new CheckTeamCommand());
		this.getCommand("warmanager").setExecutor(new WarManagerCommand());
		this.getCommand("changespeed").setExecutor(new ChangeSpeedCommand());
		
		WarSchedular.warStatus = WarStatus.NONE;
		WarSchedular.isPaused = true;
		WarSchedular.StartSchedule();
	}
	
	public void OnDisable() {
		
	}
	
}
