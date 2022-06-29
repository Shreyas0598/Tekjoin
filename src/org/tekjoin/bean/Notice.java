package org.tekjoin.bean;

import java.io.Serializable;

public class Notice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int userId;
	private int noticeId;
	private String notice;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) 
	{
		this.noticeId = noticeId;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	

}
