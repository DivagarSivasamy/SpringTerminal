package co.divagar.terminal.client.service;

import java.util.Map;

import co.divagar.model.request.TerminalPayload;

public interface TerminalService {

  TerminalPayload processRequest(Map<String, Object> payload);
  String processRequestTest(Map<String, Object> payload);
}
