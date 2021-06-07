package me.keycatchupwhy.warplugin.teams;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Team {
	
	public String name;
	public String founderName;
	public UUID founderUUID;
	public ArrayList<UUID> playerUUIDs = new ArrayList<UUID>();
	
	public Team(String _name, Player _founderPlayer) {
		name = _name;
		founderName = _founderPlayer.getName();
		founderUUID = _founderPlayer.getUniqueId();
	}
	
}
