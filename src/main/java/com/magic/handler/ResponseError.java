package com.magic.handler;

import java.util.List;

public class ResponseError {
    String info;
    List<String>list;
	public ResponseError(String info, List<String> list) {
		super();
		this.info = info;
		this.list = list;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
    
    
}
