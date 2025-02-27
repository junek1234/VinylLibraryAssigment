package View;

import ViewModel.AddVinylViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class AddVinylView
{
  @FXML TextField artistTextField;
  @FXML TextField yearTextField;
  @FXML TextField titleTextField;
  @FXML Button addButton;

  private AddVinylViewModel viewModel;

  public AddVinylView(AddVinylViewModel viewModel)
  {
    this.viewModel=viewModel;
  }
  public void initialize()
  {
    artistTextField.textProperty().bindBidirectional(viewModel.artistProperty());
    titleTextField.textProperty().bindBidirectional(viewModel.titleProperty());
    yearTextField.textProperty().bindBidirectional(viewModel.releaseYearProperty(), new NumberStringConverter());
  }
  public void onAddVinyl()
  {
    viewModel.addVinyl();
  }
}
