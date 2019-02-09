package com.wh.entity;

import java.io.Serializable;

public class Result<T>  implements Serializable {
	private Boolean success;
	private String state;
	private String msg;
	private T list;
	private TotalPaginationitem paginationitem;
	
	public Result(Boolean success, String state, String msg, T list) {
		super();
		this.success = success;
		this.state = state;
		this.msg = msg;
		this.list = list;
	}
	public Result(Boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	public Result(Boolean success, String state, String msg) {
		super();
		this.success = success;
		this.state = state;
		this.msg = msg;
	}
	
	public Result(Boolean success, String state, String msg, T list,
			TotalPaginationitem paginationitem) {
		super();
		this.success = success;
		this.state = state;
		this.msg = msg;
		this.list = list;
		this.paginationitem = paginationitem;
	}
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Result [msg=" + msg + ", state=" + state + ", success="
				+ success + "]";
	}
	public void setList(T list) {
		this.list = list;
	}
	public T getList() {
		return list;
	}
	public void setPaginationitem(TotalPaginationitem paginationitem) {
		this.paginationitem = paginationitem;
	}
	public TotalPaginationitem getPaginationitem() {
		return paginationitem;
	}
	
	
}
