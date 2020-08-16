package com.excel;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author 付雪
 * @data 2017/8/21
 * @description
 */
public class EXExport {

	// ExportToExcle(域名，数据);
	public void ExportToExcle(List<List<List<String>>> datas) throws Exception {

		EXConfig config = new EXConfig();
		config.parse();

		// 1.创建workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// 2.创建Sheet
		List<String> sheets = new ArrayList<String>();
		sheets = config.getSheet();
		for (int k = 0; k < sheets.size(); k++) {
			XSSFSheet xssfSheet = workbook.createSheet(sheets.get(k));

			// 3.创建row
			XSSFRow row;
			XSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中

			// 4.表头
			List<String> columnHeaderList = new ArrayList<String>();
			columnHeaderList = config.getColumns().get(k);
			int hLength = columnHeaderList.size();
			int[][] maxColumnWidth = new int[hLength][1];
			row = xssfSheet.createRow(0);
			XSSFCell cell = null;
			for (int i = 0; i < hLength; i++) {
				cell = row.createCell(i);
				cell.setCellValue(columnHeaderList.get(i));
				cell.setCellStyle(style); // 内容居中
				maxColumnWidth[i][0] = columnHeaderList.get(i).toString().length() * 512;
			}

			// 5.插入数据
			int dLength = datas.get(k).size();
			System.out.print(datas.get(k).size());
			for (int i = 0; i < dLength; i++) {
				int numRow = i + 1;
				int startcell = 0;
				row = xssfSheet.createRow(numRow);

				List<String> data = datas.get(k).get(i);
				String dataValue = null;

				for (int j = startcell; j < data.size() + startcell; j++) {
					dataValue = data.get(j - startcell);
					cell = row.createCell(j);
					cell.setCellValue(dataValue);
					cell.setCellStyle(style);

					// 设置列宽
					if (maxColumnWidth[j - startcell][0] < dataValue.toString().length() * 512) {
						xssfSheet.setColumnWidth(j, dataValue.toString().length() * 512);
					}
				}
			}

		}

		// 6.将生成的excle文件保存在指定的路径下
		try {
			OutputStream os = new FileOutputStream(config.getOutpath());
			workbook.write(os);
			os.close();

			System.out.println("Excel文件生成成功,文件位置：" + config.getOutpath());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("Excel文件生成失败...");
		}
	}
}
