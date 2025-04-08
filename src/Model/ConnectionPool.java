
package Model;

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


}
