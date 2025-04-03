package Application;

import Model.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    try
    {
      Socket socket = new Socket("localhost", 2910);
      ClientConnection clientConnection = new ClientConnection(socket);
      new Thread(clientConnection).start();
      System.out.println("ID: ");
      int clientID= scanner.nextInt();
      while(true)
      {
        System.out.println("Vinyl: ");
        int vinyl = scanner.nextInt();
        scanner.nextLine();
        System.out.println("action: ");
        String stringToSend = scanner.nextLine();
        clientConnection.send(new Message(clientID, vinyl, stringToSend ));
      }
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
}