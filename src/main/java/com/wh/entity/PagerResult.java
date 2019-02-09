package com.wh.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagerResult<T> {
	private Long page;
	private Long total;
	private Long records;
	private List<T> rows = new ArrayList<T>();
	private Map<String,Object> userdata = new HashMap<String,Object>();
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getRecords() {
		return records;
	}
	public void setRecords(Long records) {
		this.records = records;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Long getPage() {
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Map<String,Object> getUserdata() {
		return userdata;
	}
	public void setUserdata(Map<String,Object> userdata) {
		this.userdata = userdata;
	}
}
