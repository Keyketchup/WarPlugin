package me.keycatchupwhy.warplugin.teams;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Team {
	
	public String name;
	public String founderName;
	public UUID founderUUID;
	public ArrayList<UUID> playerUUIDs;
	
	public int points;
	public int teamDeaths;
	public int teamKills;
	
	public boolean hasCastle;
	
	public Team(String _name, Player _founderPlayer) {
		
		name = _name;
		founderName = _founderPlayer.getName();
		founderUUID = _founderPlayer.getUniqueId();
		playerUUIDs = new ArrayList<UUID>();
		playerUUIDs.add(founderUUID);
		points = 10000;
		hasCastle = false;
		
	}
	
}
