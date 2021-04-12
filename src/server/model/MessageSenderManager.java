package server.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class MessageSenderManager implements MessageSender
{
  private PropertyChangeSupport support;
  private List<String> messages;


  public MessageSenderManager() {
    support = new PropertyChangeSupport(this);
    messages = new ArrayList<>();
  }

  @Override public String sendMessage(String message)
  {
    messages.add(message);
    String result = "";
    for (String m : messages)
    {
      result += m + "\n";
    }
    support.firePropertyChange("NewMessage", null, result);
    return result;
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
