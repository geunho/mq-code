package dev.geunho.mq_kafka;

import java.util.UUID;

public class Message {
  private final UUID id;
  private final Object body;

  public Message(Object contents) {
    id = UUID.randomUUID();
    body = contents;
  }

  public String getId() {
    return id.toString();
  }

  public Object getContents() {
    return body;
  }

}
