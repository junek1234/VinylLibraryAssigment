package Model;

import java.io.Serializable;

public class Vinyl implements Serializable
{
  VinylState currentState, availableState, borrowedState, reservedState, borrowedReservedState;
  private final String title;
  private final String artist;
  private final int releaseYear;
  private int reservedBy;
  private int borrowedBy;
  private String status;
  private boolean removeFlag;

  public Vinyl(Vinyl other)
  {
    this.currentState=other.currentState;
    this.availableState=other.availableState;
    this.borrowedBy=other.borrowedBy;
    this.reservedBy=other.reservedBy;
    this.borrowedReservedState=other.borrowedReservedState;
    this.title=other.title;
    this.artist=other.artist;
    this.releaseYear=other.releaseYear;
    this.reservedBy=other.reservedBy;
    this.borrowedBy=other.borrowedBy;
    this.status=other.status;
    this.removeFlag=other.removeFlag;


  }
  public String getTitle()
  {
    return title;
  }

  public String getArtist()
  {
    return artist;
  }

  public int getReleaseYear()
  {
    return releaseYear;
  }

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
    status="Available";
    removeFlag = false;
  }

  public String getStatus()
  {
    currentState.updateStatus(this);
    return status;
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
    if(!removeFlag)
    {
      currentState.onReserve(this, clientID);
    }
  }
  public void onReturn(int clientID)
  {
    currentState.onReturn(this, clientID);
  }
  public void onCancel(int clientID)
  {
    currentState.onCancel(this,clientID);
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


  public void setStatus(String status)
  {
    this.status = status;
  }
  public void setRemoveFlag()
  {
    removeFlag=true;
  }
  public boolean isRemoveFlag()
  {
    return removeFlag;
  }

}
