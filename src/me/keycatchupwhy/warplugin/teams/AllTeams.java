package me.keycatchupwhy.warplugin.teams;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class AllTeams {
	
	public static ArrayList<Team> allTeams = new ArrayList<Team>();
	
	public static void addTeam(Team team, Player player) {
		
		for(int i=0; i<allTeams.size(); i++) {
			if(allTeams.get(i).name == team.name) {
				player.sendMessage("�Ȱ��� �̸��� ������ �̹� �����մϴ�");
				return;
			}
			if(allTeams.get(i).founderUUID == team.founderUUID) {
				player.sendMessage("�̹� ������ �����ϰ� �ֽ��ϴ�");
				return;
			}
		}
		
		allTeams.add(team);
		
	}
	
}
