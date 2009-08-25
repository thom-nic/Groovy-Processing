import processing.core.*

public class Sketch1 extends PApplet {
	// global variables
	public PFont font
	public int a = 0

	public void setup() {
		size 200, 200
		background 0
		noStroke()
		
		// The font must be located in the sketch's 
		// "data" directory to load successfully
		font = loadFont("FranklinGothic-Medium-48.vlw") 
		textFont font, 32
	}

	public void draw() {
		background 0
		
		fill 102
		rect a++%width, 10, 20, 80 
		
		fill 250
		// draw text/font stuff
		text "word", 15, 50
	}

	public static void main(args) {
		PApplet.main( [ "Sketch1" ] as String[] )
	}
}
