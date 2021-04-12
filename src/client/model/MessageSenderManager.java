package client.model;

import client.network.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MessageSenderManager implements MessageSender
{
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private Client client;

  public MessageSenderManager(Client client) {
    this.client = client;
    client.startClient();
    client.addPropertyChangeListener("NewMessage", this::onNewMessage);
  }

  private void onNewMessage(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
  }

  @Override public String sendMessage(String message)
  {
    return client.sendMessage(message);
  }

  @Override public void createUser(String username)
  {
    client.createUser(username);
  }

  @Override public String getNumberOfConnections()
  {
    return client.getNumberOfConnections();
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

}
