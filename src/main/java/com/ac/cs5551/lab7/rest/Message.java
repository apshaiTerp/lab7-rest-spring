package com.ac.cs5551.lab7.rest;

/**
 * @author ac010168
 *
 */
public class Message {
  
  private String message;
  
  public Message() {
    setMessage(null);
  }
  
  public Message(String message) {
    this.setMessage(message);
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
