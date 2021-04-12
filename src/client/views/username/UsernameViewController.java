package client.views.username;

import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UsernameViewController
{
  @FXML private TextField usernameField;
  private UsernameViewModel usernameViewModel;
  private ViewHandler viewHandler;

  public void init(UsernameViewModel usernameViewModel, ViewHandler viewHandler) {
    this.usernameViewModel = usernameViewModel;
    this.viewHandler = viewHandler;
    usernameViewModel.createUser(usernameField.getText());
  }

  public void enterUsernameButton(ActionEvent evt)
  {
    usernameViewModel.createUser(usernameField.getText());
    viewHandler.openChatView();
  }
}
