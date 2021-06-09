package me.keycatchupwhy.warplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import me.keycatchupwhy.warplugin.schedular.WarSchedular;
import me.keycatchupwhy.warplugin.schedular.WarStatus;
import me.keycatchupwhy.warplugin.teams.Team;

public class NetherBoardsManager {
	public static void SetBoard(Team team) {
		for(int i=0; i<team.playerUUIDs.size(); i++) {
			BPlayerBoard board = Netherboard.instance().createBoard(Bukkit.getPlayer(team.playerUUIDs.get(i)), ChatColor.YELLOW + "����������");
			board.set(WarSchedular.warStatus.GetName(), 7);
			if(WarSchedular.warStatus != WarStatus.NONE) {
				board.set(WarSchedular.timeLeft/60 + ":" + WarSchedular.timeLeft%60, 6);
				board.set("�� ���� - " + team.teamDeaths, 2);
				board.set("�� ų �� - " + team.teamKills, 1);
			}
			board.set(" ", 5);
			board.set("�� �̸� - " + team.name, 4);
			board.set("�� ���� - " + team.points, 3);
		}
	}
	
}
