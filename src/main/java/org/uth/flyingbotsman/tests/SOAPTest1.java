package org.uth.flyingbotsman.tests;

import org.uth.flyingbotsman.utils.SOAPHandle;

public class SOAPTest1
{
  public static void main( String[] args )
  {
    String XMLPayload = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://thalesgroup.com/RTTI/2013-11-28/Token/types\" xmlns:ldb=\"http://thalesgroup.com/RTTI/2017-10-01/ldb/\">\n" +
                        "  <soapenv:Header>\n" +
                        "    <typ:AccessToken>\n" +
                        "    <typ:TokenValue>b0ea9cb7-1dd5-4642-88d2-1c2cea541765</typ:TokenValue>\n" +
                        "    </typ:AccessToken>\n" +
                        "  </soapenv:Header>\n" +
                        "  <soapenv:Body>\n" +
                        "    <ldb:GetDepartureBoardRequest>\n" +
                        "    <ldb:numRows>20</ldb:numRows>\n" +
                        "    <ldb:crs>HFD</ldb:crs>\n" +
                        "    </ldb:GetDepartureBoardRequest>\n" +
                        "  </soapenv:Body>\n" +
                        "</soapenv:Envelope>";

    String SOAPURL = "http://lite.realtime.nationalrail.co.uk/OpenLDBWS/ldb11.asmx";
    String SOAPAction = "http://thalesgroup.com/RTTI/2012-01-13/ldb/GetDepartureBoard";
    
    try
    {
      String response = SOAPHandle.sendSOAPPayload(SOAPURL, SOAPAction, XMLPayload);

      System.out.println( response );
    }
    catch( Exception exc )
    {
      exc.printStackTrace();
    }
  }
}