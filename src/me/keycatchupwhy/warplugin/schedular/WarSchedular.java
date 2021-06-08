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
		Bukkit.broadcastMessage(ChatColor.RED + "40�� �Ŀ� ���� ���� ����ð��� ���۵˴ϴ�! �غ��ϼ��� [�غ�ð� 30��]");
		warStatus = WarStatus.READY;
		count = 0;
	}
	
	public static void StartCommunicate() {
		Bukkit.broadcastMessage(ChatColor.RED + "���� 10�е��� ������ ���� ������ �����ϼ��� [ȸ�ǽð� 10��]");
		warStatus = WarStatus.COMMUNICATE;
	}
	
	public static void StartWar() {
		Bukkit.broadcastMessage(ChatColor.RED + "������ ���۵˴ϴ�, �ڽ��� ������ ��Ű�ų� �ٸ� ������ �����ϼ���! [�����ð� 15��]");
		warStatus = WarStatus.WAR;
	}
	
	public static void AfterWar() {
		Bukkit.broadcastMessage(ChatColor.RED + "������ �������ϴ�, ���� �������� ���ư�����");
		warStatus = WarStatus.NONE;
		count = 0;
	}
	
}
