package client.network;

import transferobjects.Message;
import transferobjects.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient implements Client
{
  private PropertyChangeSupport support;
  private String username;

  public SocketClient() {
    support = new PropertyChangeSupport(this);
    username = "";
  }

  @Override public void startClient()
  {
    try
    {
      Socket socket = new Socket("localhost", 1234);
      ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

      new Thread(() -> listenToServer(outToServer, inFromServer)).start();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void createUser(String username)
  {
    this.username = username;
  }


  private void listenToServer(ObjectOutputStream outToServer, ObjectInputStream inFromServer)
  {
    try
    {
      outToServer.writeObject(new Request("Listener", null));
      while (true) {
        Request request = (Request) inFromServer.readObject();
        support.firePropertyChange(request.getType(), null, request.getArgument());
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private Request requestMessage(String arg, String type)
      throws IOException, ClassNotFoundException
  {
    Socket socket = new Socket("localhost", 1234);
    ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
    outToServer.writeObject(new Request(type, new Message(username, arg)));
    return (Request) inFromServer.readObject();
  }


  @Override public String sendMessage(String str)
  {
    Message message = new Message(username, str);
    try
    {
      Request response = requestMessage(str, "SendMessage");
      return (String) response.getArgument();
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    return message.toString();
  }

  private Request requestConnections(String arg, String type)
      throws IOException, ClassNotFoundException
  {
    Socket socket = new Socket("localhost", 1234);
    ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
    outToServer.writeObject(new Request(type, arg));
    return (Request) inFromServer.readObject();
  }
  @Override public String getNumberOfConnections()
  {
      try
      {
        Request response = requestConnections("1", "NumberOfConnections");
        return (String) response.getArgument();
      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
      };
    return "1";
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
