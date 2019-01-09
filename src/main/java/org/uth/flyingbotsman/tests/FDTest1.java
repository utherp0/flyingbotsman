package org.uth.flyingbotsman.tests;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.uth.flyingbotsman.currency.Departure;
import org.uth.flyingbotsman.currency.Templates;
import org.uth.flyingbotsman.sax.FastestDepartureHandler;
import org.uth.flyingbotsman.utils.DateUtils;
import org.uth.flyingbotsman.utils.SOAPHandle;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class FDTest1
{
  public static void main( String[] args )
  {
    String SOAPURL = "http://lite.realtime.nationalrail.co.uk/OpenLDBWS/ldb11.asmx";
    String SOAPAction = "http://thalesgroup.com/RTTI/2015-05-14/ldb/GetFastestDepartures";

    List<String> params = new ArrayList<>();

    params.add( "b0ea9cb7-1dd5-4642-88d2-1c2cea541765" );
    params.add( "HFD" );
    params.add( "LED" );
    params.add( "0" );
    params.add( "120" );

    String soapPayload = Templates.replace(Templates.FASTEST_DEPARTURE, params);

    //System.out.println( soapPayload );

    boolean satisfied = false;
    FastestDepartureHandler handler = new FastestDepartureHandler();
    Departure departure = null;
    int maxRetries = 10;

    while( !satisfied && !( maxRetries == 0 ) )
    {
      try
      {
        String response = SOAPHandle.sendSOAPPayload(SOAPURL, SOAPAction, soapPayload);

        XMLReader parser = XMLReaderFactory.createXMLReader();
        parser.setContentHandler(handler);
  
        parser.parse(new InputSource(new StringReader(response)));
        satisfied = true;
      }
      catch( Exception exc )
      {
        maxRetries--;
        //System.err.println( "SAX Parser failure, retries at " + maxRetries );
      }
    }
      
    departure = handler.getFastestDeparture();

    if( departure == null )
    {
      System.out.println( "Exhausted all retries....");
      System.exit(0);
    }

    System.out.println( "Generated " + DateUtils.convertNWRFormat(departure.getGenerated()));
    System.out.println( "Time:" + departure.getTime());
    System.out.println( "  " + departure.getOrigin().getShortCode() + "->" + 
                               departure.getTarget().getShortCode() + "->" +
                               departure.getDestination().getShortCode());
    System.out.println( "  [" + departure.getOperator() + "] (" + departure.getStatus() + ")");                           
  }
}