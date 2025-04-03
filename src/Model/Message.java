package Model;

import java.io.Serializable;

public class Message implements Serializable
{
  private int clientID;
  private int vinylID;
  private String action;

  public Message(int clientID, int vinylID, String action)
  {
    this.clientID=clientID;
    this.vinylID=vinylID;
    this.action=action;
  }

  public int getClientID()
  {
    return clientID;
  }

  public int getVinylID()
  {
    return vinylID;
  }

  public String getAction()
  {
    return action;
  }
}
