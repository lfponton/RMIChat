package server.model;

import util.PropertyChangeSubject;

public interface MessageSender extends PropertyChangeSubject
{
  void sendMessage(String message);
}
