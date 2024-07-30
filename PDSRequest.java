package com.test.sku.network.pds;

import java.io.Serializable;

public class PDSRequest implements Serializable
{
	private PDSVO pds;
	public static enum CMD { UPLOAD, LIST, DETAIL, SEARCH, UPDATE, DELETE};
	private CMD cmd;
	
	public PDSRequest() {}
	
	public PDSRequest(CMD cmd) {
		this.cmd = cmd;
	}
	
	public PDSVO getPds() {
		return pds;
	}
	public void setPds(PDSVO pds) {
		this.pds = pds;
	}
	public CMD getCmd() {
		return cmd;
	}
	public void setCmd(CMD cmd) {
		this.cmd = cmd;
	}
}
