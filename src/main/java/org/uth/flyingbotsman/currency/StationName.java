package org.uth.flyingbotsman.currency;

import java.io.*;
import java.util.*;

public class StationName
{
  private Map<String,String> _stations = new HashMap<String,String>();
  private Map<String,String> _display = new HashMap<String,String>();
  private Map<String,String> _reverseLookup = new HashMap<String,String>();

  public StationName()
  {
    // Read and populate the station name lookup
    InputStream input = StationName.class.getClassLoader().getResourceAsStream("station_codes.csv");

    BufferedReader reader = new BufferedReader( new InputStreamReader( input ));

    String data = null;

    try
    {
      while( ( data = reader.readLine()) != null )
      {
        String[] components = data.split(",");

        _stations.put(components[1], components[0].toLowerCase());
        _display.put(components[1], components[0]);
        _reverseLookup.put(components[0].toLowerCase(), components[1]);
      }

      System.out.println( "[FLYINGBOTSMAN] Stations Loaded: " + _stations.size() + "/" + _display.size() + "/" + _reverseLookup.size());
    }
    catch( IOException exc )
    {
      System.err.println( "Unable to read resource file in JAR due to " + exc.toString());
    }
  }

  public String getFullName( String shortCode )
  {
    return _stations.get( shortCode );
  }

  public String getDisplayName( String shortCode )
  {
    return _display.get( shortCode );
  }

  public String getShortCode( String longName )
  {
    return _reverseLookup.get( longName );
  }

  public Set<String> getAllShortCodes()
  {
    return _stations.keySet();
  }

  public Set<String> getAllFullNames()
  {
    return _reverseLookup.keySet();
  }
}