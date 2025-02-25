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
    //add logic that vinyls can be removed only when state = available
    //otherwise they are marked as to be removed and wait until they are returned
    //as marked they cannot be reserved
    vinyls.remove(vinylID);
    support.firePropertyChange("Vinyls",null, vinyls);
  }
  public void borrowVinyl(int clientID, int vinylID)
  {
    vinyls.get(vinylID).onBorrow(clientID);
    support.firePropertyChange("Vinyls",null, vinyls);
  }
  public void reserveVinyl(int clientID, int vinylID)
  {
    vinyls.get(vinylID).onReserve(clientID);
    support.firePropertyChange("Vinyls",null, vinyls);
  }
  public void returnVinyl(int clientID, int vinylID)
  {
    vinyls.get(vinylID).onReturn(clientID);
    support.firePropertyChange("Vinyls",null, vinyls);
  }
  public String getVinylData(int vinylID)
  {
    return vinyls.get(vinylID).getStateName();
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
}
