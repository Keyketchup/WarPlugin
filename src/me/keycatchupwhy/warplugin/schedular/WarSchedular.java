package me.keycatchupwhy.warplugin.schedular;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.keycatchupwhy.warplugin.Main;
import me.keycatchupwhy.warplugin.NetherBoardsManager;
import me.keycatchupwhy.warplugin.teams.AllTeams;

public class WarSchedular {
	
	public static WarStatus warStatus;
	public static Boolean isPaused;
	public static int schedularId;
	public static int timeLeft;
	
	public static void StartSchedule() {
		
		schedularId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {

            	@Override
            	public void run() {
            		for(int i=0; i<AllTeams.allTeams.size(); i++) {
            			NetherBoardsManager.SetBoard(AllTeams.allTeams.get(i));
            		}
            		
            		if(!isPaused || warStatus == WarStatus.NONE) {
            			timeLeft--;
            		}
            		if(warStatus == WarStatus.READY && timeLeft <= 0) {
            			StartCommunicate();
            		}
            		if(warStatus == WarStatus.COMMUNICATE && timeLeft <= 0) {
            			StartWar();
            		}
            		if(warStatus == WarStatus.WAR && timeLeft <= 0) {
            			AfterWar();
            		}
            		
            	}

			},
				
       0L,20);
		
	}
	
	public static void KillSchedule() {
		
		Bukkit.getScheduler().cancelTask(schedularId);
		
	}
	
	public static void DeclareWar() {
		Bukkit.broadcastMessage(ChatColor.RED + "40�� �Ŀ� ���� ���� ����ð��� ���۵˴ϴ�! �غ��ϼ��� [�غ�ð� 30��]");
		warStatus = WarStatus.READY;
		timeLeft = 30 * 60;
		AllTeams.ResetAllDeaths();
		AllTeams.ResetAllKills();
	}
	
	public static void StartCommunicate() {
		Bukkit.broadcastMessage(ChatColor.RED + "���� 10�е��� ������ ���� ������ �����ϼ��� [ȸ�ǽð� 10��]");
		warStatus = WarStatus.COMMUNICATE;
		timeLeft = 10*60;
	}
	
	public static void StartWar() {
		Bukkit.broadcastMessage(ChatColor.RED + "������ ���۵˴ϴ�, �ڽ��� ������ ��Ű�ų� �ٸ� ������ �����ϼ���! [�����ð� 15��]");
		warStatus = WarStatus.WAR;
		timeLeft = 15*60;
	}
	
	public static void AfterWar() {
		Bukkit.broadcastMessage(ChatColor.RED + "������ �������ϴ�, ���� �������� ���ư�����");
		warStatus = WarStatus.NONE;
		timeLeft = 0;
	}
	
}
