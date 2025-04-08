package Model;

public class ReturnStrategy implements ActionStrategy {
  public void execute(Message message, VinylLibrary vinylLibrary) {
    vinylLibrary.returnVinyl(message.getClientID(), message.getVinylID());
  }
}