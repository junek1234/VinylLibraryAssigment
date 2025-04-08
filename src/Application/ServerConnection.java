
package Application;

import Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerConnection implements Runnable
{
  private final ObjectInputStream inFromClient;
  private final ObjectOutputStream outToClient;
  private final ConnectionPool connectionPool;
  private final VinylLibrary vinylLibrary;
  private final Log log;
  private final String clientIP;
  private final String serverIP;
  private final Map<String, ActionStrategy> strategies;

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


    strategies= new HashMap<>();
    strategies.put("Borrow", new BorrowStrategy());
    strategies.put("Reserve", new ReserveStrategy());
    strategies.put("Return", new ReturnStrategy());
    strategies.put("Cancel", new CancelStrategy());
    strategies.put("Remove", new RemoveStrategy());
    strategies.put("Add", new AddStrategy());
  }

  @Override
  public void run()
  {

    try {
      sendPacket(new ServerPacket(vinylLibrary.getVinyls(), "onlyVinyls"));
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
     String logString;
     if(packet.getMessage().equals("onlyVinyls"))
     {
       logString="Vinyl List has been sent";
     }
     else
     {
       logString="Vinyl List and Message ["+packet.getMessage()+"] have been sent";

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
  }


  public void actionIF(Message message) {
    ActionStrategy strategy = strategies.get(message.getAction());
    if (strategy != null) {
      strategy.execute(message, vinylLibrary);
    } else {
      System.out.println("error");
    }
  }

}
