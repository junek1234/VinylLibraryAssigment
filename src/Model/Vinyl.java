package Model;

public class Vinyl
{
  VinylState currentState, availableState, borrowedState, reservedState, borrowedReservedState;
  private String title;
  private String artist;
  private int releaseYear;
  private int reservedBy;
  private int borrowedBy;

  public Vinyl(String title, String artist, int releaseYear)
  {
    availableState = new AvailableState();
    borrowedState = new BorrowedState();
    reservedState = new ReservedState();
    borrowedReservedState = new BorrowedReservedState();
    currentState = availableState;
    this.title=title;
    this.artist=artist;
    this.releaseYear=releaseYear;
    this.reservedBy=0;
    this.borrowedBy=0;
  }

  public int getReservedBy()
  {
    return reservedBy;
  }

  public void setReservedBy(int reservedBy)
  {
    this.reservedBy = reservedBy;
  }

  public int getBorrowedBy()
  {
    return borrowedBy;
  }

  public void setBorrowedBy(int borrowedBy)
  {
    this.borrowedBy = borrowedBy;
  }

  public void onBorrow(int clientID)
  {
    currentState.onBorrow(this, clientID);
  }
  public void onReserve(int clientID)
  {
    currentState.onReserve(this, clientID);
  }
  public void onReturn(int clientID)
  {
    currentState.onReturn(this, clientID);
  }
  public void changeToAvailableState()
  {
    currentState = availableState;
  }
  public void changeToBorrowedState()
  {
    currentState = borrowedState;
  }
  public void changeToReservedState()
  {
    currentState = reservedState;
  }
  public void changeToBorrowedReservedState()
  {
    currentState = borrowedReservedState;
  }

  public String getStateName()
  {
    return currentState.getStateName(this);
  }

}
