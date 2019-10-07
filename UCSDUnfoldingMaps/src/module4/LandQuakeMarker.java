package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

/** Implements a visual marker for land earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class LandQuakeMarker extends EarthquakeMarker {
	
	
	public LandQuakeMarker(PointFeature quake) {
		
		// calling EarthquakeMarker constructor
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = true;
	}


	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		// Draw a centered circle for land quakes
		// DO NOT set the fill color here.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered circle.
		
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		
		// TODO: Implement this method
		
				
				
				
				
				if(isOnLand()) {
					
					Object getrad = getProperty("radius");
					float rad = Float.parseFloat(getrad.toString());
					pg.ellipse(x,y,rad,rad);
					
					
					Object getmag = getProperty("radius");
					float mag = Float.parseFloat(getmag.toString());
					
					
					String xmarkage = getStringProperty("age");
					
						
					pg.fill(255,255,255);
					pg.stroke(0,0,0);
					if (xmarkage.equals("Past Day")|| xmarkage.equals("Past Day") ){
						
							pg.line(x + mag, y - mag, x - mag, y + mag);
							pg.line(x - mag, y - mag, x + mag, y + mag);
						}
					
					
					}
	
	
		
		
	}
	

	// Get the country the earthquake is in
	public String getCountry() {
		return (String) getProperty("country");
	}



		
}