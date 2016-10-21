package com.independent.shadow.http;

public class CommData {

	private String pid; // 手机唯一标识
	private int us; // 渠道号
	private String version; //  版本号
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getUs() {
		return us;
	}
	public void setUs(int us) {
		this.us = us;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}


}
