package me.keycatchupwhy.warplugin.schedular;

public enum WarStatus {
	
	NONE("�ƹ��� ������ ����"),
    READY("���� �غ�ð�"),
    COMMUNICATE("ȸ�� �ð�"),
    WAR("���� ��");
    
    String name;
    
    private WarStatus(String name) {
    	this.name = name;
    }
    
    public String GetName() {
    	return this.name;
    }
    
}