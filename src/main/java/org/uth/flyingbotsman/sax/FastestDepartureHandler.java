package org.uth.flyingbotsman.sax;

import java.util.Stack;

import org.uth.flyingbotsman.currency.Departure;
import org.uth.flyingbotsman.currency.Station;
import org.uth.flyingbotsman.currency.StationName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FastestDepartureHandler extends DefaultHandler
{
  private Stack<String> _elementStack = new Stack<>();
  boolean _inDestination = false;
  boolean _inOrigin = false;

  StationName _stations = new StationName();

  // Local storage
  private String _generated = null;
  private Station _origin = null;
  private Station _destination = null;
  private Station _target = null;
  private String _time = null;
  private String _status = null;
  private String _operator = null;
  private String _type = null;
  private String _id = null;

  private Departure _departure = null;

  @Override
  public void startElement( String uri, String localName, String qName, Attributes attributes ) throws SAXException
  {
    _elementStack.push(qName);

    if( "lt5:destination".equals(qName)) _inDestination = true;
    if( "lt5:origin".equals(qName)) _inOrigin = true;

    if( "lt7:destination".equals(qName))
    {
      _target = new Station( attributes.getValue("crs"), _stations.getFullName(attributes.getValue("crs")), _stations.getDisplayName(attributes.getValue("crs")));
    }
  }  

  @Override
  public void endElement( String uri, String localName, String qName ) throws SAXException
  {
    _elementStack.pop();

    if( "lt5:destination".equals(qName)) _inDestination = false;
    if( "lt5:origin".equals(qName)) _inOrigin = false;
  }

  @Override
  public void characters( char ch[], int start, int length ) throws SAXException
  {
    String value = new String( ch, start, length ).trim();

    // Short circuit empty values.
    if( value.length() == 0 ) return;

    String tag = _elementStack.peek();

    switch( tag )
    {
      case "lt4:generatedAt": _generated = value; break;
      case "lt4:crs":
        if( !_inDestination && !_inOrigin)
        { 
          _origin = new Station( value, _stations.getFullName(value), _stations.getDisplayName(value)); break;
        }
        else
        {
          if( _inDestination ) _destination = new Station( value, _stations.getFullName(value), _stations.getDisplayName(value)); break;
        }
      case "lt4:std": _time = value; break;
      case "lt4:etd": _status = value; break;
      case "lt4:operator": _operator = value; break;
      case "lt4:serviceType": _type = value; break;
      case "lt4:serviceID": _id = value; break;
      default: break;      
    }
  }

  @Override
  public void endDocument() throws SAXException
  {
    _departure = new Departure(_generated, _origin, _destination, _target, _time, _status, _operator, _type, _id);
  }

  public Departure getFastestDeparture() { return _departure; }
}