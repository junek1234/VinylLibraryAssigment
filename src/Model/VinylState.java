package Model;

public interface VinylState
{
  void onBorrow(Vinyl vinyl, int clientID);
  void onReserve(Vinyl vinyl, int clientID);
  void onReturn(Vinyl vinyl, int clientID);
  void onCancel(Vinyl vinyl, int clientID);
  void updateStatus(Vinyl vinyl);
}
