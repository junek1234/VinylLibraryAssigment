package Model;

public class ModelTest1
{
  public static void main(String[] args)
  {
    VinylLibrary vinylLibrary = new VinylLibrary();
    Vinyl vinyl1 = new Vinyl("123","123",2010);
    Vinyl vinyl2 = new Vinyl("qwe","123",2011);
    Vinyl vinyl3 = new Vinyl("e5t","123",2020);
    int client1 = 1;
    int client2 = 2;
    int client3 = 3;
    vinylLibrary.addVinyl(vinyl1);
    vinylLibrary.addVinyl(vinyl2);
    vinylLibrary.addVinyl(vinyl3);

    System.out.println(vinylLibrary.getVinyls().get(0).getStatus());
    System.out.println(vinylLibrary.getVinyls().get(1).getStatus());
    System.out.println(vinylLibrary.getVinyls().get(2).getStatus());

    vinylLibrary.borrowVinyl(client1,0);
    vinylLibrary.reserveVinyl(client2,1);

    System.out.println(vinylLibrary.getVinyls().get(0).getStatus());
    System.out.println(vinylLibrary.getVinyls().get(1).getStatus());
    System.out.println(vinylLibrary.getVinyls().get(2).getStatus());

    vinylLibrary.borrowVinyl(client3, 0);
    vinylLibrary.borrowVinyl(client2, 1);
    vinylLibrary.reserveVinyl(client3, 1);

    System.out.println(vinylLibrary.getVinyls().get(0).getStatus());
    System.out.println(vinylLibrary.getVinyls().get(1).getStatus());
    System.out.println(vinylLibrary.getVinyls().get(2).getStatus());

    vinylLibrary.returnVinyl(client2,1);
    vinylLibrary.returnVinyl(client1,0);

    System.out.println(vinylLibrary.getVinyls().get(0).getStatus());
    System.out.println(vinylLibrary.getVinyls().get(1).getStatus());
    System.out.println(vinylLibrary.getVinyls().get(2).getStatus());

  }
}
