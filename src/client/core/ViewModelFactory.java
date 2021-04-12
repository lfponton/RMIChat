package client.core;

import client.views.chat.ChatViewModel;
import client.views.username.UsernameViewModel;

public class ViewModelFactory
{
  private ChatViewModel chatViewModel;
  private UsernameViewModel usernameViewModel;

  public ViewModelFactory(ModelFactory modelFactory)
  {
    chatViewModel = new ChatViewModel(modelFactory.getDataModel());
    usernameViewModel = new UsernameViewModel(modelFactory.getDataModel());
  }

  public ChatViewModel getChatViewModel() {
    return chatViewModel;
  }

  public UsernameViewModel getUsernameViewModel()
  {
    return usernameViewModel;
  }
}
