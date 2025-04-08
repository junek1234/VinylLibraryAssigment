package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Log
{
  private Queue<LogLine> logQueue;
  private File logFile;
  private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd  hh:mm:ss");
  private static Log instance;
  private static final Lock lock = new ReentrantLock();
  private Log()
  {
    initialize();
  }
  public static Log getInstance()
  {
    if(instance==null)
    {
      synchronized (lock)
      {
        if(instance==null)
        {
          instance=new Log();
        }
      }
    }
    return instance;
  }

  private void initialize() {
    logQueue = new LinkedList<>();
    logFile = new File("logs.txt");
    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public synchronized void add(String log, String ipAddressFrom, String ipAddressTo)
  {
    if (log == null || log.equals("")) //Don't log empty lines
    {
      return;
    }
    // add to the queue
    LogLine logLine = new LogLine(log, dateFormat.format(Calendar.getInstance().getTime()), ipAddressFrom, ipAddressTo);
    logQueue.add(logLine);
    addToFile(logLine.toString());          // add to the file
    System.out.println(logLine); // add to the console
  }

  public Queue<LogLine> getAll()
  {
    return logQueue;
  }

  //Method to write logEntries to a file
  private void addToFile(String log)
  {
    if (log == null)
    {
      return;
    }
    BufferedWriter out = null;
    try
    {
      out = new BufferedWriter(new FileWriter(logFile, true));
      out.write(log + "\n");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        out.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}
