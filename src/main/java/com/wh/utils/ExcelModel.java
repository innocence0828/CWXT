package com.wh.utils;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelModel {
	/**
	 * 导出内容的标题
	 */
	public String headerText = "";
	
	/**
	 * 数据列头
	 */
	public IDataHeader dataHeader = null;

	/**
	 * 是否显示表头
	 */
	public Boolean showHeader = false;

	/**
	 * 单元格的横向对齐设置
	 */
	public Hashtable<Integer, Short> cellsHorizontalAlignment = new Hashtable<Integer, Short>();

	/**
	 * 单元格的样式设置
	 */
	public Hashtable<Integer, CellStyle> cellsStyle = new Hashtable<Integer, CellStyle>();

	/**
	 * 单元格的宽度设置
	 */
	public Hashtable<Integer, Integer> cellsWidth = new Hashtable<Integer, Integer>();

	/**
	 * 单元格的标题设置
	 */
	public Hashtable<Integer, String> cellsTitle = new Hashtable<Integer, String>();

	/**
	 * 需要显示的列名
	 */
	public String[] columnsName = null;
	public interface IDataHeader {
	    public int create(Sheet sheet, int rowIndex, CellStyle headStyle);
	}
}
