package View;

import Model.Vinyl;
import ViewModel.AddVinylViewModel;
import ViewModel.VinylLibraryViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class VinylLibraryView
{
  @FXML Button addButton;
  private VinylLibraryViewModel listViewModel;
  private ObservableList<Vinyl> vinyls;
  @FXML TableView tableView;
  @FXML TextField textFieldID;
  @FXML Button borrowButton;
  @FXML Button reserveButton;
  @FXML Button returnButton;
  @FXML Button cancelButton;
  @FXML Button removeButton;
  @FXML TableColumn<Vinyl, String> title;
  @FXML TableColumn<Vinyl, String> artist;
  @FXML TableColumn<Vinyl, Integer> releaseYear;
  @FXML TableColumn<Vinyl, String> status;

  public VinylLibraryView(VinylLibraryViewModel listViewModel)
  {
    this.listViewModel=listViewModel;
  }
  public void initialize()
  {
    textFieldID.textProperty().bindBidirectional(listViewModel.getUserIDProperty(), new NumberStringConverter());
    vinyls=listViewModel.getVinyls();
    title.setCellValueFactory(new PropertyValueFactory<>("title"));
    artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
    releaseYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
    status.setCellValueFactory(new PropertyValueFactory<>("status"));
    tableView.setItems(vinyls);
  }

  public void onBorrowButtonPressed()
  {
    Vinyl selectedItem = (Vinyl) tableView.getSelectionModel().getSelectedItem();
    listViewModel.onBorrow(selectedItem);
  }

  public void onReserveButtonPressed()
  {
    Vinyl selectedItem = (Vinyl) tableView.getSelectionModel().getSelectedItem();
    listViewModel.onReserve(selectedItem);
  }

  public void onReturnButtonPressed()
  {
    Vinyl selectedItem = (Vinyl) tableView.getSelectionModel().getSelectedItem();
    listViewModel.onReturn(selectedItem);
  }
  public void onCancelButtonPressed()
  {
    Vinyl selectedItem = (Vinyl) tableView.getSelectionModel().getSelectedItem();
    listViewModel.onCancel(selectedItem);
  }

  public void onRemoveButtonPressed()
  {
    Vinyl selectedItem = (Vinyl) tableView.getSelectionModel().getSelectedItem();
    listViewModel.onRemove(selectedItem);
  }

  public void onAddButtonPressed()
  {
    listViewModel.onAdd();
  }
}
