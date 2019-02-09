package com.wh.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

public class ExportUtil {
	
	public static void setRegionBorder(CellRangeAddress region, Sheet sheet){
		RegionUtil.setBorderBottom(CellStyle.BORDER_THIN,region, sheet, sheet.getWorkbook());
		RegionUtil.setBorderLeft(CellStyle.BORDER_THIN,region, sheet, sheet.getWorkbook());
		RegionUtil.setBorderRight(CellStyle.BORDER_THIN,region, sheet, sheet.getWorkbook());
		RegionUtil.setBorderTop(CellStyle.BORDER_THIN,region, sheet, sheet.getWorkbook());
	}

	private static HSSFWorkbook export(List<Map<String, Object>> tblSource,ExcelModel excelModel) throws UnsupportedEncodingException {
		if (tblSource.size() == 0)
			return null;
		List<Map<String, Object>> dtSource = new ArrayList<Map<String, Object>>();
		if (excelModel.columnsName == null || excelModel.columnsName.length == 0) {
			dtSource = tblSource;
		} else {
			Map<String, Object> rowData = null;
			Map<String, Object> temp = null;
			for (int i = 0; i < tblSource.size(); i++) {

				rowData = new LinkedHashMap<String, Object>();
				temp = tblSource.get(i);
				for (int j = 0; j < excelModel.columnsName.length; j++) {
					if (temp.containsKey(excelModel.columnsName[j])) {
						rowData.put(excelModel.columnsName[j], temp.get(excelModel.columnsName[j]));
					} else {
						rowData.put(excelModel.columnsName[j], "");
					}
				}
				dtSource.add(rowData);
			}
		}

		HSSFWorkbook workbook = new HSSFWorkbook();
		Sheet sheet = null;

		for (Map.Entry<Integer, Integer> entry : excelModel.cellsWidth.entrySet()) {
			if (entry.getKey().intValue() >= dtSource.get(0).keySet().size() || entry.getKey().intValue() < 0) {
				excelModel.cellsWidth.remove(entry.getKey());
			}
		}
		for (Map.Entry<Integer, String> entry : excelModel.cellsTitle.entrySet()) {
			if (entry.getKey().intValue() >= dtSource.get(0).keySet().size() || entry.getKey().intValue() < 0) {
				excelModel.cellsTitle.remove(entry.getKey());
			}
		}

		int[] arrColWidth = new int[dtSource.get(0).keySet().size()];
		CellStyle cellStyle;
		int columnIndex = 0;
		for (Map.Entry<String, Object> entry : dtSource.get(0).entrySet()) {

			if (!excelModel.cellsTitle.containsKey(new Integer(columnIndex))) {
				excelModel.cellsTitle.put(new Integer(columnIndex), entry.getKey());
			}

			arrColWidth[columnIndex] = excelModel.cellsTitle.get(new Integer(columnIndex)).getBytes("UTF-8").length;

			cellStyle = workbook.createCellStyle();
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);   
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);   
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);   
			cellStyle.setBorderRight(CellStyle.BORDER_THIN); 
			if (excelModel.cellsHorizontalAlignment.containsKey(new Integer(columnIndex))) {
				cellStyle.setAlignment(excelModel.cellsHorizontalAlignment.get(new Integer(columnIndex)).shortValue());
			} else {
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			}

			excelModel.cellsStyle.put(columnIndex, cellStyle);
			columnIndex++;
		}
		for (int i = 0; i < dtSource.size(); i++) {
			columnIndex = 0;
			for (Map.Entry<String, Object> entry : dtSource.get(i).entrySet()) {
				int intTemp = 0;
				if (entry.getValue() == null) {
					dtSource.get(i).put(entry.getKey(), "");
					intTemp = 0;
				} else {
					intTemp = entry.getValue().toString().getBytes("UTF-8").length;
				}
				if (intTemp > arrColWidth[columnIndex]) {
					arrColWidth[columnIndex] = intTemp;
				}
				columnIndex++;
			}
		}

		for (int i = 0; i < arrColWidth.length; i++) {
			if (!excelModel.cellsWidth.containsKey(new Integer(i))) {
				excelModel.cellsWidth.put(new Integer(i), new Integer((arrColWidth[i] + 1) * 256));
			} else {
				excelModel.cellsWidth.put(new Integer(i), new Integer((excelModel.cellsWidth.get(new Integer(i)).intValue() + 1) * 256));
			}
		}

		int rowIndex = 0;
		for (int tblRowIndex = 0; tblRowIndex < dtSource.size(); tblRowIndex++) {
			// 新建表，填充表头，填充列头，样式
			if (rowIndex % 65535 == 0) {
				// if (rowIndex != 0) {
				sheet = workbook.createSheet();
				rowIndex = 0;
				// 设置列宽
				for (int colIndex = 0; colIndex < excelModel.cellsWidth.size(); colIndex++) {
					if (excelModel.cellsWidth.get(new Integer(colIndex)).intValue() > 0) {
						sheet.setColumnWidth(colIndex, (excelModel.cellsWidth.get(new Integer(colIndex)).intValue() > 65280 ? 65280
								: excelModel.cellsWidth.get(new Integer(colIndex)).intValue()));
					} else {
						sheet.setColumnHidden(colIndex, true);
					}
				}
				if (excelModel.showHeader) {
					// 文档标题及样式
					Row headerRow = sheet.createRow(rowIndex);
					headerRow.setHeightInPoints(25);
					headerRow.createCell(0).setCellValue(excelModel.headerText);

					CellStyle headStyle = workbook.createCellStyle();
					headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					Font font = workbook.createFont();
					font.setFontHeightInPoints((short) 20);
					font.setBoldweight((short) 20);
					headStyle.setFont(font);
					headerRow.getCell(0).setCellStyle(headStyle);
					sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, excelModel.cellsWidth.size() - 1));
					rowIndex++;
				}
				sheet.createFreezePane( 0, 1, 0, 1 ); //冻结首行
				// 列头及样式
				Row headerRow = sheet.createRow(rowIndex);
				CellStyle headStyle = workbook.createCellStyle();
				headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				headStyle.setBorderBottom(CellStyle.BORDER_THIN);   
				headStyle.setBorderTop(CellStyle.BORDER_THIN);   
				headStyle.setBorderLeft(CellStyle.BORDER_THIN);   
				headStyle.setBorderRight(CellStyle.BORDER_THIN); 
				Font font = workbook.createFont();
				font.setFontHeightInPoints((short) 10);
				font.setBoldweight((short) 700);
				headStyle.setFont(font);

				if (excelModel.dataHeader == null) {
					for (int colIndex = 0; colIndex < excelModel.cellsWidth.size(); colIndex++) {
						headerRow.createCell(colIndex).setCellValue(excelModel.cellsTitle.get(new Integer(colIndex)));
						headerRow.getCell(colIndex).setCellStyle(headStyle);
					}
					rowIndex++;
				} else {
					rowIndex = excelModel.dataHeader.create(sheet, rowIndex, headStyle);
				}
			}
			// 填充内容
			Row dataRow = sheet.createRow(rowIndex);
			columnIndex = 0;
			for (Map.Entry<String, Object> entry : dtSource.get(tblRowIndex).entrySet()) {
				Cell newCell = dataRow.createCell(columnIndex);
				newCell.setCellStyle(excelModel.cellsStyle.get(new Integer(columnIndex)));
				String drValue = entry.getValue().toString();
				newCell.setCellValue(drValue);
				columnIndex++;
			}
			rowIndex++;
		}
		return workbook;
	}

	/**
	 * 文件下载设置
	 * @param request
	 * @param response
	 * @param fileName
	 * @throws UnsupportedEncodingException 
	 */
	private static void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
		final String userAgent = request.getHeader("USER-AGENT");
		// 中文文件名支持
		String finalFileName = null;
		if (StringUtils.contains(userAgent, "MSIE")) {
			finalFileName = URLEncoder.encode(fileName, "UTF8");
		} else if (StringUtils.contains(userAgent, "Mozilla")) {
			finalFileName = new String(fileName.getBytes(), "ISO8859-1");
		} else {
			finalFileName = URLEncoder.encode(fileName, "UTF8");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFileName + "\"");
	}
	
	/**
	 * 直接导出Excel到页面
	 * @param request
	 * @param response
	 * @param tblSource 数据源
	 * @param fileName 文件名
	 * @throws IOException 
	 */
	public static void exportList(HttpServletRequest request, HttpServletResponse response,
			List<Map<String, Object>> tblSource,ExcelModel excelModel, String fileName) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		setFileDownloadHeader(request, response, fileName);
		export(tblSource,excelModel).write(response.getOutputStream());
	}
	/**
	 * 导出Excel
	 * @param tblSource 数据源
	 * @param excelPath 文件路径
	 * @return
	 * @throws IOException 
	 */
	public static void exportListToFile(List<Map<String, Object>> tblSource,ExcelModel excelModel,String excelPath) throws IOException {
		File file=new File(excelPath);
		File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		FileOutputStream outputStream = new FileOutputStream(excelPath);
		export(tblSource,excelModel).write(outputStream);
		outputStream.flush();
		outputStream.close();
	}
}
