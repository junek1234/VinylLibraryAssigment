package Model;

public class ReservedState implements VinylState
{

  @Override public void onBorrow(Vinyl vinyl, int clientID)
  {
    if(clientID== vinyl.getReservedBy())
    {
      vinyl.setReservedBy(0);
      vinyl.setBorrowedBy(clientID);
      vinyl.changeToBorrowedState();
    }
  }

  @Override public void onReserve(Vinyl vinyl, int clientID)
  {
    //nothing
  }

  @Override public void onReturn(Vinyl vinyl, int clientID)
  {
    //nothing
  }

  @Override public String getStateName(Vinyl vinyl)
  {
    return "Reserved by "+vinyl.getReservedBy();
  }
}
