package me.keycatchupwhy.warplugin.castles;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;

import com.onarandombox.MultiverseCore.api.MultiverseWorld;

import me.keycatchupwhy.warplugin.teams.Team;

public class Castle {
	
	public CastleType castleType;
	public Team team;
	public Material icon;
	public MultiverseWorld mvWorld;
	public ArrayList<Block> allowableBlocks = new ArrayList<Block>();
	
	public Castle(CastleType castleType, Material icon, MultiverseWorld mvWorld) {
		this.castleType = castleType;
		this.icon = icon;
		this.mvWorld = mvWorld;
	}
	
	public String getName() {
		return castleType.GetName();
	}
	
}
