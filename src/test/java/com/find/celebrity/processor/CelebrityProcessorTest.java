package com.find.celebrity.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.find.celebrity.entity.People;

public class CelebrityProcessorTest {

	@Test
	public void findCelebrityId() {
		Map<Integer, People> teamMap = new HashMap<>();
		People people = new People(1, "Juan");
		teamMap.put(1, people);
		
		people = new People(2, "Ana");
		List<Integer> knowList = new ArrayList<>();
		knowList.add(1);
		people.setTeamKnowList(knowList);
		teamMap.put(2, people);
		
		List<Integer> celebrityList = CelebrityProcessor.getCelebrityId(teamMap);
		
		assertEquals(celebrityList.size(), 1);
	}

	@Test
	public void checkTeamKnowCelebrity() {
		Map<Integer, People> teamMap = new HashMap<>();
		People people = new People(1, "Juan");
		teamMap.put(1, people);
		
		people = new People(2, "Ana");
		List<Integer> knowList = new ArrayList<>();
		knowList.add(1);
		people.setTeamKnowList(knowList);
		teamMap.put(2, people);
		
		Boolean teamKnow = CelebrityProcessor.checkTeamKnowCelebrity(1, teamMap);
		assertTrue(teamKnow);
		
		teamKnow = CelebrityProcessor.checkTeamKnowCelebrity(5, teamMap);
		assertFalse(teamKnow);
	}
}
