package org.uth.flyingbotsman.tests;

import org.uth.flyingbotsman.currency.StationName;

public class StationNameTest1
{
  public static void main( String[] args )
  {
    // Static tests
    StationName stations = new StationName();

    System.out.println( "SHORTCODES:");
    for( String shortCode : stations.getAllShortCodes())
    {
      System.out.print( shortCode + " " );
    }

    System.out.println();

    System.out.println( "STATIONAMES:");
    for( String station : stations.getAllFullNames())
    {
      System.out.print( station + " " );
    }

    System.out.println( "LOOKUP HFD:");
    System.out.println( stations.getFullName("HFD"));

    System.out.println( "LOOKUP Hereford:");
    System.out.println( stations.getShortCode("Hereford"));
  }
}