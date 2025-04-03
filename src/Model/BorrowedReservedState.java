package Model;

import java.io.Serializable;

public class BorrowedReservedState implements VinylState, Serializable
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

  @Override public void onCancel(Vinyl vinyl, int clientID)
  {
    if(clientID==vinyl.getReservedBy())
    {
      vinyl.setReservedBy(0);
      vinyl.changeToBorrowedState();
    }
  }

  @Override public void updateStatus(Vinyl vinyl)
  {
    String status="Borrowed by "+vinyl.getBorrowedBy()+" and reserved by "+vinyl.getReservedBy();
    if(vinyl.isRemoveFlag())
    {
      status+=" (to be REMOVED)";
    }
    vinyl.setStatus(status);
  }

}
