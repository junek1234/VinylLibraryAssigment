package Model;

public class LogLine {

  private String timestamp;
  private String logString;
  private String ipAdressFrom;
  private String ipAdressTo;

  public LogLine(String logString, String timestamp, String ipAdressFrom, String ipAdressTo) {
    this.logString = logString;
    this.timestamp = timestamp;
    this.ipAdressFrom=ipAdressFrom;
    this.ipAdressTo=ipAdressTo;
  }

  public String getLogString() {
    return logString;
  }

  public String getTimestamp() {
    return timestamp;
  }
  public String getIpAdressFrom() {
    return ipAdressFrom;
  }
  public String getIpAdressTo() {
    return ipAdressTo;
  }


  @Override
  public String toString() {
    return  "["+timestamp+"]" +" From:"+ ipAdressFrom+" To:" +ipAdressTo+" "+ logString;
  }
}