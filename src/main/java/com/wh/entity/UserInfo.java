package com.wh.entity;

import java.io.Serializable;

/**
 * 用户
 */
public class UserInfo  implements Serializable{

	private String f_Id;
	private String f_UserId;
	private String f_UserName;
	private String f_Email;
	private String f_ImgUrl;
	private String f_PassWord;
	private String f_Code;
	
	
	public String getF_Code() {
		return f_Code;
	}
	public void setF_Code(String f_Code) {
		this.f_Code = f_Code;
	}
	public String getF_Id() {
		return f_Id;
	}
	public void setF_Id(String fId) {
		f_Id = fId;
	}
	public String getF_UserId() {
		return f_UserId;
	}
	public void setF_UserId(String fUserId) {
		f_UserId = fUserId;
	}
	public String getF_UserName() {
		return f_UserName;
	}
	public void setF_UserName(String fUserName) {
		f_UserName = fUserName;
	}
	public String getF_Email() {
		return f_Email;
	}
	public void setF_Email(String fEmail) {
		f_Email = fEmail;
	}
	public String getF_ImgUrl() {
		return f_ImgUrl;
	}
	public void setF_ImgUrl(String fImgUrl) {
		f_ImgUrl = fImgUrl;
	}
	public void setF_PassWord(String f_PassWord) {
		this.f_PassWord = f_PassWord;
	}
	public String getF_PassWord() {
		return f_PassWord;
	}

	
	

}
