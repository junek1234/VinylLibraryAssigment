package View;

import Model.Vinyl;
import ViewModel.VinylLibraryViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class VinylLibraryView
{
  private VinylLibraryViewModel listViewModel;
  private ObservableList<Vinyl> vinyls;
  @FXML ListView listView;
  @FXML TextField textFieldID;
  @FXML Button borrowButton;
  @FXML Button reserveButton;
  @FXML Button returnButton;

  public VinylLibraryView(VinylLibraryViewModel listViewModel)
  {
    this.listViewModel=listViewModel;

  }
  public void initialize()
  {
    textFieldID.textProperty().bindBidirectional(listViewModel.getUserIDProperty(), new NumberStringConverter());
    vinyls=listViewModel.getVinyls();
    listView.setItems(vinyls);
  }

  public void onBorrowButtonPressed()
  {
//    listViewModel.getVinyls().
  }

  public void onReserveButtonPressed(ActionEvent actionEvent)
  {
  }

  public void onReturnButtonPressed(ActionEvent actionEvent)
  {
  }
}
