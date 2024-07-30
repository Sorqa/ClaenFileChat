package com.test.sku.network.pds;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDSVO implements Serializable 
{
    private int no;
    private String fname;
    private String author;
    private long fsize;
    private Date date;
    private String desc;
    private byte[] fdata;
    /** equals()에서 비교에 사용될 속성명(no,fname 둘 중 하나)*/
    private String key;

    public PDSVO() {}
    
    public PDSVO(int no) {
    	this.no = no;
    	this.key = "no";
    }
    
    public PDSVO(String fname) {
    	this.fname = fname;
    	this.key = "fname";
    }

	@Override
	public boolean equals(Object obj) {
		PDSVO other = (PDSVO) obj;
		if(key.equals("no")) return this.getNo()==other.getNo();
		else if(key.equals("fname")) return this.getFname().equals(other.getFname());
		else return false;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = sdf.format(date);
		return String.format("%d\t%s\t%s\t%d\t%s\t%s", no,fname,author,fsize,sDate,desc);
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getFsize() {
		return fsize;
	}

	public void setFsize(long fsize) {
		this.fsize = fsize;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public byte[] getFdata() {
		return fdata;
	}

	public void setFdata(byte[] fdata) {
		this.fdata = fdata;
	}
	
}