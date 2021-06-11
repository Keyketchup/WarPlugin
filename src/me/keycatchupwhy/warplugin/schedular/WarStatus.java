package me.keycatchupwhy.warplugin.schedular;

public enum WarStatus {
	
	NONE("전쟁 중이 아님"),
    READY("전쟁 준비 시간"),
    COMMUNICATE("전쟁 회의 시간"),
    WAR("전쟁 시간");
    
    String name;
    
    private WarStatus(String name) {
    	this.name = name;
    }
    
    public String GetName() {
    	return this.name;
    }
    
}