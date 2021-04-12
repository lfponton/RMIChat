package client.network;

import util.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject
{
  String sendMessage(String str);
  void startClient();
  void createUser(String username);
  String getNumberOfConnections();
}
