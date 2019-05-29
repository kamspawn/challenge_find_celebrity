package com.find.celebrity.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author KamGon
 *
 */
public class People implements Serializable{

	private static final long serialVersionUID = 204854076847954625L;

	private Integer codeId;
	private String name;
	private List<Integer> teamKnowList;
	
	public People(Integer codeId, String name) {
		this.codeId = codeId;
		this.name = name;
		this.teamKnowList = new ArrayList<>();
	}
	
	public Integer getCodeId() {
		return codeId;
	}
	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getTeamKnowList() {
		return teamKnowList;
	}

	public void setTeamKnowList(List<Integer> teamKnowList) {
		this.teamKnowList = teamKnowList;
	}
	
}
