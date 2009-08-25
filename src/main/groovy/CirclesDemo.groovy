import processing.core.*
/**
A group of dots appear and then shrink in size.
This sketch uses the Groovy List and uses a second class SpriteEllipseInternal.
*/
class CirclesDemo extends PApplet {

	def sprites = []
	def renderer = JAVA2D // P2D
	void setup() {
		ellipseMode CENTER
		frameRate 20
		smooth()
	}
	
	void draw() {
		background 120
		
		nCount++
		nCount %= 90  // add additional sprites every so often:
		if ( ! nCount ) (1..20).each {

		PApplet.main( [ "CirclesDemo" ] as String[] );
	}	
}