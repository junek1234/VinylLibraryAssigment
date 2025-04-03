package Model;

import java.util.ArrayList;
import java.util.List;

public class ClientModel
{
  private List<Object> vinyls;
  private int clientID;
  private Message message;

  public ClientModel(int clientID)
  {
    this.clientID=clientID;
    vinyls= new ArrayList<>();
  }

}
