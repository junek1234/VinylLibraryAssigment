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

  @Override public void onCancel(Vinyl vinyl, int clientID)
  {
    if(clientID== vinyl.getReservedBy())
    {
      vinyl.setReservedBy(0);
      vinyl.changeToAvailableState();

    }
  }

  @Override public void updateStatus(Vinyl vinyl)
  {
    String status="Reserved by "+vinyl.getReservedBy();
    if(vinyl.isRemoveFlag())
    {
      status+=" (to be REMOVED)";
    }
    vinyl.setStatus(status);
  }

}
