package com.example.githubanalytics;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
class GithubDataListener {

	private final IssuesService issuesService;
	
	@StreamListener(Sink.INPUT)
	void listen(GithubDatum githubDatum) {
		issuesService.save(githubDatum.getUsername(), githubDatum.getRepository());
	}
	
}
