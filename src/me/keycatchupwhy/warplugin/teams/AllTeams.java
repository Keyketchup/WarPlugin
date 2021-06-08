package me.keycatchupwhy.warplugin.teams;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class AllTeams {
	
	public static ArrayList<Team> allTeams = new ArrayList<Team>();
	
	public static void addTeam(Team team, Player player) {
		
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).name.toLowerCase() == team.name.toLowerCase()) {
				player.sendMessage("�Ȱ��� �̸��� ������ �̹� �����մϴ�");
				return;
			}
			if(allTeams.get(i).founderUUID == team.founderUUID) {
				player.sendMessage("�̹� ������ �����ϰ� �ֽ��ϴ�");
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
		
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).playerUUIDs.contains(player.getUniqueId())) {
				player.sendMessage("����� �ҼӵǾ� �ִ� ������ " + allTeams.get(i).name + " �Դϴ�");
				return;
			}
		}
		
		player.sendMessage("�Ҽӵ� ������ �����ϴ�");
		
	}
	
}
