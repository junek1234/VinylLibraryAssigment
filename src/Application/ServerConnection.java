
package Application;

import Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerConnection implements Runnable
{
  private final ObjectInputStream inFromClient;
  private final ObjectOutputStream outToClient;
  private final ConnectionPool connectionPool;
  private final VinylLibrary vinylLibrary;
  private final Log log;
  private final String clientIP;
  private final String serverIP;

  public ServerConnection(Socket connectionSocket, ConnectionPool connectionPool, VinylLibrary vinylLibrary) throws IOException
  {
    inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
    outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
    this.connectionPool = connectionPool;
    this.vinylLibrary=vinylLibrary;
    log = Log.getInstance();
    //Storing clientAddress
    InetAddress clientAddress = connectionSocket.getInetAddress();
    clientIP = clientAddress.getHostAddress();
    //Storing serverAddress
    InetAddress serverAddress = connectionSocket.getLocalAddress();
    serverIP = serverAddress.getHostAddress();
  }

  @Override
  public void run()
  {

    try {
      sendPacket(new ServerPacket(vinylLibrary.getVinyls(), "nothing"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    while(true)
    {
      try
      {

        Message message = (Message) inFromClient.readObject();
        logFromClient(message);



        actionIF(message);


        connectionPool.broadcastPacket(new ServerPacket(vinylLibrary.getVinyls(), "nothing"));


      }
      catch (IOException | ClassNotFoundException e)
      {
        throw new RuntimeException(e);
      }
    }
  }


  public synchronized void sendPacket(ServerPacket packet) throws IOException {
    outToClient.reset();
    outToClient.writeObject(packet);
    logFromServer(packet);
   }
   public void logFromServer(ServerPacket packet)
   {
     String logString="Message ["+packet.getMessage()+"] has been sent";
     if(packet.getMessage().equals("nothing"))
     {
       logString="Vinyl List has been sent";
     }
     log.add(logString,serverIP,clientIP);
   }
  public void logFromClient(Message message)
  {
    String logString;
    if(message.getAction().equals("Add"))
    {
      logString="Client ID-"+message.getClientID()+" requested adding Vinyl ["+message.getTitle()+", "+message.getAuthor()+", "+message.getYearOfRelease()+"]";
    }
    else
    {
      logString="Client ID-"+message.getClientID()+" requested to "+message.getAction().toLowerCase()+" Vinyl ID-"+message.getVinylID();
    }
    log.add(logString,clientIP,serverIP);
  }
  public void sendMessage(String message) throws IOException
  {
    ServerPacket packet = new ServerPacket(vinylLibrary.getVinyls(), message);
    connectionPool.broadcastPacket(packet);
    logFromServer(packet);
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
  public void removeVinyl(int clientID, int vinylID)
  {
    vinylLibrary.removeVinyl(vinylID);
  }
  public void addVinyl(int clientID, String title, String artist, int releaseYear)
  {
    vinylLibrary.addVinyl(new Vinyl(title, artist, releaseYear));
  }
  public void actionIF(Message message)
  {
    //i should use strategy pattern here maybe


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
    else if (message.getAction().equals("Remove"))
    {
      removeVinyl(message.getClientID(), message.getVinylID());
    }
    else if (message.getAction().equals("Add"))
    {
      addVinyl(message.getClientID(), message.getTitle(), message.getAuthor(),
          message.getYearOfRelease());
    }
    else
    {
        System.out.println("error");
    }


  }

}
