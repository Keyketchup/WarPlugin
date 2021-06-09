package me.keycatchupwhy.warplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.keycatchupwhy.warplugin.teams.AllTeams;
import me.keycatchupwhy.warplugin.teams.Team;

public class KickTeamCommand implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
			if(sender instanceof Player) {	
				
				Player player = (Player)sender;
				
				if(args.length == 1) {
					Team team = AllTeams.FindOwnersTeam(player);
					if(team != null) {
						Player _p = Bukkit.getPlayer(args[0]);
						if(_p != null && _p != player) {
							Team _t = AllTeams.FindTeam(_p);
							if(_t != null) {
								if(_t == team) {
									team.playerUUIDs.remove(_p.getUniqueId());
								} else {
									player.sendMessage("�÷��̾ ����� ������ ������ �ʽ��ϴ�");
								}
							} else {
								player.sendMessage("�÷��̾ �ƹ��� ������ �ҼӵǾ� ���� �ʽ��ϴ�");
							}
						} else {
							player.sendMessage("��ġ�ϴ� �÷��̾ �����ϴ�");
						}
					} else {
						player.sendMessage("�������� �ƴմϴ�");
					}
				} else {
					player.sendMessage("�߹��� �÷��̾��� �̸��� ��������");
				}
				
			} else {
				return false;
			}
		
		return true;
		
	}
	
}

