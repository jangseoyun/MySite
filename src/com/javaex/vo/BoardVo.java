package com.javaex.vo;

public class BoardVo {

	// 필드
	private int bNo;
	private String title;
	private String content;
	private int hit;
	private String regDate;
	private int userNo;

	private int uNo;
	private String uName;

	// 생성자
	public BoardVo() {
	}

	public BoardVo(int bNo, String title, String content, int hit, String regDate, String uName) {
		this.bNo = bNo;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.uName = uName;
	}

	public BoardVo(int bNo, String title, int hit, String regDate, int uNo, String uName, int userNo) {
		this.bNo = bNo;
		this.title = title;
		this.hit = hit;
		this.regDate = regDate;
		this.uNo = uNo;
		this.uName = uName;
		this.userNo = userNo;
	}

	public BoardVo(int bNo, String title, String content, int hit, String regDate, int userNo, int uNo, String uName) {
		this.bNo = bNo;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.userNo = userNo;
		this.uNo = uNo;
		this.uName = uName;
	}

	// 메소드 g,s
	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getuNo() {
		return uNo;
	}

	public void setuNo(int uNo) {
		this.uNo = uNo;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	// 메소드 일반
	@Override
	public String toString() {
		return "BoardVo [bNo=" + bNo + ", title=" + title + ", content=" + content + ", hit=" + hit + ", regDate="
				+ regDate + ", userNo=" + userNo + ", uNo=" + uNo + ", uName=" + uName + "]";
	}

}
