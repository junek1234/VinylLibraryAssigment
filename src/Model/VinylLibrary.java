package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VinylLibrary implements PropertyChangeSubject
{
  private PropertyChangeSupport support;

  private List<Vinyl> vinyls;
  public VinylLibrary()
  {
    vinyls = new ArrayList<>();
    support = new PropertyChangeSupport(this);
  }
  public synchronized void addVinyl(Vinyl vinyl)
  {
    vinyls.add(vinyl);
    firePropertyChangeAdd(vinyl.getTitle());
  }
  public synchronized void removeVinyl(int vinylID)
  {

    Thread thread = new Thread(()->{
      Vinyl vinylToRemove = vinyls.get(vinylID);
      vinylToRemove.setRemoveFlag();
      firePropertyChangeToBeRemoved(vinylToRemove.getTitle());

      while(!vinylToRemove.currentState.getClass().getSimpleName().equals("AvailableState"))
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

      vinyls.removeIf(v -> v == vinylToRemove);
      firePropertyChangeRemoved(vinylToRemove.getTitle());

    });
    thread.setDaemon(true);
    thread.start();

  }
  public synchronized void borrowVinyl(int clientID, int vinylID)
  {
    String oldStatus= vinyls.get(vinylID).getStatus();
    vinyls.get(vinylID).onBorrow(clientID);
    if(!vinyls.get(vinylID).getStatus().equals(oldStatus))
    {
      firePropertyChangeStatusChange(vinyls.get(vinylID).getTitle()+" has been borrowed");
    }

  }
  public synchronized void reserveVinyl(int clientID, int vinylID)
  {
    String oldStatus= vinyls.get(vinylID).getStatus();
    vinyls.get(vinylID).onReserve(clientID);
    if(!vinyls.get(vinylID).getStatus().equals(oldStatus))
    {
      firePropertyChangeStatusChange(vinyls.get(vinylID).getTitle()+" has been reserved");
    }

  }
  public synchronized void returnVinyl(int clientID, int vinylID)
  {
    String oldStatus= vinyls.get(vinylID).getStatus();
    vinyls.get(vinylID).onReturn(clientID);
    if(!vinyls.get(vinylID).getStatus().equals(oldStatus))
    {
      firePropertyChangeStatusChange(vinyls.get(vinylID).getTitle()+" has been returned");
    }

  }
  public synchronized void cancelReservation(int clientID, int vinylID)
  {
    String oldStatus= vinyls.get(vinylID).getStatus();
    vinyls.get(vinylID).onCancel(clientID);
    if(!vinyls.get(vinylID).getStatus().equals(oldStatus))
    {
      firePropertyChangeStatusChange(vinyls.get(vinylID).getTitle()+" has been cancelled");
    }

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
  public void firePropertyChangeRemoved(String vinylTitle)
  {
    support.firePropertyChange("Removed",null, vinylTitle);
  }
  public void firePropertyChangeToBeRemoved(String vinylTitle)
  {
    support.firePropertyChange("ToBeRemoved",null, vinylTitle);
  }
  public void firePropertyChangeStatusChange(String vinylTitle)
  {
    support.firePropertyChange("Status",null, vinylTitle);
  }
  public void firePropertyChangeAdd(String vinylTitle)
  {
    support.firePropertyChange("Add",null, vinylTitle);
  }


}
