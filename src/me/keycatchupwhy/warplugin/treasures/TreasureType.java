package me.keycatchupwhy.warplugin.treasures;

public enum TreasureType {
	
	HERB("�ź��� ����", 3),
	MASK("���ֹ� ����", 3),
	DOPAMIN("���Ĺ�", 3),
	NEEDLE("������ ��ħ", 3),
	TSUNAMI("������ ������", 3),
	DUCK("�˿� ���� ��������", 3),
	DIARY("����� �������� �ϱ�", 3),
	GLOVE("������ �۷��� ", 3),
	PEARL("���� ����", 3),
	HOE("������ ���� ����", 3),
	PICKAXE("������ ���� ���", 3),
	AXE("�������� ���� ����", 3),
	SHOVEL("�������� ���� ��", 3),
	SWORD("�˻��� ���� ��", 3),
	SHOES("������ �帵ũ", 3);
	
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
