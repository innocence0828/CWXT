package com.wh.entity;
/**
 * 条数和价格
 * @author Thinkpad-e470
 *
 */
public class TotalPaginationitem {
	private String numItem;
	private String totalPrice;
	
	public TotalPaginationitem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TotalPaginationitem(String numItem, String totalPrice) {
		super();
		this.numItem = numItem;
		this.totalPrice = totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setNumItem(String numItem) {
		this.numItem = numItem;
	}
	public String getNumItem() {
		return numItem;
	}
	
	
	

}
