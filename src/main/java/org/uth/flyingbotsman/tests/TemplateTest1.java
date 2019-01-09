package org.uth.flyingbotsman.tests;

import java.util.*;

import org.uth.flyingbotsman.currency.Templates;

public class TemplateTest1
{
  public static void main( String[] args )
  {
    List<String> params = new ArrayList<String>();

    params.add( "TOKEN");
    params.add( "HFD" );
    params.add( "LED" );
    params.add( "0" );
    params.add( "120" );

    System.out.println( Templates.replace(Templates.FASTEST_DEPARTURE, params));
  }
}