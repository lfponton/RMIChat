package client.core;

import client.views.chat.ChatViewController;
import client.views.username.UsernameViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private Scene chatScene;
  private Scene userScene;
  private ViewModelFactory viewModelFactory;

  public ViewHandler(Stage stage, ViewModelFactory viewModelFactory)
  {
    this.stage = stage;
    this.viewModelFactory = viewModelFactory;
  }

  public void start()
  {
    openUserView();
    stage.show();
  }

  private void openUserView()
  {
    FXMLLoader loader = new FXMLLoader();
    if (userScene == null)
    {
      Parent root = getRootByPath("../views/username/UsernameView.fxml", loader);
      UsernameViewController controller = loader.getController();
      controller.init(viewModelFactory.getUsernameViewModel(), this);
      userScene = new Scene(root);
    }
    stage.setTitle("Username");
    stage.setScene(userScene);
  }

  public void openChatView()
  {
    FXMLLoader loader = new FXMLLoader();
    if (chatScene == null)
    {
      Parent root = getRootByPath("../views/chat/ChatView.fxml", loader);
      ChatViewController controller = loader.getController();
      controller.init(viewModelFactory.getChatViewModel(), this);
      chatScene = new Scene(root);
    }
    stage.setTitle("Chat");
    stage.setScene(chatScene);
  }

  private Parent getRootByPath(String path, FXMLLoader loader)
  {
    loader.setLocation(getClass().getResource(path));
    Parent root = null;
    try
    {
      root = loader.load();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return root;
  }
}
