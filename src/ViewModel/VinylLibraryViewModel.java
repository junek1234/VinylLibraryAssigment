package ViewModel;

import Model.ClientModel;
import Model.Vinyl;
import Model.VinylLibrary;
import View.AddVinylView;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.List;
import Model.Vinyl;

public class VinylLibraryViewModel
{
  private ClientModel clientModel;
  private ObservableList<Vinyl> vinyls;
  private IntegerProperty userID;
  private StringProperty messages;


  public VinylLibraryViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    vinyls= FXCollections.observableArrayList();
    clientModel.addPropertyChangeListener("Vinyls",this::update);
    clientModel.addPropertyChangeListener("Message", this::addMessage);
    userID = new SimpleIntegerProperty();
    messages = new SimpleStringProperty();
  }

  private void addMessage(PropertyChangeEvent event)
  {
    String newMessages;
    if(getMessages()!=null)
    {
      newMessages=getMessages();
      newMessages+=event.getNewValue()+"\n";
    }
    else
    {
      newMessages=event.getNewValue()+"\n";
    }

    messages.set(newMessages);
  }

  private void update(PropertyChangeEvent event)
  {


    List<Vinyl> newVinyls =(List<Vinyl>) event.getNewValue();

    Platform.runLater(() -> {
      vinyls.setAll(newVinyls);
    });


  }

  public String getMessages()
  {
    return messages.get();
  }

  public StringProperty messagesProperty()
  {
    return messages;
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
    clientModel.borrowVinyl(getUserIDProperty().getValue(),findIndex(vinyl));
  }
  public void onReserve(Vinyl vinyl)
  {
    clientModel.reserveVinyl(getUserIDProperty().getValue(),findIndex(vinyl));
  }
  public void onReturn(Vinyl vinyl)
  {
    clientModel.returnVinyl(getUserIDProperty().getValue(),findIndex(vinyl));
  }
  public void onCancel(Vinyl vinyl)
  {
    clientModel.cancelReservation(getUserIDProperty().getValue(),findIndex(vinyl));
  }
  public void onRemove(Vinyl vinyl)
  {
    clientModel.removeVinyl(getUserIDProperty().getValue(),findIndex(vinyl));
  }

  public void onAdd()
  {
    AddVinylViewModel addViewModel = new AddVinylViewModel(clientModel, this);
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
    for (int i = 0; i < clientModel.getVinyls().size(); i++)
    {
      if(clientModel.getVinyls().get(i)==vinyl)
      {
        return i;
      }
    }
    return -1;
  }
}
