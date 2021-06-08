package me.keycatchupwhy.warplugin.schedular;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.keycatchupwhy.warplugin.Main;

public class WarSchedular {
	
	public static WarStatus warStatus;
	public static Boolean isPaused;
	public static int schedularId;
	public static int count;
	
	public static int speed = 1200;
	
	public static void StartSchedule() {
		
		schedularId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {

            	@Override
            	public void run() {
            		
            		if(!isPaused || warStatus == WarStatus.NONE) {
            			count ++;
            		}
            		if(warStatus == WarStatus.READY && count == 6) {
            			StartCommunicate();
            		}
            		if(warStatus == WarStatus.COMMUNICATE && count == 8) {
            			StartWar();
            		}
            		if(warStatus == WarStatus.WAR && count == 11) {
            			AfterWar();
            		}
            		
            	}

			},
				
       0L,5L*speed);
		
	}
	
	public static void KillSchedule() {
		
		Bukkit.getScheduler().cancelTask(schedularId);
		
	}
	
	public static void DeclareWar() {
		Bukkit.broadcastMessage(ChatColor.RED + "40분 후에 국가 간의 전쟁시간이 시작됩니다! 준비하세요 [준비시간 30분]");
		warStatus = WarStatus.READY;
		count = 0;
	}
	
	public static void StartCommunicate() {
		Bukkit.broadcastMessage(ChatColor.RED + "남은 10분동안 동료들과 서로 전략을 상의하세요 [회의시간 10분]");
		warStatus = WarStatus.COMMUNICATE;
	}
	
	public static void StartWar() {
		Bukkit.broadcastMessage(ChatColor.RED + "전쟁이 시작됩니다, 자신의 국가를 지키거나 다른 국가를 공격하세요! [전생시간 15분]");
		warStatus = WarStatus.WAR;
	}
	
	public static void AfterWar() {
		Bukkit.broadcastMessage(ChatColor.RED + "전쟁이 끝났습니다, 각자 진영으로 돌아가세요");
		warStatus = WarStatus.NONE;
		count = 0;
	}
	
}
