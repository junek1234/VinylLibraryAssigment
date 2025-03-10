package ViewModel;

import Model.Vinyl;
import Model.VinylLibrary;
import View.AddVinylView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
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
    vinylLibrary.addPropertyChangeListener("Vinyls",this::update);
    userID = new SimpleIntegerProperty();
  }

  private void update(PropertyChangeEvent event)
  {


    List<Vinyl> newVinyls =(List<Vinyl>) event.getNewValue();
    vinyls.setAll(newVinyls);


  }

  public ObservableList<Vinyl> getVinyls()
  {
    return vinyls;
  }

  public IntegerProperty getUserIDProperty()
  {
    return userID;
  }

  public void onBorrow(Vinyl vinyl)
  {
    vinylLibrary.borrowVinyl(getUserIDProperty().getValue(),findIndex(vinyl));
  }
  public void onReserve(Vinyl vinyl)
  {
    vinylLibrary.reserveVinyl(getUserIDProperty().getValue(),findIndex(vinyl));
  }
  public void onReturn(Vinyl vinyl)
  {
    vinylLibrary.returnVinyl(getUserIDProperty().getValue(),findIndex(vinyl));
  }
  public void onCancel(Vinyl vinyl)
  {
    vinylLibrary.cancelReservation(getUserIDProperty().getValue(),findIndex(vinyl));
  }
  public void onRemove(Vinyl vinyl)
  {
    vinylLibrary.removeVinyl(findIndex(vinyl));
  }

  public void onAdd()
  {
    AddVinylViewModel addViewModel = new AddVinylViewModel(vinylLibrary);
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AddVinylView.fxml"));
    fxmlLoader.setControllerFactory(controllerClass -> new AddVinylView(addViewModel));

    Scene sceneAddVinyl = null;
    try
    {
      sceneAddVinyl = new Scene(fxmlLoader.load());
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    Stage addStage = new Stage();
    addStage.setTitle("Add Vinyl");
    addStage.setScene(sceneAddVinyl);
    addStage.show();
  }
  public int findIndex(Vinyl vinyl)
  {
    for (int i = 0; i < vinylLibrary.getVinyls().size(); i++)
    {
      if(vinylLibrary.getVinyls().get(i)==vinyl)
      {
        return i;
      }
    }
    return -1;
  }
}
