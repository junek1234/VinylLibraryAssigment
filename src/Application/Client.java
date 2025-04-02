package Application;

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

      while(true)
      {
        System.out.println("Choice: ");
        String stringToSend = scanner.nextLine();
        clientConnection.send(stringToSend);
      }
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
}