package Model;

public class BorrowStrategy implements ActionStrategy {
  public void execute(Message message, VinylLibrary vinylLibrary) {
    vinylLibrary.borrowVinyl(message.getClientID(), message.getVinylID());
  }
}