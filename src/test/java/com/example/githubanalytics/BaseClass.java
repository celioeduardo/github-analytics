package com.example.githubanalytics;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

//
public class BaseClass {

	
	@Before
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(new IssuesController(service()));
	}
	
	private IssuesService service() {
		IssuesService service = mock(IssuesService.class);
		given(service.count()).willReturn(5L);
		given(service.list()).willReturn(issues());
		return service;
	}
	
	private List<IssueDto> issues(){
		List<IssueDto> dtos = new ArrayList<>();
		dtos.add(new IssueDto("foo","spring-cloud/bar"));
		dtos.add(new IssueDto("bar","spring-cloud/baz"));
		return dtos;
	}
}
