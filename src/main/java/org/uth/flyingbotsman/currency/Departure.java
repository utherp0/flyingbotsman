package org.uth.flyingbotsman.currency;

public class Departure
{
  private String _generated = null;
  private Station _origin = null;
  private Station _destination = null;
  private Station _target = null;
  private String _time = null;
  private String _status = null;
  private String _operator = null;
  private String _type = null;
  private String _id = null;

  public Departure( String generated, Station origin, Station destination, Station target, String time, String status, String operator, String type, String id )
  {
    _generated = generated;
    _origin = origin;
    _destination = destination;
    _target = target;
    _time = time;
    _status = status;
    _operator = operator;
    _type = type;
    _id = id;
  }

  // Accessors
  public String getGenerated() { return _generated; }
  public Station getOrigin() { return _origin; }
  public Station getDestination() { return _destination; }
  public Station getTarget() { return _target; }
  public String getTime() { return _time; }
  public String getStatus() { return _status; }
  public String getOperator() { return _operator; }
  public String getType() { return _type; }
  public String getId() { return _id; }
}