package com.wh.entity;
/**
 * 统计数据类
 * @author Thinkpad-e470
 *
 */
public class CountInfo {
	/** 
	 * @param f_Date    日期
	 * @param f_Income  收入
	 * @param f_Expense 支出
	 * @param f_Balance 剩余
	 * @param f_UserId 剩余
	 * @return
	 */
	private String f_Date;
	private String f_Income;
	private String f_Expense;
	private String f_Balance;
	private String f_UserId;
	
	public CountInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CountInfo(String fDate, String fIncome, String fExpense,
			String fBalance) {
		super();
		f_Date = fDate;
		f_Income = fIncome;
		f_Expense = fExpense;
		f_Balance = fBalance;
	}



	public String getF_Date() {
		return f_Date;
	}

	public void setF_Date(String fDate) {
		f_Date = fDate;
	}

	public String getF_Expense() {
		return f_Expense;
	}

	public void setF_Expense(String fExpense) {
		f_Expense = fExpense;
	}

	public String getF_Income() {
		return f_Income;
	}

	public void setF_Income(String fIncome) {
		f_Income = fIncome;
	}

	public String getF_Balance() {
		return f_Balance;
	}

	public void setF_Balance(String fBalance) {
		f_Balance = fBalance;
	}

	public void setF_UserId(String f_UserId) {
		this.f_UserId = f_UserId;
	}

	public String getF_UserId() {
		return f_UserId;
	}
	
	

}
