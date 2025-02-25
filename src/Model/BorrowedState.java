package Model;

public class BorrowedState implements VinylState
{
  @Override public void onBorrow(Vinyl vinyl, int clientID)
  {
    //nothing
  }

  @Override public void onReserve(Vinyl vinyl, int clientID)
  {
    vinyl.setReservedBy(clientID);
    vinyl.changeToBorrowedReservedState();
  }

  @Override public void onReturn(Vinyl vinyl, int clientID)
  {
    if(clientID==vinyl.getBorrowedBy())
    {
      vinyl.setBorrowedBy(0);
      vinyl.changeToAvailableState();
    }

  }

  @Override public String getStateName(Vinyl vinyl)
  {
    return "Borrowed by "+vinyl.getBorrowedBy();
  }
}
