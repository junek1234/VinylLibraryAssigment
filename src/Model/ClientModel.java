package Model;

import Application.ClientConnection;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientModel implements PropertyChangeSubject
{
  private List<Vinyl> vinyls;
  private int clientID;
  private Message message;
  private PropertyChangeSupport support;
  private ClientConnection clientConnection;

  public ClientModel(int clientID) throws IOException
  {
    Socket socket = new Socket("localhost", 2910);
    clientConnection = new ClientConnection(socket, this);
    new Thread(clientConnection).start();

    this.clientID=clientID;
    vinyls= new ArrayList<>();
    support = new PropertyChangeSupport(this);
    //      while(true)
    //      {
    //        System.out.println("Vinyl: ");
    //        int vinyl = scanner.nextInt();
    //        scanner.nextLine();
    //        System.out.println("action: ");
    //        String stringToSend = scanner.nextLine();
    //        clientConnection.send(new Message(clientID, vinyl, stringToSend ));
    //      }
  }
  public List<Vinyl> getVinyls()
  {
    return vinyls;
  }

  public void setVinyls(List<Vinyl> vinyls)
  {
    this.vinyls = vinyls;
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
//    firePropertyChange();
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
    //    firePropertyChange();
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
    //    firePropertyChange();
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
    //    firePropertyChange();
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
  public void firePropertyChange()
  {
    support.firePropertyChange("Vinyls",null, vinyls);
  }
}
