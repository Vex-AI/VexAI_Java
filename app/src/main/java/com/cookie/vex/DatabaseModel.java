package com.cookie.vex;

public class DatabaseModel {
  private String msg, id, answer;

  public DatabaseModel(String msg, String answer, String id) {
    this.msg = msg;
    this.id = id;
    this.answer = answer;
  }

  public void setMessage(String msg) {
    this.msg = msg;
  }

  public String getMessage() {
    return this.msg;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getAnswer() {
    return this.answer;
  }

  public void setID(String id) {
    this.id = id;
  }

  public String getID() {
    return this.id;
  }

}