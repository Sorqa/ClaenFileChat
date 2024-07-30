package com.test.sku.network.pds;

import java.io.Serializable;
import java.util.List;

public class PDSResponse implements Serializable
{
	private String msg;
	private List<PDSVO> list;
	private PDSVO pds;
	
	public PDSResponse() {}
	
	public PDSResponse(PDSVO pds) {
		this.pds = pds;
	}
	public PDSResponse(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<PDSVO> getList() {
		return list;
	}

	public void setList(List<PDSVO> list) {
		this.list = list;
	}

	public PDSVO getPds() {
		return pds;
	}

	public void setPds(PDSVO pds) {
		this.pds = pds;
	}
}
