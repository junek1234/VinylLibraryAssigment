package Application;

import Model.Vinyl;
import Model.VinylLibrary;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

  public static void main(String[] args)
  {
    System.out.println("Starting server...");
    VinylLibrary vinylLibrary = new VinylLibrary();
    Vinyl vinyl1 = new Vinyl("Midnight Echoes", "The Velvet Reverie", 1978);
    vinylLibrary.addVinyl(vinyl1);
    try
    {
      ServerSocket welcomeSocket = new ServerSocket(2910);
      ConnectionPool connectionPool = new ConnectionPool();

      while (true)
      {
        Socket socket = welcomeSocket.accept();
        ServerConnection serverConnection = new ServerConnection(socket, connectionPool, vinylLibrary);
        connectionPool.add(serverConnection);
        System.out.println("Client connected");
        new Thread(serverConnection).start();
      }
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
}