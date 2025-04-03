package Application;

import Model.ClientModel;
import Model.Message;
import View.VinylLibraryView;
import ViewModel.VinylLibraryViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Application
{
  public static void main(String[] args)
  {
    launch();
  }
  @Override public void start(Stage primaryStage) throws Exception
  {
    Scanner scanner = new Scanner(System.in);

    try
    {

      //this should be in a separate window login or sth - this needs to be changed
      System.out.println("ID: ");
      int clientID= scanner.nextInt();

      ClientModel clientModel = new ClientModel(clientID);
      VinylLibraryViewModel viewModel = new VinylLibraryViewModel(clientModel);
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/VinylLibraryView.fxml"));
      fxmlLoader.setControllerFactory(controllerClass -> new VinylLibraryView(viewModel));

      Scene sceneLibraryList = new Scene(fxmlLoader.load());
      primaryStage.setTitle("Vinyl Library");
      primaryStage.setScene(sceneLibraryList);
      primaryStage.show();

    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }

  }
}