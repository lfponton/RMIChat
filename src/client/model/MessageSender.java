package client.model;

import util.PropertyChangeSubject;

public interface MessageSender extends PropertyChangeSubject
{
  void sendMessage(String message);
  void createUser(String username);
  String getNumberOfConnections();
}
