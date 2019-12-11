package dev.geunho.mq_kafka;

import java.util.UUID;

public class Message {
  private final UUID id;
  private final String body;

  public Message(String contents) {
    id = UUID.randomUUID();
    body = contents;
  }

  public String getId() {
    return id.toString();
  }

  public String getContents() {
    return body;
  }

}
