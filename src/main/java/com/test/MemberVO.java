package com.test;

public class MemberVO {
private Integer memID;
private String  memName;
public MemberVO(Integer memID, String memName) {
	super();
	this.memID = memID;
	this.memName = memName;
}
public Integer getMemID() {
	return memID;
}
public void setMemID(Integer memID) {
	this.memID = memID;
}
public String getMemName() {
	return memName;
}
public void setMemName(String memName) {
	this.memName = memName;
}

 }
