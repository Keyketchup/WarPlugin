package me.keycatchupwhy.warplugin.teams;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class AllTeams {
	
	public static ArrayList<Team> allTeams = new ArrayList<Team>();
	
	public static void addTeam(Team team, Player player) {
		
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).name.toLowerCase() == team.name.toLowerCase()) {
				player.sendMessage("똑같은 이름의 국가가 이미 존재합니다");
				return;
			}
			if(allTeams.get(i).founderUUID == team.founderUUID) {
				player.sendMessage("이미 국가을 보유하고 있습니다");
				return;
			}
		}
		
		player.sendMessage("국가를 성공적으로 생성하였습니다");
		allTeams.add(team);
		
	}
	
	public static void joinTeam(String teamName, Player player) {
		
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).playerUUIDs.contains(player.getUniqueId())) {
				player.sendMessage("이미 어느 국가에 소속되어 있습니다");
				return;
			}
		}
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).name.toLowerCase() == teamName.toLowerCase()) {
				player.sendMessage("성공적으로 국가에 들어갔습니다");
				allTeams.get(i).playerUUIDs.add(player.getUniqueId());
				return;
			}
		}
		
		player.sendMessage("국가를 찾을 수 없습니다");
		
	}
	
	public static void CheckTeam(Player player) {
		
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).playerUUIDs.contains(player.getUniqueId())) {
				player.sendMessage("당신이 소속되어 있는 국가은 " + allTeams.get(i).name + " 입니다");
				return;
			}
		}
		
		player.sendMessage("소속된 국가가 없습니다");
		
	}
	
}
