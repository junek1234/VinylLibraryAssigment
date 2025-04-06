
package Application;

import Model.ServerPacket;
import Model.Vinyl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool
{
  private final List<ServerConnection> connections;

  public ConnectionPool()
  {
    this.connections = new ArrayList<>();
  }

  public void add(ServerConnection serverConnection)
  {
    connections.add(serverConnection);
  }

  public void broadcastPacket(ServerPacket packet) {
    for (ServerConnection connection : connections) {
      try {
        connection.sendPacket(packet);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
//  public void broadcastList(List<Vinyl> vinyls) throws IOException
//  {
//    for (ServerConnection connection : connections)
//    {
//      connection.updateList(vinyls);
//
//    }
//  }
//  public void broadcastMessage( String message) throws IOException
//  {
//    for (ServerConnection connection : connections)
//    {
//
//      connection.sendMessage(message);
//    }
//  }

}
