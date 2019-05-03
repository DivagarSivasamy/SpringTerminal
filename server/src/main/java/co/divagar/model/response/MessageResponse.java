package co.divagar.model.response;

import java.io.Serializable;

public class MessageResponse {

  private String message;

  public MessageResponse() {
    //Required Empty Constructor for (de)serialization
  }


public MessageResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
