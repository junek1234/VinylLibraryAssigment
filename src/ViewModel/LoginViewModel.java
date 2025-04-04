package ViewModel;

import Model.ClientModel;
import Model.Vinyl;
import Model.VinylLibrary;
import View.AddVinylView;
import View.VinylLibraryView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewModel
{
  private ClientModel clientModel;
  private IntegerProperty id;

  public LoginViewModel(ClientModel clientModel)
  {
    this.clientModel=clientModel;
   id = new SimpleIntegerProperty();
  }

  public void login() throws IOException
  {
    clientModel.setClientID(id.getValue());
    VinylLibraryViewModel viewModel = new VinylLibraryViewModel(clientModel);
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/VinylLibraryView.fxml"));
    fxmlLoader.setControllerFactory(controllerClass -> new VinylLibraryView(viewModel));


    Scene sceneLibraryList = new Scene(fxmlLoader.load());
    Stage stage = new Stage();
    stage.setScene(sceneLibraryList);
    stage.setTitle("Vinyl Library");
    stage.show();
  }

  public IntegerProperty idProperty()
  {
    return id;
  }
}
