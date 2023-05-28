package com.cookie.vex;

public class MessageListModel {
  private String msg, id, hour, key;

  public MessageListModel(String msg, String id, String hour, String key) {
    this.msg = msg;
    this.id = id;
    this.hour = hour;
    this.key = key;
  }

  public void setMessage(String msg) {
    this.msg = msg;
  }

  public String getMessage() {
    return this.msg;
  }

  public void setID(String id) {
    this.id = id;
  }

  public String getID() {
    return this.id;
  }

  public void setHour(String hour) {
    this.hour = hour;
  }

  public String getHour() {
    return this.hour;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return this.key;
  }
}