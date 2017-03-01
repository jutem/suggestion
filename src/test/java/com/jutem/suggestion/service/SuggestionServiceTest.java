package com.jutem.suggestion.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jutem.suggestion.base.BaseTest;

public class SuggestionServiceTest extends BaseTest{
	
	@Autowired
	public SuggestionService suggestionService;
	
	@Test
	public void insertCases() {
		suggestionService.insertRandomCases();
	}
}
