package com.find.celebrity.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.find.celebrity.entity.People;
import com.find.celebrity.exception.WrongFormatException;

public class FileProcessor {

	private Map<Integer, People> teamMap = new HashMap<Integer, People>();

	public void loadFile(String fileName) throws WrongFormatException, IOException {
		File source = new File(fileName);
		validateFile(source);

		FileInputStream file = new FileInputStream(source);

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (row.getRowNum() == 0)
				continue;// Avoid the column headers

			Cell codeIdCell = row.getCell(0);// Code Id
			if (codeIdCell == null) {
				System.out.println("Row [" + (row.getRowNum() + 1) + "] Code_Id is required");
				continue;
			}

			String tmpId = null;
			switch (codeIdCell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                	int cellVal = Double.valueOf(codeIdCell.getNumericCellValue()).intValue();
                	tmpId = String.valueOf(cellVal);
                    break;
                case Cell.CELL_TYPE_STRING:
                	tmpId = codeIdCell.getStringCellValue();
                    break;
            }		
			
			
			if (!StringUtils.isNumeric(tmpId)) {
				System.out.println("Row [" + (row.getRowNum() + 1) + "] Code_Id is not numeric value [" + tmpId + "]");
				continue;
			}

			Cell nameCell = row.getCell(1);// Name
			if (nameCell == null || StringUtils.isBlank(nameCell.getStringCellValue())) {
				System.out.println("Row [" + (row.getRowNum() + 1) + "] Code_Id [" + tmpId + "] require a Name");
				continue;
			}

			People people = new People(Integer.valueOf(tmpId), nameCell.getStringCellValue());

			Cell knowListCell = row.getCell(2);// KnowList
			if (knowListCell != null) {
				List<Integer> knowList = new ArrayList<>();
				switch (knowListCell.getCellType()){
	                case Cell.CELL_TYPE_NUMERIC:
	                	int cellVal = Double.valueOf(knowListCell.getNumericCellValue()).intValue();
	                	knowList.add(cellVal);
	                    break;
	                case Cell.CELL_TYPE_STRING:
	                	if(StringUtils.isNotBlank(knowListCell.getStringCellValue())){
	        				String tmpKnown = knowListCell.getStringCellValue();
	        				knowList = Arrays.asList(tmpKnown.split("\\|"))
	        						.stream().map(Integer::valueOf)
	        						.collect(Collectors.toList());
	                	}
	                	break;
				}		
				people.setTeamKnowList(knowList);
			}
			teamMap.put(people.getCodeId(), people);
		}
		file.close();
	}

	private void validateFile(File source) throws FileNotFoundException, WrongFormatException {
		if (!source.exists() || !source.isFile())
			throw new FileNotFoundException("File not Found "+source.getPath());

		String name = source.getName();
		String extension = name.substring(name.lastIndexOf("."));

		if (!extension.equalsIgnoreCase(".xlsx"))
			throw new WrongFormatException(extension);
	}

	public Map<Integer, People> getTeamMap() {
		return teamMap;
	}
}
