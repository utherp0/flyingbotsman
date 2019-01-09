package org.uth.flyingbotsman.currency;

import java.util.*;

public class Templates
{
  public final static String FASTEST_DEPARTURE = 
    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://thalesgroup.com/RTTI/2013-11-28/Token/types\" xmlns:ldb=\"http://thalesgroup.com/RTTI/2017-10-01/ldb/\">\n" +
    "  <soapenv:Header>\n" +
    "    <typ:AccessToken>\n" +
    "      <typ:TokenValue>PARAM1</typ:TokenValue>\n" +
    "    </typ:AccessToken>\n" +
    "  </soapenv:Header>\n" +
    "  <soapenv:Body>\n" +
    "    <ldb:GetFastestDeparturesRequest>\n" +
    "      <ldb:crs>PARAM2</ldb:crs>\n" +
    "      <ldb:filterList>\n" +
    "        <ldb:crs>PARAM3</ldb:crs>\n" +
    "      </ldb:filterList>\n" +
    "      <ldb:timeOffset>PARAM4</ldb:timeOffset>\n" +
    "      <ldb:timeWindow>PARAM5</ldb:timeWindow>\n" +
    "    </ldb:GetFastestDeparturesRequest>\n" +
    "  </soapenv:Body>\n" +
    "</soapenv:Envelope>";

  private Templates() {}
  
  public static String replace( String template, List<String> params )
  {
    int position = 1;

    for( String component : params )
    {
      template = template.replaceFirst( "PARAM" + position, component );
      position++;
    }

    return template;
  }
}