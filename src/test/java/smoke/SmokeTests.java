package smoke;

import static org.awaitility.Awaitility.await;

import java.util.Map;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmokeTests.Config.class, webEnvironment = WebEnvironment.NONE)
public class SmokeTests {
	
	@Value("${application.url}") String applicationUrl;
	@Value("${stubrunner.url}") String stubRunnerUrl;
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void should_store_a_message_when_github_data_was_received_via_messaging() {
		final long countOfEntries = countGithubData();
		
		ResponseEntity<Map> response = triggerMessage();
		BDDAssertions.then(response.getStatusCode().is2xxSuccessful()).isTrue();
		
		await().until(() -> countGithubData() > countOfEntries);
		
	}
	
	private Long countGithubData() {
		return restTemplate.getForObject("http://"+applicationUrl+"/issues/count", Long.class);
	}
	
	private ResponseEntity<Map> triggerMessage(){
		return restTemplate.postForEntity("http://"+stubRunnerUrl+"/triggers/issue_created_v2", "", Map.class);
	}
	
	
	@Configuration
	@EnableAutoConfiguration
	static class Config {
		
	}

}
