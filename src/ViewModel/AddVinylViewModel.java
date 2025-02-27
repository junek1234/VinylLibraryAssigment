package ViewModel;

import Model.Vinyl;
import Model.VinylLibrary;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddVinylViewModel
{
  private VinylLibrary vinylLibrary;
  private StringProperty title;
  private StringProperty artist;
  private IntegerProperty releaseYear;

  public AddVinylViewModel(VinylLibrary vinylLibrary)
  {
    this.vinylLibrary = vinylLibrary;
    title = new SimpleStringProperty();
    artist = new SimpleStringProperty();
    releaseYear = new SimpleIntegerProperty();
  }

  public void addVinyl()
  {
    vinylLibrary.addVinyl(new Vinyl(title.get(), artist.get(), releaseYear.get()));
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
