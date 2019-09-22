package guimodule;

import processing.core.PApplet;

/*import processing.core.*;*/
public class MyDisplay extends PApplet{

	
	public void setup() {
		size(400,400);
		background(200,200,200);
	}
	
	public void draw() {
		fill(255,255,0);
		ellipse(200, 200, 390, 390);
		
		fill(0,0,0);
		ellipse(120,130,50,70);
		
		fill(0,0,0);
		ellipse(280,130,50,70);
		
		 arc(200,280,75,75, 0, PI);
		 
		 fill(0,0,0);
		 line(200, 280, 85, 20);
		 curve(200, 300, 5, 26, 73, 24, 73, 61);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
