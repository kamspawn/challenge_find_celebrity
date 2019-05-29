package com.find.celebrity;

import java.util.List;
import java.util.Map;

import com.find.celebrity.entity.People;
import com.find.celebrity.file.FileProcessor;
import com.find.celebrity.processor.CelebrityProcessor;

/**
 * 
 * @author KamGon
 *
 */
public class FindCelebrity{
	
    public static void main( String[] args ){
    	try {
    		if(args.length!=1)
    			throw new Exception("It is required only the file path");
    		
    		FileProcessor fileProcessor = new FileProcessor();
    		fileProcessor.loadFile(args[0]);
    		
    		Map<Integer, People> teamMap = fileProcessor.getTeamMap();
    		
    		if(teamMap.size()<2)
    			throw new Exception("It is required at least 2 team members to identify the celebrity");
    		
    		List<Integer> celebrityList = CelebrityProcessor.getCelebrityId(teamMap);
    		
    		if(celebrityList.size()<1)
    			throw new Exception("It is required add a team member thar does not know anybody");
    		
    		celebrityList.stream().forEach(celebrity -> {
    			if(CelebrityProcessor.checkTeamKnowCelebrity(celebrity, teamMap))
    	    		System.out.println("Celebrity found: "+teamMap.get(celebrity).getName());
    		});
    
    		System.out.println("Process Complete");
    	}catch(Exception e) {
            System.out.println("Process Fail: "+e.getMessage());
    	}
    }
}
