package co.divagar.terminal.client.service.impl;

import java.util.Map;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import co.divagar.model.request.TerminalPayload;
import co.divagar.model.response.TerminalResponse;
import co.divagar.terminal.client.service.TerminalService;
import co.divagar.terminal.client.util.SequenceGenerator;

@Service
public class TerminalServiceImpl implements TerminalService {

  private static final String SERVER_TERMINAL_URL = "/v1/terminal/server";

  private final SequenceGenerator sequenceGenerator;

  private final RestTemplate restTemplate;

  private final RetryTemplate retryTemplate;

  public TerminalServiceImpl(SequenceGenerator sequenceGenerator, RestTemplate restTemplate, RetryTemplate retryTemplate) {
    this.sequenceGenerator = sequenceGenerator;
    this.restTemplate = restTemplate;
    this.retryTemplate = retryTemplate;
  }

  @Override
  public TerminalPayload processRequest(Map<String, Object> payload) {
    final TerminalResponse terminalResponse = retryTemplate.execute(
            (RetryCallback<TerminalResponse, RestClientException>) context ->
                    restTemplate.getForObject(SERVER_TERMINAL_URL + "/availableTerminalId", TerminalResponse.class));

    final int sequenceId = sequenceGenerator.getNext();
    final long timestamp = System.currentTimeMillis();
    final TerminalPayload terminalPayload = new TerminalPayload(terminalResponse.getTerminalId(), sequenceId, timestamp);

    final String response= retryTemplate.execute(
            (RetryCallback<String, RestClientException>) context ->
                   restTemplate.postForObject(SERVER_TERMINAL_URL, terminalPayload, String.class));
   final String message = "success";
  
   return terminalPayload;
  }
  public String processRequestTest(Map<String, Object> payload) {
	    final TerminalResponse terminalResponse = retryTemplate.execute(
	            (RetryCallback<TerminalResponse, RestClientException>) context ->
	                    restTemplate.getForObject(SERVER_TERMINAL_URL + "/availableTerminalId", TerminalResponse.class));

	    final int sequenceId = sequenceGenerator.getNext();
	    final long timestamp = System.currentTimeMillis();
	    final TerminalPayload terminalPayload = new TerminalPayload(terminalResponse.getTerminalId(), sequenceId, timestamp);

	    final String response= retryTemplate.execute(
	            (RetryCallback<String, RestClientException>) context ->
	                   restTemplate.postForObject(SERVER_TERMINAL_URL, terminalPayload, String.class));
	    
	    return response;
	  }
}
