
package Application;

import Model.Message;
import Model.Vinyl;
import Model.VinylLibrary;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ServerConnection implements Runnable
{
  private final ObjectInputStream inFromClient;
  private final ObjectOutputStream outToClient;
  private final ConnectionPool connectionPool;
  private final VinylLibrary vinylLibrary;

  public ServerConnection(Socket connectionSocket, ConnectionPool connectionPool, VinylLibrary vinylLibrary) throws IOException
  {
    inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
    outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
    this.connectionPool = connectionPool;
    this.vinylLibrary=vinylLibrary;
  }

  @Override
  public void run()
  {
    try
    {
      updateList(vinylLibrary.getVinyls());
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    while(true)
    {
      try
      {
        Message message = (Message) inFromClient.readObject();
//        System.out.println("Received: " + message);
        actionIF(message);
        connectionPool.broadcast(vinylLibrary.getVinyls().get(0).getStatus());
      }
      catch (IOException | ClassNotFoundException e)
      {
        throw new RuntimeException(e);
      }
    }
  }

  public void send(String message) throws IOException
  {
    outToClient.writeObject(message);
  }
  public void updateList(List<Vinyl> vinylList) throws IOException
  {
    outToClient.writeObject(vinylList);
  }
  public void borrowVinyl(int clientID, int vinylID)
  {
    vinylLibrary.borrowVinyl(clientID, vinylID);
  }
  public void reserveVinyl(int clientID, int vinylID)
  {
    vinylLibrary.reserveVinyl(clientID, vinylID);
  }
  public void returnVinyl(int clientID, int vinylID)
  {
    vinylLibrary.returnVinyl(clientID, vinylID);
  }
  public void cancelReservation(int clientID, int vinylID)
  {
    vinylLibrary.cancelReservation(clientID, vinylID);
  }
  public void actionIF(Message message)
  {
    if(message.getAction().equals("Borrow"))
    {
      borrowVinyl(message.getClientID(), message.getVinylID());
    }
    else if(message.getAction().equals("Reserve"))
    {
      reserveVinyl(message.getClientID(), message.getVinylID());
    }
    else if(message.getAction().equals("Return"))
    {
      returnVinyl(message.getClientID(), message.getVinylID());
    }
    else if(message.getAction().equals("Cancel"))
    {
      cancelReservation(message.getClientID(), message.getVinylID());
    }
    else
    {
      try{
        send("error");
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }

    }
  }
}
