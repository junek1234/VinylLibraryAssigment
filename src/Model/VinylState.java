package Model;

public interface VinylState
{
  void onBorrow(Vinyl vinyl, int clientID);
  void onReserve(Vinyl vinyl, int clientID);
  void onReturn(Vinyl vinyl, int clientID);
  String getStateName(Vinyl vinyl);
}
