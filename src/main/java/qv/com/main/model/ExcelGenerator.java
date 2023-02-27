package qv.com.main.model;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import qv.com.main.entities.Revenue;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelGenerator {
	private List<Revenue> listRecords;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private int totalAmount = 0;
	
	public ExcelGenerator(List<Revenue> listRecords) {
		this.listRecords = listRecords;
		
		for (int i = 0; i < listRecords.size(); i++) {
			this.totalAmount += (listRecords.get(i).getSellprice() * listRecords.get(i).getQuantity());
		}
		workbook = new XSSFWorkbook();
	}

	private void writeHeader() {
		sheet = workbook.createSheet("Revenue Records");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Phone Name", style);
		createCell(row, 1, "Capacity(GB)", style);
		createCell(row, 2, "Quantity", style);
		createCell(row, 3, "Sell Price", style);
		createCell(row, 4, "Revenue", style);
		createCell(row, 5, "Sell Date", style);
		createCell(row, 6, "User Name", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} 
        else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void write() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		

		for (Revenue record : listRecords) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			
			
			String[] dArr = record.getSelldate().toString().split("-");  // ex input: "2010-01-18"
			String dateConverted = dArr[2]+ "/" +dArr[1]+ "/" +dArr[0]; //ex output: "18/01/2010"

			createCell(row, columnCount++, record.getPhonename(), style);
			createCell(row, columnCount++, record.getCapacity(), style);
			createCell(row, columnCount++, record.getQuantity(), style);
			createCell(row, columnCount++, record.getSellprice(), style);
			createCell(row, columnCount++, record.getTotal(), style);
			createCell(row, columnCount++, dateConverted, style);
			createCell(row, columnCount++, record.getUsername(), style);
		}
		
		
		CellStyle style1 = workbook.createCellStyle();
		XSSFFont font1 = workbook.createFont();
		font1.setBold(true);
		style1.setAlignment(HorizontalAlignment.CENTER);
		font1.setFontHeight(16);
		style1.setFont(font1);
		style1.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		
		CellRangeAddress tong = new CellRangeAddress(rowCount,rowCount,0,3);
		CellRangeAddress totalAmount = new CellRangeAddress(rowCount,rowCount,4,6); 
		Row row = sheet.createRow(rowCount);
		sheet.addMergedRegion(tong);
		sheet.addMergedRegion(totalAmount);
		createCell(row, 0, "Tổng :", style1);
		createCell(row, 4, this.totalAmount, style1);
	}

	public void generate(HttpServletResponse response) throws IOException {
		writeHeader();
		write();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}
