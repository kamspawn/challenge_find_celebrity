package com.find.celebrity.file;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import com.find.celebrity.entity.People;
import com.find.celebrity.exception.WrongFormatException;

public class FileProcessorTest {

	@Test
	public void loadFileSucess() throws WrongFormatException, IOException {
		FileProcessor fileProcessor = new FileProcessor();
		String fileName = "src/test/resources/Success_Example.xlsx";
		fileProcessor.loadFile(fileName);
		
		Map<Integer, People> teamMap = fileProcessor.getTeamMap();
		
		assertEquals(teamMap.size(), 10);
	}

	@Test(expected = FileNotFoundException.class)
	public void fileNotFound() throws WrongFormatException, IOException {
		FileProcessor fileProcessor = new FileProcessor();
		String fileName = "src/test/resources/Fake_File.xlsx";
		fileProcessor.loadFile(fileName);
	}

	@Test(expected = FileNotFoundException.class)
	public void notSourceFile() throws WrongFormatException, IOException {
		FileProcessor fileProcessor = new FileProcessor();
		String fileName = "src/test/resources";
		fileProcessor.loadFile(fileName);
	}

	@Test(expected = WrongFormatException.class)
	public void notValidExtension() throws WrongFormatException, IOException {
		FileProcessor fileProcessor = new FileProcessor();
		String fileName = "src/test/resources/fake_file_ext.txt";
		fileProcessor.loadFile(fileName);
	}

	@Test
	public void loadFileOptionFails1() throws WrongFormatException, IOException {
		FileProcessor fileProcessor = new FileProcessor();
		String fileName = "src/test/resources/Wrong_Example_1.xlsx";
		fileProcessor.loadFile(fileName);
		
		Map<Integer, People> teamMap = fileProcessor.getTeamMap();
		
		assertEquals(teamMap.size(), 7);
	}
}
