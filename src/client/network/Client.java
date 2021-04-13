package client.network;

import util.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject
{
  void sendMessage(String str);
  void startClient();
  void createUser(String username);
  String getNumberOfConnections();
}
