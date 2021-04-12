package server;

import server.model.MessageSenderManager;
import server.network.SocketServer;

public class RunServer
{
  public static void main(String[] args)
  {
    SocketServer server = new SocketServer(new MessageSenderManager());
    server.startServer();
  }

}
