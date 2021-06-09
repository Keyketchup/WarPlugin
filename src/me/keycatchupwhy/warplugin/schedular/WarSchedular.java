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
		Bukkit.broadcastMessage(ChatColor.RED + "40분 후에 국가 간의 전쟁시간이 시작됩니다! 준비하세요 [준비시간 30분]");
		warStatus = WarStatus.READY;
		timeLeft = 30 * 60;
		AllTeams.ResetAllDeaths();
		AllTeams.ResetAllKills();
	}
	
	public static void StartCommunicate() {
		Bukkit.broadcastMessage(ChatColor.RED + "남은 10분동안 동료들과 서로 전략을 상의하세요 [회의시간 10분]");
		warStatus = WarStatus.COMMUNICATE;
		timeLeft = 10*60;
	}
	
	public static void StartWar() {
		Bukkit.broadcastMessage(ChatColor.RED + "전쟁이 시작됩니다, 자신의 국가를 지키거나 다른 국가를 공격하세요! [전생시간 15분]");
		warStatus = WarStatus.WAR;
		timeLeft = 15*60;
	}
	
	public static void AfterWar() {
		Bukkit.broadcastMessage(ChatColor.RED + "전쟁이 끝났습니다, 각자 진영으로 돌아가세요");
		warStatus = WarStatus.NONE;
		timeLeft = 0;
	}
	
}
