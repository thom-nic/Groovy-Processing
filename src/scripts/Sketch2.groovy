import processing.core.*

/* Demo of a super-streamlined DSL that uses a script to define the instance
 * fields and setup/ draw methods.
 */

a = 0

setup = {
	size 200, 200
	background 0
	noStroke()
	
	// The font must be located in the sketch's 
	// "data" directory to load successfully
	def font = loadFont("FranklinGothic-Medium-48.vlw") 
	textFont font, 32
}

draw = {
	background 0
	
	fill 102
	rect a++ % width, 10, 20, 80 
	
	fill 250
	// draw text/font stuff
	text "word", 15, 50
}