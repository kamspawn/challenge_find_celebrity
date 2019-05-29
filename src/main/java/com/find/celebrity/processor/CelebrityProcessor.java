package com.find.celebrity.processor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.find.celebrity.entity.People;

public class CelebrityProcessor {

	public static List<Integer> getCelebrityId(Map<Integer, People> teamMap) {
		Iterator<Entry<Integer, People>> it = teamMap.entrySet().iterator();
		Entry<Integer, People> tmpEntry = null;
		List<Integer> celebrityIdList = new ArrayList<>();
		
		while(it.hasNext()){
			tmpEntry = it.next();
			if(tmpEntry.getValue().getTeamKnowList().size()==0) {
				celebrityIdList.add(tmpEntry.getKey());
			}
		}
		
		return celebrityIdList;
	}
	
	public static Boolean checkTeamKnowCelebrity(Integer celebrityId, Map<Integer, People> teamMap) {
		List<People> notKnowList = new ArrayList<>();
		
		teamMap.forEach((id,people) -> {
			if(id != celebrityId && !people.getTeamKnowList().contains(celebrityId)) {
				notKnowList.add(people);
			}
		});
		
		return notKnowList.size()<1?true:false;
	}
}
