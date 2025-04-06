//package View;
//
//import ViewModel.LoginViewModel;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.util.converter.NumberStringConverter;
//
//import java.io.IOException;
//
//public class LoginView
//{
//  @FXML TextField idTextField;
//  @FXML Button loginButton;
//  private LoginViewModel loginViewModel;
//
//  public LoginView(LoginViewModel loginViewModel)
//  {
//    this.loginViewModel=loginViewModel;
//  }
//  public void initialize()
//  {
//    idTextField.textProperty().bindBidirectional(loginViewModel.idProperty(), new NumberStringConverter());
//  }
//
//  public void onLogin() throws IOException
//  {
//    loginViewModel.login();
//
//  }
//}
