package co.divagar.terminal.client.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.divagar.model.request.TerminalPayload;
import co.divagar.terminal.client.service.TerminalService;

@RestController
@RequestMapping(value = "v1/terminal/client", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TerminalController {

  private final TerminalService terminalService;

  public TerminalController(TerminalService terminalService) {
    this.terminalService = terminalService;
  }

  @GetMapping
  public ResponseEntity<TerminalPayload> processRequest(@RequestBody(required = false) Map<String, Object> payload) {
	  
	  final TerminalPayload terminalPayload = terminalService.processRequest(payload);
    
    return new ResponseEntity<TerminalPayload>(terminalPayload,HttpStatus.OK);
  }
}
