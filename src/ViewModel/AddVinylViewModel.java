package ViewModel;

import Model.ClientModel;
import Model.Vinyl;
import Model.VinylLibrary;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddVinylViewModel
{
  private ClientModel clientModel;
  private StringProperty title;
  private StringProperty artist;
  private IntegerProperty releaseYear;
  private IntegerProperty clientID;
  private VinylLibraryViewModel vinylLibraryViewModel;

  public AddVinylViewModel(ClientModel clientModel, VinylLibraryViewModel vinylLibraryViewModel)
  {
   this.clientModel=clientModel;
    title = new SimpleStringProperty();
    artist = new SimpleStringProperty();
    releaseYear = new SimpleIntegerProperty();
    this.vinylLibraryViewModel=vinylLibraryViewModel;
    clientID=vinylLibraryViewModel.getUserIDProperty();
  }

  public void addVinyl()
  {
    clientModel.addVinyl(clientID.getValue() ,title.get(), artist.get(), releaseYear.get());
  }

  public StringProperty titleProperty()
  {
    return title;
  }

  public StringProperty artistProperty()
  {
    return artist;
  }

  public IntegerProperty releaseYearProperty()
  {
    return releaseYear;
  }
}
