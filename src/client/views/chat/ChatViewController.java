package client.views.chat;

import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ChatViewController
{
  @FXML private TextField messageField;
  @FXML private TextArea messagesArea;
  @FXML private Label numberOfConnections;
  private ChatViewModel viewModel;
  private ViewHandler viewHandler;
  public void init(ChatViewModel viewModel, ViewHandler viewHandler)
  {
    this.viewModel = viewModel;
    this.viewHandler = viewHandler;

    messageField.textProperty().bindBidirectional(viewModel.getMessage());
    messagesArea.textProperty().bind(viewModel.getMessages());

  }

  public void sendMessageButton(ActionEvent evt)
  {
    viewModel.sendMessage();
  }

  public void numberOfConnections(ActionEvent evt) {
    numberOfConnections.setText(viewModel.numberOfConnections());
  }
}
