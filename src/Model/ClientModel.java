package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientModel implements PropertyChangeSubject
{
  private List<Vinyl> vinyls;
  private PropertyChangeSupport support;
  private ClientConnection clientConnection;
  private String message;

  public ClientModel() throws IOException
  {
    Socket socket = new Socket("192.168.1.34", 2910);
    clientConnection = new ClientConnection(socket, this);
    new Thread(clientConnection).start();

    vinyls= new ArrayList<>();
    support = new PropertyChangeSupport(this);

  }


  public List<Vinyl> getVinyls()
  {
    return vinyls;
  }

  public void setVinyls(List<Vinyl> vinyls)
  {
    this.vinyls = vinyls;
    firePropertyChangeVinylList();
  }
  public void setMessage(String message)
  {
    this.message=message;
    firePropertyChangeNewMessage();
  }

  public void borrowVinyl(int clientID, int vinylID)
  {
    try
    {
      clientConnection.send(new Message(clientID, vinylID, "Borrow"));
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }

  }
  public void reserveVinyl(int clientID, int vinylID)
  {
    try
    {
      clientConnection.send(new Message(clientID, vinylID, "Reserve"));
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }

  }
  public void returnVinyl(int clientID, int vinylID)
  {
    try
    {
      clientConnection.send(new Message(clientID, vinylID, "Return"));
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }

  }
  public void cancelReservation(int clientID, int vinylID)
  {
    try
    {
      clientConnection.send(new Message(clientID, vinylID, "Cancel"));
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }

  }
  public void removeVinyl(int clientID, int vinylID)
  {
    try
    {
      clientConnection.send(new Message(clientID, vinylID, "Remove"));
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  public void addVinyl(int clientID, String title, String artist, int releaseYear)
  {
    try
    {
      clientConnection.send(new Message(clientID, title, artist,releaseYear, "Add"));
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name, listener);
  }
  public void firePropertyChangeVinylList()
  {
    support.firePropertyChange("Vinyls",null, vinyls);
  }
  public void firePropertyChangeNewMessage()
  {
    support.firePropertyChange("Message", null, message);
  }

  public String getMessage()
  {
    return message;
  }


}
