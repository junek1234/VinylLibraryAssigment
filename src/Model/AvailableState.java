package Model;

import java.io.Serializable;

public class AvailableState implements VinylState, Serializable
{

  @Override public void onBorrow(Vinyl vinyl, int clientID)
  {
    vinyl.setBorrowedBy(clientID);
    vinyl.changeToBorrowedState();
  }

  @Override public void onReserve(Vinyl vinyl, int clientID)
  {
    vinyl.setReservedBy(clientID);
    vinyl.changeToReservedState();
  }

  @Override public void onReturn(Vinyl vinyl, int clientID)
  {
    //nothing
  }

  @Override public void onCancel(Vinyl vinyl, int clientID)
  {
    //nothing
  }

  @Override public void updateStatus(Vinyl vinyl)
  {
    String status="Available";
    if(vinyl.isRemoveFlag())
    {
      status+=" (to be REMOVED)";
    }
    vinyl.setStatus(status);
  }


}
