package Application;

import Model.ServerPacket;
import Model.Vinyl;
import Model.VinylLibrary;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
  private ServerConnection serverConnection;
  private ConnectionPool connectionPool;
  public Server()
    {
//      System.out.println("Starting server...");
      VinylLibrary vinylLibrary = new VinylLibrary();
      Vinyl vinyl1 = new Vinyl("Midnight Echoes", "The Velvet Reverie", 1978);
      Vinyl vinyl2 = new Vinyl("Electric Sunburst", "Neon Horizon", 1985);
      Vinyl vinyl3 = new Vinyl("Moonlit Serenade", "Scarlet Dreams", 1969);
      Vinyl vinyl4 = new Vinyl("Wildfire Skies", "The Dust Riders", 1973);
      Vinyl vinyl5 = new Vinyl("Analog Soul", "Retro Frequency", 1991);
      Vinyl vinyl6 = new Vinyl("Crimson Carousel", "The Amber Chords", 1976);
      Vinyl vinyl7 = new Vinyl("Soundwaves & Shadows", "Echo Division", 1982);
      Vinyl vinyl8 = new Vinyl("Cosmic Reverberation", "Solar Drift", 1974);
      Vinyl vinyl9 = new Vinyl("Forgotten Melodies", "The Lullaby Syndicate", 1965);
      Vinyl vinyl10 = new Vinyl("Synthetic Dreams", "Cyber Groove", 1989);
      vinylLibrary.addVinyl(vinyl1);
      vinylLibrary.addVinyl(vinyl2);
      vinylLibrary.addVinyl(vinyl3);
      vinylLibrary.addVinyl(vinyl4);
      vinylLibrary.addVinyl(vinyl5);
      vinylLibrary.addVinyl(vinyl6);
      vinylLibrary.addVinyl(vinyl7);
      vinylLibrary.addVinyl(vinyl8);
      vinylLibrary.addVinyl(vinyl9);
      vinylLibrary.addVinyl(vinyl10);
      vinylLibrary.addPropertyChangeListener("Removed", this::onVinylRemoved);
      vinylLibrary.addPropertyChangeListener("Status", this::onVinylStatusChanged);
      vinylLibrary.addPropertyChangeListener("Add", this::onVinylAdd);
      vinylLibrary.addPropertyChangeListener("ToBeRemoved", this::onVinylToBeRemoved);

      try
      {
        ServerSocket welcomeSocket = new ServerSocket(2910);
        connectionPool = new ConnectionPool();

        while (true)
        {
          Socket socket = welcomeSocket.accept();
          serverConnection = new ServerConnection(socket, connectionPool, vinylLibrary);
          connectionPool.add(serverConnection);
//          System.out.println("Client connected");
          new Thread(serverConnection).start();
        }
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }

  private void onVinylToBeRemoved(PropertyChangeEvent propertyChangeEvent)
  {
    try
    {
      serverConnection.sendMessage(propertyChangeEvent.getNewValue()+" is marked to be removed");
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  private void onVinylAdd(PropertyChangeEvent propertyChangeEvent)
  {
    try
    {
      serverConnection.sendMessage(propertyChangeEvent.getNewValue()+" has been added");
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  private void onVinylStatusChanged(PropertyChangeEvent propertyChangeEvent)
  {
    try
    {
      serverConnection.sendMessage(propertyChangeEvent.getNewValue()+"");
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  private void onVinylRemoved(PropertyChangeEvent propertyChangeEvent)

  {
    try
    {
      serverConnection.sendMessage(propertyChangeEvent.getNewValue()+" has been removed");
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args)
  {
    new Server();
  }
}