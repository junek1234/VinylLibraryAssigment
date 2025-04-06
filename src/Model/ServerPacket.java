package Model;

import java.io.Serializable;
import java.util.List;

public class ServerPacket implements Serializable
{
  private List<Vinyl> vinyls;
  private String message;

  public ServerPacket(List<Vinyl> vinyls, String message) {
    this.vinyls = vinyls;
    this.message = message;
  }

  public List<Vinyl> getVinyls() {
    return vinyls;
  }

  public String getMessage() {
    return message;
  }
}
