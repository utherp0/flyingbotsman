package org.uth.flyingbotsman.utils;

import java.io.*;
import java.net.*;

public class SOAPHandle
{
  private SOAPHandle() {}

  public static String sendSOAPPayload( String targetURL, String targetAction, String payload ) throws IOException
  {
    URL url = new URL( targetURL );
    URLConnection connection = url.openConnection();
    HttpURLConnection httpConnection = (HttpURLConnection)connection;

    byte[] payloadBytes = payload.getBytes();

    httpConnection.setRequestProperty("Content-Length", String.valueOf( payloadBytes.length));
    httpConnection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
    //httpConnection.setRequestProperty("Accept-Encoding", "gzip,deflate");
    httpConnection.setRequestProperty("SOAPAction", targetAction );
    //httpConnection.setRequestProperty("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
    //httpConnection.setRequestProperty("Connection", "Keep-Alive");
    httpConnection.setRequestMethod("POST");

    httpConnection.setDoOutput(true);
    httpConnection.setDoInput(true);

    OutputStream output = httpConnection.getOutputStream();
    output.write( payloadBytes );
    output.close();

    //System.out.println( "(CODE):" + httpConnection.getResponseCode());

    InputStreamReader input = new InputStreamReader( httpConnection.getInputStream());
    BufferedReader inputReader = new BufferedReader( input );

    StringBuilder outputPayload = new StringBuilder();

    String data = null;

    while( ( data = inputReader.readLine()) != null )
    {
      outputPayload.append( data );
    }

    httpConnection.disconnect();

    return outputPayload.toString();
  }
}