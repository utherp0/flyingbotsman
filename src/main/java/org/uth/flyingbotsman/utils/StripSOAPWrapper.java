package org.uth.flyingbotsman.utils;

public class StripSOAPWrapper
{
  private StripSOAPWrapper() {}

  public static String strip( String payload )
  {
    return payload.substring((payload.indexOf("<soap:Body>") + "<soap:Body>".length()), payload.indexOf("</soap:Body>"));
  }
}