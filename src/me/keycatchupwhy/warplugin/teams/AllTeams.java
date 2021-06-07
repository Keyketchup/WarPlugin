package me.keycatchupwhy.warplugin.teams;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class AllTeams {
	
	public static ArrayList<Team> allTeams = new ArrayList<Team>();
	
	public static void addTeam(Team team, Player player) {
		
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).name == team.name) {
				player.sendMessage("똑같은 이름의 국가가 이미 존재합니다");
				return;
			}
			if(allTeams.get(i).founderUUID == team.founderUUID) {
				player.sendMessage("이미 국가을 보유하고 있습니다");
				return;
			}
		}
		
		allTeams.add(team);
		
	}
	
}
