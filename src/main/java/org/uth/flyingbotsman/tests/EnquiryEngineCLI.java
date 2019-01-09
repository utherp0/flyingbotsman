package org.uth.flyingbotsman.tests;

import org.uth.flyingbotsman.enquiries.EnquiryEngine;
import org.uth.flyingbotsman.currency.*;
import java.io.*;

public class EnquiryEngineCLI
{
  private final static String PREPEND = "[FBM CLI] ";
  private final static String PROMPT = "[FBM COM]> ";
  private EnquiryEngine _engine = null;

  public static void main( String[] args )
  {
    if( args.length != 1 )
    {
      System.out.println( "Usage: java EnquiryEngineCLI accessToken");
      System.exit(0);
    }
    else
    {
      new EnquiryEngineCLI( args[0] );
    }
  }

  public EnquiryEngineCLI( String token )
  {
    _engine = new EnquiryEngine(token);

    // Loop reading from CLI
    Console console = System.console();

    System.out.print( PROMPT );
    String command = console.readLine();

    while( !( "quit".equalsIgnoreCase(command)))
    {
      String[] components = command.split(" ");

      switch( components[0].toLowerCase() )
      {
        case "help": log( "TBD" ); break;
        case "crs": this.processShortCode( components ); break;
        case "allcrs": this.processAllShortCodes(); break;
        case "fastest": this.processFastestDeparture( components ); break;
        default: log( "Unknown command (help,quit,crs)");
      }

      System.out.print( PROMPT );
      command = console.readLine();
    }
  }

  public void processFastestDeparture( String[] components )
  {
    if( components.length != 4 )
    {
      log( "Usage: fastest startcrs targetcrs maxretries" );
      return;
    }

    Integer retries = 0;

    try
    {
      retries = Integer.valueOf(components[3]);
    }
    catch( NumberFormatException exc )
    {
      log( "Invalid number format for retries (" + components[3] + ")" );
      return;
    }

    Departure departure = _engine.getFastestDeparture( components[1].toUpperCase(), components[2].toUpperCase(), retries );

    if( departure == null )
    {
      log( "Failed to find a departure for " + components[2] + " to " + components[3] );
    }
    else
    {
      StringBuilder output = new StringBuilder();

      output.append( "[" + departure.getTime() + "] ");
      output.append( "from " + departure.getOrigin().getDisplayName());
      output.append( " to " + departure.getTarget().getDisplayName());

      String destination = ( departure.getDestination() == null ? "" : " terminates " + departure.getDestination().getDisplayName());

      output.append( destination );
      output.append( " (" + departure.getStatus() + ") " + "{" + departure.getOperator() + "}");

      log( output.toString());
    }
  }

  public void processAllShortCodes()
  {
    StringBuilder allShortCodes = new StringBuilder();

    for( String code : _engine.getAllShortCodes() )
    {
      allShortCodes.append( code + " " );
    }

    log( allShortCodes.toString() );
  }

  public void processShortCode( String[] components )
  {
    if( components.length != 2 )
    {
      log( "Usage: crs shortcode" );
      return;
    }

    String displayName = _engine.getStationDisplayName(components[1].toUpperCase());
    displayName = ( displayName == null ? "No Match" : displayName );

    log( components[1].toUpperCase() + ":" + displayName );
  }

  private void log( String message )
  {
    System.out.println( PREPEND + message );
  }
}