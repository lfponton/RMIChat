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
  private ObjectOutputStream outToServer;
  private ObjectInputStream inFromServer;
  private Socket socket;

  public SocketClient() {
    support = new PropertyChangeSupport(this);
    username = "";
    try
    {
      socket = new Socket("localhost", 1234);
      outToServer = new ObjectOutputStream(socket.getOutputStream());
      inFromServer = new ObjectInputStream(socket.getInputStream());

      new Thread(this::listenToServer).start();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void startClient()
  {
    try
    {
      Socket socket = new Socket("localhost", 1234);
      this.outToServer = new ObjectOutputStream(socket.getOutputStream());
      this.inFromServer = new ObjectInputStream(socket.getInputStream());

      Thread t = new Thread(this::listenToServer);
      t.start();
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

  private void listenToServer()
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

  public void requestMessage(String arg, String type)
      throws IOException, ClassNotFoundException
  {
    Socket socket = new Socket("localhost", 1234);
    ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
    outToServer.writeObject(new Request(type, new Message(username, arg)));
  }


  @Override public void sendMessage(String str)
  {
    try
    {
      requestMessage(str, "SendMessage");
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
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
