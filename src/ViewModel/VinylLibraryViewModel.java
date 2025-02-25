package ViewModel;

import Model.Vinyl;
import Model.VinylLibrary;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class VinylLibraryViewModel
{
  private VinylLibrary vinylLibrary;
  private ObservableList<Vinyl> vinyls;
  private IntegerProperty userID;

  public VinylLibraryViewModel(VinylLibrary vinylLibrary)
  {
    this.vinylLibrary = vinylLibrary;
    vinyls= FXCollections.observableArrayList();
    vinylLibrary.addPropertyChangeListener(this::update);
    userID = new SimpleIntegerProperty();
  }

  private void update(PropertyChangeEvent event)
  {
    vinyls.clear();

    List<Vinyl> newVinyls =(List<Vinyl>) event.getNewValue();
    vinyls.addAll(newVinyls);
  }

  public ObservableList<Vinyl> getVinyls()
  {
    return vinyls;
  }

  public IntegerProperty getUserIDProperty()
  {
    return userID;
  }
}
