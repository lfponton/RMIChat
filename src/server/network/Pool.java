package server.network;

import transferobjects.Message;

import java.util.ArrayList;
import java.util.List;

public class Pool
{
  private List<SocketHandler> connections = new ArrayList<>();

  public void addConnection(SocketHandler handler, Message message)
  {
    boolean flag = true;
    for (SocketHandler c : connections)
    {
      if (c.getUsername().equals(message.getUsername()))
      {
        flag = false;
        break;
      }
    }
    if (flag)
    {
      connections.add(handler);
    }
  }

  public int size()
  {
    return connections.size();
  }
}
