package com.kopo.memo;

public class Memo {
    int idx;
	String created;
    String content;
	
	Memo() {
	}
	
	Memo(int idx, String created, String content) {
		this.idx = idx;
		this.created = created;
		this.content = content;
	}
	
	public int getIdx() {
		return this.idx;
	}
	
	public String getCreated() {
		return this.created;
	}
	
	public String getContent() {
		return this.content;
	}
}
