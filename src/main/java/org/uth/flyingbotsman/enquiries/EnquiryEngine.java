package org.uth.flyingbotsman.enquiries;

import org.uth.flyingbotsman.currency.*;
import org.uth.flyingbotsman.sax.*;
import org.uth.flyingbotsman.utils.*;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.*;
import java.util.*;

public class EnquiryEngine
{
  private String _token = null;
  private StationName _stations = null;

  public EnquiryEngine( String token )
  {
    _token = token;
    _stations = new StationName();
  }

  public String getStationShortCode( String input )
  {
    return _stations.getShortCode(input);
  }

  public String getStationFullName( String shortCode )
  {
    return _stations.getFullName(shortCode);
  }

  public String getStationDisplayName( String shortCode )
  {
    return _stations.getDisplayName(shortCode);
  }

  public Set<String> getAllShortCodes()
  {
    return _stations.getAllShortCodes();
  }

  public Departure getFastestDeparture( String startShortCode, String endShortCode, int retries )
  {
    String SOAPURL = "http://lite.realtime.nationalrail.co.uk/OpenLDBWS/ldb11.asmx";
    String SOAPAction = "http://thalesgroup.com/RTTI/2015-05-14/ldb/GetFastestDepartures";

    List<String> params = new ArrayList<>();

    params.add( _token );
    params.add( startShortCode );
    params.add( endShortCode );
    params.add( "0" );
    params.add( "120" );

    String soapPayload = Templates.replace(Templates.FASTEST_DEPARTURE, params);

    boolean satisfied = false;
    FastestDepartureHandler handler = new FastestDepartureHandler();
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

    if( handler.getFastestDeparture() == null ) System.err.println( "Max retries reached without success");
      
    return handler.getFastestDeparture();
  }
}