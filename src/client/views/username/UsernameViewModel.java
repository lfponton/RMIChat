package client.views.username;

import client.model.MessageSender;

public class UsernameViewModel
{
  private MessageSender messageSender;

  public UsernameViewModel(MessageSender messageSender) {
    this.messageSender = messageSender;
  }

  public void createUser(String username) {
    messageSender.createUser(username);
  }
}
