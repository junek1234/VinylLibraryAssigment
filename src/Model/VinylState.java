public interface VinylState
{
  void onBorrow();
  void onReserve();
  void onReturn();
  String getStateName();
}
