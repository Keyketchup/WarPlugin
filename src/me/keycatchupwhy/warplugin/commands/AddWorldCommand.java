package me.keycatchupwhy.warplugin.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.onarandombox.MultiverseCore.api.MultiverseWorld;

import me.keycatchupwhy.warplugin.AllWorlds;
import me.keycatchupwhy.warplugin.Main;
import me.keycatchupwhy.warplugin.castles.Castle;
import me.keycatchupwhy.warplugin.castles.CastleType;

public class AddWorldCommand implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
			if(sender instanceof Player) {	
				
				Player player = (Player)sender;
				
				if(args.length > 0) {
					
					if(args[0].equalsIgnoreCase("castle")) {
						
						if(args.length >= 2) {
							
							if(args[1].equalsIgnoreCase("help")) {
								String helpString = "/castle <CastleType> <Material>\nCastleType : \n";
								for(int i=0; i<CastleType.values().length; i++) {
									helpString += CastleType.values()[i].name + " : " + CastleType.values()[i].name() + "\n";
								}
								player.sendMessage(helpString);
							}
							
						}
						
						if(args.length >= 3) {
							
							Castle castle = new Castle(null, null, null);
							MultiverseWorld mvWorld = Main.core.getMVWorldManager().getMVWorld(player.getWorld());
							if(mvWorld == null) {
								player.sendMessage("메타버스 월드를 찾다가 오류가 발생하였습니다, 조금 있다가 다시 시도해주세요");
								return true;
							}
							for(int i=0; i<AllWorlds.allCastles.size(); i++) {
								if(AllWorlds.allCastles.get(i).mvWorld == mvWorld) {
									player.sendMessage("이 월드에 중복된 성이 있습니다, /deleteworld를 써주세요");
									return true;
								}
							}
							castle.mvWorld = mvWorld;
							if(CastleType.valueOf(args[1]) == null) {
								player.sendMessage("CastleType의 변수값이 알맞지 않습니다, /addWorld castle help를 이용하세요");
								return true;
							} else {
								castle.castleType = CastleType.valueOf(args[1]);
							}
							if(Material.valueOf(args[2]) == null) {
								player.sendMessage("Material의 변수값이 알맞지 않습니다");
								return true;
							} else {
								castle.icon = Material.valueOf(args[2]);
							}
							
							AllWorlds.addCastle(castle);
							player.sendMessage("성을 추가하였습니다");
							
						}
						
					}
					
				}
								
			} else {
				return false;
			}
		
		return true;
		
	}
	
}

