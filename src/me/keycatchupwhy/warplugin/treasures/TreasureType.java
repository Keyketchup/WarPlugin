package me.keycatchupwhy.warplugin.treasures;

public enum TreasureType {
	
	HERB("신비의 약초", 3),
	MASK("원주민 가면", 3),
	DOPAMIN("도파민", 3),
	NEEDLE("뾰족한 독침", 3),
	TSUNAMI("쓰나미 생성기", 3),
	DUCK("늪에 빠진 오리인형", 3),
	DIARY("허기진 여행자의 일기", 3),
	GLOVE("블레이즈 글러브 ", 3),
	PEARL("엔더 진주", 3),
	HOE("농사꾼의 마법 괭이", 3),
	PICKAXE("광부의 마법 곡괭이", 3),
	AXE("나무꾼의 마법 도끼", 3),
	SHOVEL("도굴꾼의 마법 삽", 3),
	SWORD("검사의 마법 검", 3),
	SHOES("에너지 드링크", 3);
	
	public String name;
	public int maxLevel;
	
	private TreasureType(String name, int maxLevel) {
		this.name = name;
		this.maxLevel = maxLevel;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getMaxLevel() {
		return this.maxLevel;
	}
	
}
