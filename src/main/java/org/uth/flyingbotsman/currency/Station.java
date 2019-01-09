package org.uth.flyingbotsman.currency;

public class Station
{
  private String _shortCode = null;
  private String _longName = null;
  private String _displayName = null;

  public Station( String shortCode, String longName, String displayName )
  {
    _shortCode = shortCode;
    _longName = longName;
    _displayName = displayName;
  }

  public String getShortCode() { return _shortCode; }
  public String getLongName() { return _longName; }
  public String getDisplayName() { return _displayName; }
}