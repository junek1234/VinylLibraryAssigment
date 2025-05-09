package Model;

import java.io.Serializable;

public class BorrowedState implements VinylState, Serializable
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

  @Override public void onCancel(Vinyl vinyl, int clientID)
  {
    //nothing
  }

  @Override public void updateStatus(Vinyl vinyl)
  {
    String status="Borrowed by "+vinyl.getBorrowedBy();
    if(vinyl.isRemoveFlag())
    {
      status+=" (to be REMOVED)";
    }
    vinyl.setStatus(status);
  }

}
