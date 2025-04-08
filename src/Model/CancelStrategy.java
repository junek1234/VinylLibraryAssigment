package Model;

public class CancelStrategy implements ActionStrategy {
  public void execute(Message message, VinylLibrary vinylLibrary) {
    vinylLibrary.cancelReservation(message.getClientID(), message.getVinylID());
  }
}