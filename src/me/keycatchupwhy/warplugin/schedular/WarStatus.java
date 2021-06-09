package me.keycatchupwhy.warplugin.schedular;

public enum WarStatus {
	
	NONE("아무런 전쟁이 없음"),
    READY("전쟁 준비시간"),
    COMMUNICATE("회의 시간"),
    WAR("전쟁 중");
    
    String name;
    
    private WarStatus(String name) {
    	this.name = name;
    }
    
    public String GetName() {
    	return this.name;
    }
    
}