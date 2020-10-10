import org.springframework.cloud.contract.spec.Contract

Contract.make { 
	request {
		method POST()
		url "/issues"
		body(
			username: "foo",
			repository: "spring-cloud/bar"
		)
	}
	response {
		status 200
	}
}