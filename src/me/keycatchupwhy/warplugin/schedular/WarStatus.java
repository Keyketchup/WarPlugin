package me.keycatchupwhy.warplugin.schedular;

public enum WarStatus {
	
	NONE("���� ���� �ƴ�"),
    READY("���� �غ� �ð�"),
    COMMUNICATE("���� ȸ�� �ð�"),
    WAR("���� �ð�");
    
    String name;
    
    private WarStatus(String name) {
    	this.name = name;
    }
    
    public String GetName() {
    	return this.name;
    }
    
}