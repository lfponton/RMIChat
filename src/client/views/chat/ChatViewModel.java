package client.views.chat;

import client.model.MessageSender;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class ChatViewModel
{
  private MessageSender messageSender;
  private StringProperty message, messages;
  private String numberOfConnections;

  public ChatViewModel(MessageSender messageSender)
  {
    this.messageSender = messageSender;
    messages = new SimpleStringProperty();
    message = new SimpleStringProperty();
    numberOfConnections = "1";
    messageSender.addPropertyChangeListener("NewMessage", this::onNewMessage);
  }

  private void onNewMessage(PropertyChangeEvent evt)
  {
    messages.setValue((String) evt.getNewValue());
  }

  public void sendMessage()
  {
    String input = message.get();
    messageSender.sendMessage(input);
  }

  public String numberOfConnections()
  {
    return messageSender.getNumberOfConnections();
  }

  public StringProperty getMessages()
  {
    return messages;
  }

  public StringProperty getMessage()
  {
    return message;
  }


}
