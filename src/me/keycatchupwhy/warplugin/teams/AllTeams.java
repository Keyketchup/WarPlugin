package me.keycatchupwhy.warplugin.teams;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AllTeams {
	
	public static ArrayList<Team> allTeams = new ArrayList<Team>();
	
	public static void addTeam(Team team, Player player) {
		
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).name.toLowerCase() == team.name.toLowerCase()) {
				player.sendMessage("�Ȱ��� �̸��� ������ �̹� �����մϴ�");
				return;
			}
		}
		
		player.sendMessage("������ ���������� �����Ͽ����ϴ�");
		allTeams.add(team);
		
	}
	
	public static void joinTeam(String teamName, Player player) {
		
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).playerUUIDs.contains(player.getUniqueId())) {
				player.sendMessage("�̹� ��� ������ �ҼӵǾ� �ֽ��ϴ�");
				return;
			}
		}
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).name.toLowerCase() == teamName.toLowerCase()) {
				player.sendMessage("���������� ������ �����ϴ�");
				allTeams.get(i).playerUUIDs.add(player.getUniqueId());
				return;
			}
		}
		
		player.sendMessage("������ ã�� �� �����ϴ�");
		
	}
	
	public static void CheckTeam(Player player) {
		
		Team team = FindTeam(player);
		if(team != null) {
			
			player.sendMessage("����� �ҼӵǾ� �ִ� ������ " + team.name + " �Դϴ�");
			return;
		
		}
		
		player.sendMessage("�Ҽӵ� ������ �����ϴ�");
		
	}
	
	@Nullable
	public static Team FindOwnersTeam(Player player) {
		
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).founderUUID == player.getUniqueId()) {
				return allTeams.get(i);
			}
		}
		
		return null;
		
	}
	
	@Nullable
	public static Team FindTeam(Player player) {
		
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).playerUUIDs.contains(player.getUniqueId())) {
				return allTeams.get(i);
			}
		}
		
		return null;
		
	}
	
	@Nullable
	public static Team FindTeam(String name) {
		
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).name == name) {
				return allTeams.get(i);
			}
		}
		
		return null;
		
	}
	
	public static void SendTeamMessage(Team team, String message) {
		
		for(int i=0; i<team.playerUUIDs.size(); i++) {
			if(Bukkit.getPlayer(team.playerUUIDs.get(i)) != null) {
				Player player = Bukkit.getPlayer(team.playerUUIDs.get(i));
				player.sendMessage("[Team]: " + message);
			}
		}
		
	}
	
	public static void ResetAllDeaths() {
		
		for(int i=0; i<allTeams.size(); i++) {
			allTeams.get(i).teamDeaths = 0;
		}
		
	}
	
	public static void ResetAllKills() {
		
		for(int i=0; i<allTeams.size(); i++) {
			allTeams.get(i).teamKills = 0;
		}
		
	}
	
}
