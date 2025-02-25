package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class VinylLibrary implements PropertyChangeSubject
{
  private PropertyChangeSupport support;

  private List<Vinyl> vinyls;
  public VinylLibrary()
  {
    vinyls = new ArrayList<>();
    support = new PropertyChangeSupport(this);
  }
  public void addVinyl(Vinyl vinyl)
  {
    vinyls.add(vinyl);
  }
  public void removeVinyl(int vinylID)
  {
    Thread thread = new Thread(()->{
      vinyls.get(vinylID).setRemoveFlag();
      firePropertyChange();
      while(!vinyls.get(vinylID).currentState.getClass().getSimpleName().equals("AvailableState"))
      {
        try
        {
          Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
          throw new RuntimeException(e);
        }
      }
      vinyls.remove(vinylID);
      firePropertyChange();
    });
    thread.setDaemon(true);
    thread.start();

  }
  public void borrowVinyl(int clientID, int vinylID)
  {
    vinyls.get(vinylID).onBorrow(clientID);
    firePropertyChange();
  }
  public void reserveVinyl(int clientID, int vinylID)
  {
    vinyls.get(vinylID).onReserve(clientID);
    firePropertyChange();
  }
  public void returnVinyl(int clientID, int vinylID)
  {
    vinyls.get(vinylID).onReturn(clientID);
    firePropertyChange();
  }
  public void cancelReservation(int clientID, int vinylID)
  {
    vinyls.get(vinylID).onCancel(clientID);
    firePropertyChange();
  }

  public List<Vinyl> getVinyls()
  {
    return vinyls;
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
