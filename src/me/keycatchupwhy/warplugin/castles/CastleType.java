package me.keycatchupwhy.warplugin.castles;

public enum CastleType {
	
	GARDEN("꽃밭"),
	TAIGA("타이기"),
	EXTREME("익스트림"),
	MUSHROOM("버섯섬"),
	ICE("얼음"),
	OCEAN("바다"),
	SWAMP("늪지"),
	DESERT("사막"),
	NETHER("지옥"),
	END("엔드");
	
	public String name;
	
	private CastleType(String name) {
		
		this.name = name;
		
	}
	
	public String GetName() {
		
		return this.name;
		
	}
	
}
