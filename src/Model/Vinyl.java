public class Vinyl
{
  VinylState currentState;
  private String title;
  private String artist;
  private int releaseYear;

  public Vinyl()
  {

  }
  public void onBorrow()
  {
    currentState.onBorrow();
  }
  public void obReserve()
  {
    currentState.onReserve();
  }
  public void onReturn()
  {
    currentState.onReturn();
  }

}
