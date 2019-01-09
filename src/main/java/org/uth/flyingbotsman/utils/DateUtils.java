package org.uth.flyingbotsman.utils;

public class DateUtils
{
  public static String convertNWRFormat( String input )
  {
    // yyyy-mm-ddThh:mm:ss.ms+xx:yy -> hh:mm (dd/mm/yyyy)
    StringBuilder output = new StringBuilder();

    output.append( input.substring(11, 13) + ":");
    output.append( input.substring(14, 16) + " (");
    output.append( input.substring(8, 10) + "/");
    output.append( input.substring(5, 7) + "/");
    output.append( input.substring(0, 4) + ")");

    return output.toString();
  }
}