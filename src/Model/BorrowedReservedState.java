package Model;

public class BorrowedReservedState implements VinylState
{

  @Override public void onBorrow(Vinyl vinyl, int clientID)
  {
    //nothing
  }

  @Override public void onReserve(Vinyl vinyl, int clientID)
  {
    //nothing
  }

  @Override public void onReturn(Vinyl vinyl, int clientID)
  {
    if(clientID==vinyl.getBorrowedBy())
    {
      vinyl.setBorrowedBy(0);
      vinyl.changeToReservedState();
    }

  }

  @Override public String getStateName(Vinyl vinyl)
  {
    return "Borrowed by "+vinyl.getBorrowedBy()+" and reserved by "+vinyl.getReservedBy();
  }
}
