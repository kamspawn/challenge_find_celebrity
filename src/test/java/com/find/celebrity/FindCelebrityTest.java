package com.find.celebrity;

import org.junit.Test;

public class FindCelebrityTest {

	@Test
	public void successfulCelebrityFound() {
		String[] fileName = new String[] {"src/test/resources/Success_Example.xlsx"};
		FindCelebrity.main(fileName);
	}

	@Test
	public void wrongParameters() {
		String[] fileNames = new String[] {"src/test/resources/Success_Example.xlsx", "badpath"};
		FindCelebrity.main(fileNames);
	}

	@Test
	public void lessThan2Members() {
		String[] fileName = new String[] {"src/test/resources/Wrong_Example_2.xlsx"};
		FindCelebrity.main(fileName);
	}

	@Test
	public void notFoundCelebrity() {
		String[] fileName = new String[] {"src/test/resources/Wrong_Example_1.xlsx"};
		FindCelebrity.main(fileName);
	}
}
