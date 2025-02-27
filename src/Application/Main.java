package Application;

import Model.User;
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

    Vinyl vinyl1 = new Vinyl("Midnight Echoes", "The Velvet Reverie", 1978);
    Vinyl vinyl2 = new Vinyl("Electric Sunburst", "Neon Horizon", 1985);
    Vinyl vinyl3 = new Vinyl("Moonlit Serenade", "Scarlet Dreams", 1969);
    Vinyl vinyl4 = new Vinyl("Wildfire Skies", "The Dust Riders", 1973);
    Vinyl vinyl5 = new Vinyl("Analog Soul", "Retro Frequency", 1991);
    Vinyl vinyl6 = new Vinyl("Crimson Carousel", "The Amber Chords", 1976);
    Vinyl vinyl7 = new Vinyl("Soundwaves & Shadows", "Echo Division", 1982);
    Vinyl vinyl8 = new Vinyl("Cosmic Reverberation", "Solar Drift", 1974);
    Vinyl vinyl9 = new Vinyl("Forgotten Melodies", "The Lullaby Syndicate", 1965);
    Vinyl vinyl10 = new Vinyl("Synthetic Dreams", "Cyber Groove", 1989);
    vinylLibrary.addVinyl(vinyl1);
    vinylLibrary.addVinyl(vinyl2);
    vinylLibrary.addVinyl(vinyl3);
    vinylLibrary.addVinyl(vinyl4);
    vinylLibrary.addVinyl(vinyl5);
    vinylLibrary.addVinyl(vinyl6);
    vinylLibrary.addVinyl(vinyl7);
    vinylLibrary.addVinyl(vinyl8);
    vinylLibrary.addVinyl(vinyl9);
    vinylLibrary.addVinyl(vinyl10);

    User user1 = new User(vinylLibrary, 1);
    User user2 = new User(vinylLibrary, 2);
    User user3 = new User(vinylLibrary, 3);
    User user4 = new User(vinylLibrary, 4);
    User user5 = new User(vinylLibrary, 5);

    Thread thread1 = new Thread(user1);
    Thread thread2 = new Thread(user2);
    Thread thread3 = new Thread(user3);
    Thread thread4 = new Thread(user4);
    Thread thread5 = new Thread(user5);

    thread1.setDaemon(true);
    thread2.setDaemon(true);
    thread3.setDaemon(true);
    thread4.setDaemon(true);
    thread5.setDaemon(true);

    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();
    thread5.start();


    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/VinylLibraryView.fxml"));
    fxmlLoader.setControllerFactory(controllerClass -> new VinylLibraryView(viewModel));

    Scene sceneLibraryList = new Scene(fxmlLoader.load());
    primaryStage.setTitle("Vinyl Library");
    primaryStage.setScene(sceneLibraryList);
    primaryStage.show();


  }
}
