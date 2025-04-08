
package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection implements Runnable
{

  private final ObjectOutputStream outToServer;
  private final ObjectInputStream inFromServer;
  private final ClientModel clientModel;

  public ClientConnection(Socket socket, ClientModel clientModel) throws IOException
  {
    outToServer = new ObjectOutputStream(socket.getOutputStream());
    inFromServer = new ObjectInputStream(socket.getInputStream());
    this.clientModel = clientModel;
  }

  @Override
  public void run()
  {
    try {
      while (true) {
        Object obj = inFromServer.readObject();
        ServerPacket serverPacket= (ServerPacket)obj;

          if (serverPacket.getVinyls() != null) {
            clientModel.setVinyls(serverPacket.getVinyls());
          }
          if (serverPacket.getMessage() != null && !serverPacket.getMessage().equals("onlyVinyls")) {
            clientModel.setMessage(serverPacket.getMessage());
          }

      }
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

  }

  public void send(Message message) throws IOException
  {
    outToServer.writeObject(message);
  }
}
