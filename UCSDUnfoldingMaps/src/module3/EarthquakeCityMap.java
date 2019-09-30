package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.ImmoScout;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.MapQuestProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;


import java.util.List;
import de.fhpotsdam.unfolding.data.Feature;

//Parsing library
import parsing.ParseFeed;




/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = true;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new  Google.GoogleTerrainProvider() );
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
//	    //setting colors
	    int yellow = color(255, 255, 0);
	    int blue = color(8, 31, 145);
	    int red = color(221, 30, 8);
   
 // The List you will populate with new SimplePointMarkers
    List<Marker> markers = new ArrayList<Marker>();
    
// Use provided parser to collect properties for each earthquake
    //PointFeatures have a getLocation method
List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);

PointFeature eqobj = earthquakes.get(0);
System.out.println(eqobj.getProperties());

 //Looping through the Parse Earthquakes to get properties
	    
  for(PointFeature eqguy: earthquakes) {
	  SimplePointMarker marks = createMarker(eqguy);
	  Object magobj = eqguy.getProperty("magnitude");
	  float eqmag = Float.parseFloat(magobj.toString());
	  
	  
	 /////Filtering and changing Mag Colors
	  
	  if(eqmag>=5.0f) {
		  marks.setColor(red);
		  marks.setRadius(10);
		  }
	  else if(eqmag>=4.0f && eqmag<5.0f) {
		  marks.setColor(yellow);
		  marks.setRadius(10);}
	  
	  else if (eqmag>=3.0f && eqmag<=4.0f) {
		  marks.setColor(color(128,128,128));
		  marks.setRadius(10);
	  }
	  
	 
	
	  
	  if(eqmag>=3) {
	
		  markers.add(marks);
	  }
	  
	  
	 
	  
	  
	 }
	    // adding markers to the map
	    map.addMarkers(markers);	  
	    
	    
	 }	    
	    
	    
	   //////////////End OF Set Up Method//////////////////////// 
	    
	  /* createMarker: A suggested helper method that takes in an earthquake 
	 * feature and returns a SimplePointMarker for that earthquake
	 * 
	 * In step 3 You can use this method as-is.  Call it from a loop in the 
	 * setp method.  
	 * 
	 * TODO (Step 4): Add code to this method so that it adds the proper 
	 * styling to each marker based on the magnitude of the earthquake.  
	*/
	private SimplePointMarker createMarker(PointFeature feature)
	{  
		// To print all of the features in a PointFeature (so you can see what they are)
		// uncomment the line below.  Note this will only print if you call createMarker 
		// from setup
		System.out.println(feature.getProperty("depth"));
		
		// Create a new SimplePointMarker at the location given by the PointFeature
		SimplePointMarker marker = new SimplePointMarker(feature.getLocation());
		
		Object magObj = feature.getProperty("magnitude");
		float mag = Float.parseFloat(magObj.toString());
		
		// Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	   // int yellow = color(255, 255, 0);
		
		// TODO (Step 4): Add code below to style the marker's size and color 
	    // according to the magnitude of the earthquake.  
	    // Don't forget about the constants THRESHOLD_MODERATE and 
	    // THRESHOLD_LIGHT, which are declared above.
	    // Rather than comparing the magnitude to a number directly, compare 
	    // the magnitude to these variables (and change their value in the code 
	    // above if you want to change what you mean by "moderate" and "light")
	    
	    
	    // Finally return the marker
	    return marker;
	}
	
	public void draw() {
	    background(10);
   map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		// Remember you can use Processing's graphics methods here
		
		
		
		
		
		
		
				// Making the Box
				fill(255, 255, 255);
				rect(30, 100, 150, 300, 7);
				
				
				// Making the text 
				textSize(12);
				fill(50);
				text("Earthquake Key", 60, 110, 150, 150);
				text("5.0+ Magnitude", 80, 150, 150, 150);
				text("4.0+ Magnitude", 80, 190, 150, 150);
				text("3 to 4", 80, 230, 150, 150);
				
				
				//Creating each Mag and changing its color and position
				//5+ Mag
				fill(221, 30, 8);
				ellipse(60, 157, 20, 20);
				//4+ mag
				fill(255, 255, 0);
				ellipse(60, 198, 20, 20);
				//small Mag
				fill(128, 128, 128);
				ellipse(60, 237, 20, 20);
		
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
	
		
	
	
}
