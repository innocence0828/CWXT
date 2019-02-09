package com.wh.entity;

/**
 * @author lyw
 * 业务字典表
 */

public class BaBusinessdct {
	
	private String f_Id;
	private String f_Parent;
	private String f_Number;
	private String f_Caption;
	public String getF_Parent() {
		return f_Parent;
	}
	public void setF_Parent(String fParent) {
		f_Parent = fParent;
	}
	public String getF_Number() {
		return f_Number;
	}
	public void setF_Number(String fNumber) {
		f_Number = fNumber;
	}
	public String getF_Caption() {
		return f_Caption;
	}
	public void setF_Caption(String fCaption) {
		f_Caption = fCaption;
	}
	public void setF_Id(String f_Id) {
		this.f_Id = f_Id;
	}
	public String getF_Id() {
		return f_Id;
	}
	

}
