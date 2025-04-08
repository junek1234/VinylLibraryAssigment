package Model;

public class ReserveStrategy implements ActionStrategy {
  public void execute(Message message, VinylLibrary vinylLibrary) {
    vinylLibrary.reserveVinyl(message.getClientID(), message.getVinylID());
  }
}
