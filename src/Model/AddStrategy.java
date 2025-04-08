package Model;

public class AddStrategy implements ActionStrategy {
  public void execute(Message message, VinylLibrary vinylLibrary) {
    vinylLibrary.addVinyl(new Vinyl(message.getTitle(), message.getAuthor(), message.getYearOfRelease()));
  }
}