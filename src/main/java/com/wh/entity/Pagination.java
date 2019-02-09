package com.wh.entity;
/**
*分页查询
*/


public class Pagination {
	//开始页
	private int startPage;
	//每页显示行数
	private int numPage;
	//开始日期
	private String startDate;
	//结束日期
	private String endDate;
	/**
	 * 收入支出 0代表收入1代表支出
	 */
	private String  f_Direction_type;
	
	/**
	 * 消费类型 ：比如 吃饭，购物
	 */
	private String  f_Consume_type;
	
	/**
	 * 账户类型：支付宝微信
	 */
	private String  f_Account_type;
	
	/**
	 * 用户
	 */
	private String  f_UserId;
	
	
	public Pagination() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Pagination(int startPage, int numPage) {
		super();
		this.startPage = startPage;
		this.setNumPage(numPage);
	}

	public Pagination(int startPage, int numPage, String startDate,
			String endDate) {
		super();
		this.startPage = startPage;
		this.numPage = numPage;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Pagination(int startPage, int numPage, String startDate,
			String endDate, String fDirectionType) {
		super();
		this.startPage = startPage;
		this.numPage = numPage;
		this.startDate = startDate;
		this.endDate = endDate;
		f_Direction_type = fDirectionType;
	}



	public Pagination(int startPage, int numPage, String startDate,
			String endDate, String fDirectionType, String fConsumeType,
			String fAccountType, String fUserId) {
		super();
		this.startPage = startPage;
		this.numPage = numPage;
		this.startDate = startDate;
		this.endDate = endDate;
		f_Direction_type = fDirectionType;
		f_Consume_type = fConsumeType;
		f_Account_type = fAccountType;
		f_UserId = fUserId;
	}


	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public void setNumPage(int numPage) {
		this.numPage = numPage;
	}


	public int getNumPage() {
		return numPage;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setF_Direction_type(String f_Direction_type) {
		this.f_Direction_type = f_Direction_type;
	}


	public String getF_Direction_type() {
		return f_Direction_type;
	}


	public void setF_Consume_type(String f_Consume_type) {
		this.f_Consume_type = f_Consume_type;
	}


	public String getF_Consume_type() {
		return f_Consume_type;
	}


	public void setF_Account_type(String f_Account_type) {
		this.f_Account_type = f_Account_type;
	}


	public String getF_Account_type() {
		return f_Account_type;
	}


	public void setF_UserId(String f_UserId) {
		this.f_UserId = f_UserId;
	}


	public String getF_UserId() {
		return f_UserId;
	}

	

}
