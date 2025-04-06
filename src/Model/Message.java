package Model;

import java.io.Serializable;

public class Message implements Serializable
{
  private int clientID;
  private int vinylID;
  private String action;
  private String title;
  private String author;
  private int yearOfRelease;

  public Message(int clientID, int vinylID, String action)
  {
    this.clientID=clientID;
    this.vinylID=vinylID;
    this.action=action;
  }
  public Message(int clientID, String title, String author, int yearOfRelease, String action)
  {
    this.clientID=clientID;
    this.title=title;
    this.author=author;
    this.yearOfRelease=yearOfRelease;
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

  public String getTitle()
  {
    return title;
  }

  public String getAuthor()
  {
    return author;
  }

  public int getYearOfRelease()
  {
    return yearOfRelease;
  }
}
