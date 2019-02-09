package com.wh.sys;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	private String id;
	private String name;
	private String pid;
	private Integer level;
	private Integer sort;
	private Object other;
	private List<TreeNode> childrenNode=new ArrayList<TreeNode>();

	public TreeNode(String id,String name,String pid,Integer level,Integer sort,Object other) {
		this.id=id;
		this.name=name;
		this.pid=pid;
		this.level=level;
		this.sort=sort;
		this.other=other;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Object getOther() {
		return other;
	}
	public void setOther(Object other) {
		this.other = other;
	}
	
	public List<TreeNode> getChildrenNode() {
		return childrenNode;
	}
	public void setChildrenNode(List<TreeNode> childrenNode) {
		this.childrenNode = childrenNode;
	}

	
}
