package server.model;

import util.PropertyChangeSubject;

public interface MessageSender extends PropertyChangeSubject
{
  String sendMessage(String message);
}
