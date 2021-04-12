package server.network;

import server.model.MessageSender;
import transferobjects.Message;
import transferobjects.Request;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler implements Runnable
{
  private Socket socket;
  private MessageSender messageSender;
  private Pool pool;
  private String username;

  private ObjectOutputStream outToClient;
  private ObjectInputStream inFromClient;

  public SocketHandler(Socket socket, MessageSender messageSender, Pool pool)
  {
    this.socket = socket;
    this.messageSender = messageSender;
    this.pool = pool;

    try
    {
      outToClient = new ObjectOutputStream(socket.getOutputStream());
      inFromClient = new ObjectInputStream(socket.getInputStream());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    try
    {
      System.out.println("Server handler accepting requests.");
      Request request = (Request) inFromClient.readObject();
      if ("Listener".equals(request.getType())) {
        messageSender.addPropertyChangeListener("NewMessage", this::onNewMessage);

      }
      else if ("SendMessage".equals(request.getType())) {
        Message message = (Message) request.getArgument();
        this.username = message.getUsername();
        pool.addConnection(this, message);
        String result = messageSender.sendMessage(message.toString());
        outToClient.writeObject(new Request(("NewMessage"), result));
      }
      else if ("NumberOfConnections".equals(request.getType())) {
        String numberOfConnections = String.valueOf(pool.size());
        outToClient.writeObject(new Request(("NumberOfConnections"), numberOfConnections));
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private void onNewMessage(PropertyChangeEvent evt)
  {
    try
    {
      outToClient.writeObject(new Request(evt.getPropertyName(), evt.getNewValue()));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public String getUsername()
  {
    return username;
  }
}
