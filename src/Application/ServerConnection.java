
package Application;

import Model.Vinyl;
import Model.VinylLibrary;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
    while(true)
    {
      try
      {
        String message = (String) inFromClient.readObject();
        System.out.println("Received: " + message);
        if(message.equals("Reserve"))
        {
          vinylLibrary.reserveVinyl(1,0);
        }
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
}
