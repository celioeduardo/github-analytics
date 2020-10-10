package com.example.githubanalytics;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/issues")
@AllArgsConstructor
class IssuesController {
	
	private final IssuesService service;

	@GetMapping("/count")
	long count() {
		return service.count();
	}
	
	@GetMapping
	List<IssueDto> issues(){
		return service.list();
	}
	
	@PostMapping
	void save(IssueDto issue) {
		service.save(issue.getUserName(), issue.getRepository());
	}
	
	@DeleteMapping
	void delete() {
		service.deleteAll();
	}
	
}
