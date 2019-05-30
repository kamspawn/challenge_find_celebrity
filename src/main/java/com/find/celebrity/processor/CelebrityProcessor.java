package com.find.celebrity.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.find.celebrity.entity.People;

public class CelebrityProcessor {

	private CelebrityProcessor() {
		
	}
	
	public static List<Integer> getCelebrityId(Map<Integer, People> teamMap) {
		List<Integer> celebrityIdList = new ArrayList<>();
		
		teamMap.forEach((id, people) -> {
			if(people.getTeamKnowList().size()==0) {
				celebrityIdList.add(id);
			}
		});
		
		return celebrityIdList;
	}
	
	public static Boolean checkTeamKnowCelebrity(Integer celebrityId, Map<Integer, People> teamMap) {
		List<People> notKnowList = new ArrayList<>();
		
		teamMap.forEach((id, people) -> {
			if(id != celebrityId && !people.getTeamKnowList().contains(celebrityId)) {
				notKnowList.add(people);
			}
		});
		
		return notKnowList.size()<1?true:false;
	}
}
