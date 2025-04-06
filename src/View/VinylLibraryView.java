package View;

import Model.Vinyl;
import ViewModel.VinylLibraryViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;

public class VinylLibraryView
{
  private VinylLibraryViewModel listViewModel;
  private ObservableList<Vinyl> vinyls;
  @FXML TableView tableView;
  @FXML TextField textFieldID;
  @FXML Button borrowButton;
  @FXML Button reserveButton;
  @FXML Button returnButton;
  @FXML Button cancelButton;
  @FXML Button removeButton;
  @FXML Button addButton;
  @FXML TableColumn<Vinyl, String> title;
  @FXML TableColumn<Vinyl, String> artist;
  @FXML TableColumn<Vinyl, Integer> releaseYear;
  @FXML TableColumn<Vinyl, String> status;
  @FXML TextArea messagesTextArea;

  public VinylLibraryView(VinylLibraryViewModel listViewModel)
  {
    this.listViewModel=listViewModel;
  }
  public void initialize()
  {
    textFieldID.textProperty().bindBidirectional(listViewModel.getUserIDProperty(), new NumberStringConverter());
    borrowButton.disableProperty().bind(listViewModel.getUserIDProperty().isEqualTo(0));
    reserveButton.disableProperty().bind(listViewModel.getUserIDProperty().isEqualTo(0));
    returnButton.disableProperty().bind(listViewModel.getUserIDProperty().isEqualTo(0));
    cancelButton.disableProperty().bind(listViewModel.getUserIDProperty().isEqualTo(0));
    vinyls=listViewModel.getVinyls();
    title.setCellValueFactory(new PropertyValueFactory<>("title"));
    artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
    releaseYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
    status.setCellValueFactory(new PropertyValueFactory<>("status"));
    tableView.setItems(vinyls);
    messagesTextArea.textProperty().bindBidirectional(listViewModel.messagesProperty());

  }

  public void onBorrowButtonPressed()
  {
    if(tableView.getSelectionModel().getSelectedItem()!=null)
    {
      Vinyl selectedItem = (Vinyl) tableView.getSelectionModel()
          .getSelectedItem();
      listViewModel.onBorrow(selectedItem);
    }
  }

  public void onReserveButtonPressed()
  {
    if(tableView.getSelectionModel().getSelectedItem()!=null)
    {
      Vinyl selectedItem = (Vinyl) tableView.getSelectionModel()
          .getSelectedItem();
      listViewModel.onReserve(selectedItem);
    }
  }

  public void onReturnButtonPressed()
  {
    if(tableView.getSelectionModel().getSelectedItem()!=null)
    {
      Vinyl selectedItem = (Vinyl) tableView.getSelectionModel()
          .getSelectedItem();
      listViewModel.onReturn(selectedItem);
    }
  }
  public void onCancelButtonPressed()
  {
    if(tableView.getSelectionModel().getSelectedItem()!=null)
    {
      Vinyl selectedItem = (Vinyl) tableView.getSelectionModel()
          .getSelectedItem();
      listViewModel.onCancel(selectedItem);
    }
  }

  public void onRemoveButtonPressed()
  {
    if(tableView.getSelectionModel().getSelectedItem()!=null)
    {
      Vinyl selectedItem = (Vinyl) tableView.getSelectionModel()
          .getSelectedItem();
      listViewModel.onRemove(selectedItem);
    }
  }

  public void onAddButtonPressed()
  {
    listViewModel.onAdd();
  }

}
