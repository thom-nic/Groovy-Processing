import processing.core.*
// setup vars
sprites = []
renderer = JAVA2D // P2D// statenCount = -1animating = trueclickX = 0clickY = 0
setup = {	source "src/scripts/resources" // additional classes here.
	ellipseMode CENTER	size 400, 400, renderer
	frameRate 20
	smooth()	textFont loadFont("TrebuchetMS-20.vlw"), 14
}

draw = {
	background 120
	
	nCount++
	nCount %= 90  // add additional sprites every so often:
	if ( ! nCount ) (1..20).each {		sprites << new SpriteEllipse( this ) 	}
	def clicked	sprites.each { s ->		s.update()		if ( s.dead ) s.init()		else if ( clickX && s.isOver( clickX, clickY ) ) clicked = s		s.render()	}	if ( clicked ) sprites.remove clicked	clickX = 0		fill 255 // set text color	text sprites.size(), 5, height-5 // update sprite count display}keyPressed = {	if ( key == ' ' && animating ) { noLoop(); animating = false }	else { loop(); animating = true }}mouseClicked = { // get mouse click pos for next draw() call.	clickX = mouseX	clickY = mouseY//	println " $clickX $clickY"}