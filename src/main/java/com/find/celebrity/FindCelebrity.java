package com.find.celebrity;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.find.celebrity.entity.People;
import com.find.celebrity.file.FileProcessor;
import com.find.celebrity.processor.CelebrityProcessor;

/**
 * 
 * @author KamGon
 *
 */
public class FindCelebrity{
	
	private static final Logger logger = LogManager.getLogger(FindCelebrity.class);
	
    public static void main( String[] args ){
    	try {
    		if(args.length!=1) {
    			logger.error("It is required only the file path");
    			return;
    		}
    		
    		FileProcessor fileProcessor = new FileProcessor();
    		fileProcessor.loadFile(args[0]);
    		
    		Map<Integer, People> teamMap = fileProcessor.getTeamMap();
    		
    		if(teamMap.size()<2) {
    			logger.error("It is required at least 2 team members to identify the celebrity");
    			return;
    		}
    		
    		List<Integer> celebrityList = CelebrityProcessor.getCelebrityId(teamMap);
    		
    		if(celebrityList.size()<1) {
    			logger.error("It is required add a team member thar does not know anybody");
    			return;
    		}else if(celebrityList.size() > 1) {
    			logger.error("Only one candidate to be a celebrity was allowed");
    			return;
    		}
    		
    		People celebrityCandidate = teamMap.get(celebrityList.get(0));
			if(CelebrityProcessor.checkTeamKnowCelebrity(celebrityCandidate.getCodeId(), teamMap)) {
				logger.info("Celebrity found: "+celebrityCandidate.getName());
			}else {
				logger.warn("Celebrity was not found");
			}
    
    		logger.info("Process Complete");
    	}catch(Exception e) {
    		logger.error("Process Fail: "+e.getMessage());
    	}
    }
}
