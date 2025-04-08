package Model;

public class RemoveStrategy implements ActionStrategy {
  public void execute(Message message, VinylLibrary vinylLibrary) {
    vinylLibrary.removeVinyl(message.getVinylID());
  }
}