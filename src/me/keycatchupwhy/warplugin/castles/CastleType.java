package me.keycatchupwhy.warplugin.castles;

public enum CastleType {
	
	GARDEN("�ɹ�"),
	TAIGA("Ÿ�̱�"),
	EXTREME("�ͽ�Ʈ��"),
	MUSHROOM("������"),
	ICE("����"),
	OCEAN("�ٴ�"),
	SWAMP("����"),
	DESERT("�縷"),
	NETHER("����"),
	END("����");
	
	public String name;
	
	private CastleType(String name) {
		
		this.name = name;
		
	}
	
	public String GetName() {
		
		return this.name;
		
	}
	
}
