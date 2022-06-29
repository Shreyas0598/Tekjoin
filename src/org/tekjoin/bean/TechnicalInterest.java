package org.tekjoin.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class TechnicalInterest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Map<String, String> hmap = new HashMap<String, String>();
	private int userId;
	private String name;
	private String about;
	private Vector<String> techdetails;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	private int count = 0;
	
	public Vector<String> getTechdetails() {
		return techdetails;
	}
	public void setTechdetails(Vector<String> techdetails) {
		this.techdetails = techdetails;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	static
	{
		hmap.put("1", "android development");
		hmap.put("2", "machine learning");
		hmap.put("3", "big data analytics");
		hmap.put("4", "blockchain");
		hmap.put("5", "cloud computing");
		hmap.put("6", "competitive coding");
		hmap.put("7", "computer graphics");
		hmap.put("8", "computer networks");
		hmap.put("9", "computer languages");
		hmap.put("10", "cyber security and cryptography");
		hmap.put("11", "data mining");
		hmap.put("12", "data sciences");
		hmap.put("13", "deep learning");
		hmap.put("14", "ethical hacking");
		hmap.put("15", "internet of things");
		hmap.put("16", "virtual reality");
		hmap.put("17", "web development");
		hmap.put("18", "The Letter A");
	}
	
	public String getTech(String i)
	{
		return hmap.get(i);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
}
