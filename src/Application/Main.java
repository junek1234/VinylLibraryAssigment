package Application;

import Model.Vinyl;
import Model.VinylLibrary;
import View.AddVinylView;
import View.VinylLibraryView;
import ViewModel.AddVinylViewModel;
import ViewModel.VinylLibraryViewModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application
{
  public static void main(String[] args)
  {
    launch();
  }

  @Override public void start(Stage primaryStage) throws Exception
  {
    VinylLibrary vinylLibrary = new VinylLibrary();
    VinylLibraryViewModel viewModel = new VinylLibraryViewModel(vinylLibrary);
    AddVinylViewModel addViewModel = new AddVinylViewModel(vinylLibrary);

    Vinyl vinyl1 = new Vinyl("123","123",2010);
    Vinyl vinyl2 = new Vinyl("qwe","123",2011);
    Vinyl vinyl3 = new Vinyl("e5t","123",2020);
    vinylLibrary.addVinyl(vinyl1);
    vinylLibrary.addVinyl(vinyl2);
    vinylLibrary.addVinyl(vinyl3);



    vinylLibrary.borrowVinyl(1,0);
    vinylLibrary.reserveVinyl(1,0);
    vinylLibrary.reserveVinyl(2,1);

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/VinylLibraryView.fxml"));
    fxmlLoader.setControllerFactory(controllerClass -> new VinylLibraryView(viewModel));

    Scene sceneLibraryList = new Scene(fxmlLoader.load());
    primaryStage.setTitle("Vinyl Library");
    primaryStage.setScene(sceneLibraryList);
    primaryStage.show();


  }
}
