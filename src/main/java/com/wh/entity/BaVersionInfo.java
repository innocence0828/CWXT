package com.wh.entity;
import org.springframework.web.multipart.MultipartFile;
/** 
 * 版本更新
 * 版本号
 * 版本更新描述
 *  APP编号
 * App文件名
 * 上传时间
 *  文件路径
 * 上传文件
 *
 */
public class BaVersionInfo {
	
	private String f_Id;
	private String f_Verid;
	private String f_Verdesb;
	private String f_Vertitle;
	private String f_Filename;
	private String f_Crttime;
	private String f_Url;

	
	public String getF_Id() {
		return f_Id;
	}
	public void setF_Id(String f_Id) {
		this.f_Id = f_Id;
	}
	public String getF_Verid() {
		return f_Verid;
	}
	public void setF_Verid(String f_Verid) {
		this.f_Verid = f_Verid;
	}
	public String getF_Verdesb() {
		return f_Verdesb;
	}
	public void setF_Verdesb(String f_Verdesb) {
		this.f_Verdesb = f_Verdesb;
	}
	public String getF_Filename() {
		return f_Filename;
	}
	public void setF_Filename(String f_Filename) {
		this.f_Filename = f_Filename;
	}
	public String getF_Crttime() {
		return f_Crttime;
	}
	public void setF_Crttime(String f_Crttime) {
		this.f_Crttime = f_Crttime;
	}
	public String getF_Url() {
		return f_Url;
	}
	public void setF_Url(String f_Url) {
		this.f_Url = f_Url;
	}
	public String getF_Vertitle() {
		return f_Vertitle;
	}
	public void setF_Vertitle(String f_Vertitle) {
		this.f_Vertitle = f_Vertitle;
	}

	
	
	

}
