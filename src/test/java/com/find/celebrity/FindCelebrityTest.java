package com.find.celebrity;

import static org.junit.Assert.assertTrue;


import org.apache.logging.log4j.LogManager;
import org.junit.Rule;
import org.junit.Test;

import com.find.celebrity.util.LogAppenderResource;

public class FindCelebrityTest {

    @Rule 
    public LogAppenderResource appender = new LogAppenderResource(LogManager.getLogger(FindCelebrity.class)); 
	
	
	@Test
	public void successfulCelebrityFound() {
		String[] fileName = new String[] {"src/test/resources/Success_Example.xlsx"};
		FindCelebrity.main(fileName);
		assertTrue(appender.getOutput().contains("Celebrity found: Jose"));
	}

	@Test
	public void wrongParameters() {
		String[] fileNames = new String[] {"src/test/resources/Success_Example.xlsx", "badpath"};
		FindCelebrity.main(fileNames);
		assertTrue(appender.getOutput().contains("It is required only the file path"));
	}

	@Test
	public void lessThan2Members() {
		String[] fileName = new String[] {"src/test/resources/Wrong_Example_2.xlsx"};
		FindCelebrity.main(fileName);
		assertTrue(appender.getOutput().contains("It is required at least 2 team members to identify the celebrity"));
	}

	@Test
	public void notFoundCelebrity() {
		String[] fileName = new String[] {"src/test/resources/Wrong_Example_1.xlsx"};
		FindCelebrity.main(fileName);
		assertTrue(appender.getOutput().contains("It is required add a team member thar does not know anybody"));
	}

	@Test
	public void FoundMoreThan1Celebrities() {
		String[] fileName = new String[] {"src/test/resources/Wrong_Example_3.xlsx"};
		FindCelebrity.main(fileName);
		assertTrue(appender.getOutput().contains("Only one candidate to be a celebrity was allowed"));
	}

	@Test
	public void celebrityNotFound() {
		String[] fileName = new String[] {"src/test/resources/Wrong_Example_4.xlsx"};
		FindCelebrity.main(fileName);
		assertTrue(appender.getOutput().contains("Celebrity was not found"));
	}

	@Test
	public void fakeFile() {
		String[] fileName = new String[] {"src/test/resources/Fake_File.xlsx"};
		FindCelebrity.main(fileName);
		assertTrue(appender.getOutput().contains("File not Found src\\test\\resources\\Fake_File.xlsx"));
	}
}
