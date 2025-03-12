import java.util.*;

public class Location
{
    // Initialise class fields
    private double latitude;
    private double longitude;
    private String coordinateSystem;

    // Parameter constructor
    public Location(double pLatitude, double pLongitude, String pCoordinateSystem)
    {
        latitude = pLatitude;
        longitude = pLongitude;
        coordinateSystem = pCoordinateSystem;
    }

    // Copy constructor
    public Location(Location pLocation)
    {
        latitude = pLocation.getLatitude();
        longitude = pLocation.getLongitude();
        coordinateSystem = pLocation.getCoordinateSystem();
    }
  
    // Accessors
    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public String getCoordinateSystem()
    {
        return coordinateSystem;
    }

    // Mutators
   public void setLatitude(double pLatitude) 
   {
        if (isValidLatitude(pLatitude)) 
        {
            latitude = pLatitude;
        } 
        else 
        {
            System.out.println("Invalid entry detected! Latitude must be a valid value.");
        }
    }

    public void setLongitude(double pLongitude) 
    {
        if (isValidLongitude(pLongitude)) 
        {
            longitude = pLongitude;
        } 
        else 
        {
            System.out.println("Invalid entry detected! Longitude must be a valid value.");
        }
    }

    public void setCoordinateSystem(String pCoordinateSystem) 
    {
        if (isValidString(pCoordinateSystem)) 
        {
            coordinateSystem = pCoordinateSystem;
        } 
        else 
        {
            System.out.println("Invalid entry detected! CoordinateSystem cannot be null.");
        }
    }

    // Validation methods
    private boolean isValidLatitude(double latitude) 
    {
        return latitude >= -90 && latitude <= 90;
    }

    private boolean isValidLongitude(double longitude) 
    {
        return longitude >= -180 && longitude <= 180;
    }

    private boolean isValidString(String str) 
    {
        return str != null;
    }

    // ToString method
    public String toString()
    {
        String locationString;
        locationString = "(" + latitude + ", " + longitude + "), Coordinate System: " + coordinateSystem;
        return locationString;
    }

    // Equals check method
    public boolean equals(Location pLocation)
    {
        return latitude == pLocation.getLatitude() && longitude == pLocation.getLongitude() && coordinateSystem.equals(pLocation.getCoordinateSystem());
    }
}
