package Model;

import java.util.Random;

public class User implements Runnable
{

  private int id;
  private VinylLibrary vinylLibrary;
  public User(VinylLibrary vinylLibrary, int id)
  {
    this.vinylLibrary = vinylLibrary;
    this.id=id;
  }

  public void randomAction()
  {
    Random random = new Random();
    int number = random.nextInt(4)+1;
    int index = random.nextInt(vinylLibrary.getVinyls().size());
    switch(number)
    {
      case 1 -> {
        vinylLibrary.borrowVinyl(id, index);
      }
      case 2 -> {
        vinylLibrary.reserveVinyl(id, index);
      }
      case 3 -> {
        vinylLibrary.returnVinyl(id, index);
      }
      case 4 -> {
        vinylLibrary.cancelReservation(id, index);
      }
    }
  }
  @Override public void run()
  {
    while(true)
    {
      Random rand = new Random();
      try
      {
        Thread.sleep(rand.nextInt(3000)+100);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
      randomAction();
    }


  }
}
