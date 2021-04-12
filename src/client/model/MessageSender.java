package client.model;

import util.PropertyChangeSubject;

public interface MessageSender extends PropertyChangeSubject
{
  String sendMessage(String message);
  void createUser(String username);
  String getNumberOfConnections();
}
