package com.example.githubanalytics;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GithubAnalyticsApplication.class, webEnvironment = WebEnvironment.NONE)
@AutoConfigureStubRunner(
		ids = "com.example.github:github-webhook",
		repositoryRoot = "${REPO_WITH_BINARIES:http://artifactory:8081/artifactory/libs-release-local}",
		stubsMode = StubRunnerProperties.StubsMode.REMOTE)
public class AcceptanceTests {
	
	@Autowired StubTrigger stubTrigger;
	@Autowired IssuesRepository repository;
	
	@Test
	public void shoud_store_a_new_issue() {
		BDDAssertions.assertThat(repository.count()).isEqualTo(0);
		
		stubTrigger.trigger("issue_created_v2");
		
		BDDAssertions.assertThat(repository.count()).isEqualTo(1);
	}
	
}
