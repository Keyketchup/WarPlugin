package me.keycatchupwhy.warplugin;

import java.util.ArrayList;

import javax.annotation.Nullable;

import com.onarandombox.MultiverseCore.api.MultiverseWorld;

import me.keycatchupwhy.warplugin.castles.Castle;
import me.keycatchupwhy.warplugin.castles.CastleType;
import me.keycatchupwhy.warplugin.teams.Team;

public class AllWorlds {
	
	public static ArrayList<Castle> allCastles = new ArrayList<Castle>();
	public static MultiverseWorld farmWorld;
	public static MultiverseWorld fishingWorld;
	public static MultiverseWorld miningWorld;
	public static MultiverseWorld prisonWorld;
	
	public static void addCastle(Castle castle) {
		
		for(int i=0; i<allCastles.size(); i++) {
			if(allCastles.get(i).castleType == castle.castleType) {
				allCastles.set(i, castle);
				return;
			}
		}
		allCastles.add(castle);
		
	}
	
	@Nullable
	public static Castle getCastle(CastleType castleType) {
		
		for(int i=0; i<allCastles.size(); i++) {
			if(allCastles.get(i).castleType == castleType) {
				return allCastles.get(i);
			}
		}
		
		return null;
		
	}
	
	@Nullable
	public static Castle getCastle(MultiverseWorld mvWorld) {
		
		for(int i=0; i<allCastles.size(); i++) {
			if(allCastles.get(i).mvWorld == mvWorld) {
				return allCastles.get(i);
			}
		}
		
		return null;
		
	}
	
	public static ArrayList<Castle> getCastles(Team team) {
		
		ArrayList<Castle> res = new ArrayList<Castle>();
		
		for(int i=0; i<allCastles.size(); i++) {
			if(allCastles.get(i).team.name == team.name) {
				res.add(allCastles.get(i));
			}
		}
		
		return res;
		
	}
	
}
