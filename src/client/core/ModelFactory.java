package client.core;

import client.model.MessageSender;
import client.model.MessageSenderManager;

public class ModelFactory
{
  private MessageSender model;
  private ClientFactory clientFactory;

  public ModelFactory(ClientFactory clientFactory)
  {
    this.clientFactory = clientFactory;
  }

  public MessageSender getDataModel() {
    if (model == null) {
      model = new MessageSenderManager(clientFactory.getClient());
    }
    return model;
  }
}
