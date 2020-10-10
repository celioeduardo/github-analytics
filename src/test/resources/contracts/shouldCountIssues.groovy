import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpStatus

Contract.make { 
	request {
		method GET()
		url "/issues/count"
	}
	response {
		status HttpStatus.OK.value()
		body 5
	}
}