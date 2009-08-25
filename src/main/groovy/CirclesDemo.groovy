import processing.core.*
/**
A group of dots appear and then shrink in size.
This sketch uses the Groovy List and uses a second class SpriteEllipseInternal.
*/
class CirclesDemo extends PApplet {
	// setup vars
	def sprites = []
	def renderer = JAVA2D // P2D	// state	int nCount = -1	def animating = true	int clickX, clickY = 0	
	void setup() {
		ellipseMode CENTER		size 400, 400, renderer
		frameRate 20
		smooth()		textFont loadFont("TrebuchetMS-20.vlw"), 14
	}
	
	void draw() {
		background 120
		
		nCount++
		nCount %= 90  // add additional sprites every so often:
		if ( ! nCount ) (1..20).each {			sprites << new SpriteEllipse( this ) 		}
		def clicked		sprites.each { s ->			s.update()			if ( s.dead ) s.init()			else if ( clickX && s.isOver( clickX, clickY ) ) clicked = s			s.render()		}		if ( clicked ) sprites.remove clicked		clickX = 0				fill 255 // set text color		text sprites.size(), 5, height-5 // update sprite count display	}		void keyPressed() {		if ( key == ' ' && animating ) { noLoop(); animating = false }		else { loop(); animating = true }	}		void mouseClicked() { // get mouse click pos for next draw() call.		this.clickX = mouseX		this.clickY = mouseY//		println " $clickX $clickY"	}		static void main(args) {
		PApplet.main( [ "CirclesDemo" ] as String[] );
	}	
}