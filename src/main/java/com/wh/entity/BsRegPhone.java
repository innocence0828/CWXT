package com.wh.entity;

public class BsRegPhone {
	/** 
	 * 验证码类
	 * @param f_Id    
	 * @param f_Phone  手机号
	 * @param f_PhoneCode  验证码
	 * @return
	 */
	private String f_Id;
	private String f_Phone;
	private String f_PhoneCode;
	
	public BsRegPhone() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BsRegPhone(String f_Phone, String f_PhoneCode) {
		super();
		this.f_Phone = f_Phone;
		this.f_PhoneCode = f_PhoneCode;
	}
	public String getF_Id() {
		return f_Id;
	}
	public void setF_Id(String f_Id) {
		this.f_Id = f_Id;
	}
	public String getF_Phone() {
		return f_Phone;
	}
	public void setF_Phone(String f_Phone) {
		this.f_Phone = f_Phone;
	}
	public String getF_PhoneCode() {
		return f_PhoneCode;
	}
	public void setF_PhoneCode(String f_PhoneCode) {
		this.f_PhoneCode = f_PhoneCode;
	}
	
	
	
	

}
