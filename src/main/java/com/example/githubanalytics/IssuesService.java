package com.example.githubanalytics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
class IssuesService {
	
	private final IssuesRepository repository;
	
	void save(String user, String repo) {
		log.info("Saving user [{}] and repo [{}]", user, repo);
		repository.save(new Issue(user, repo));
	}

	long count() {
		return repository.count();
	}

	void deleteAll() {
		repository.deleteAll();
	}

	List<IssueDto> list() {
		List<IssueDto> issues = new ArrayList<>();
		repository.findAll().forEach(i -> 
			issues.add(new IssueDto(i.getUsername(), i.getRepository())));
		return issues;
	}
}
