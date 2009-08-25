import processing.core.PApplet

class SpriteEllipse {
	private Float x = 0
	private Float y = 0
	private Float rad = 75
	private Integer color = 20
	private @Delegate PApplet pApplet
	
	SpriteEllipse(PApplet pApplet) {
		this.pApplet = pApplet // must be set first since it is delegate
		this.init()
	}
	
	SpriteEllipse(Float x, Float y, Float r, Integer colorIn, PApplet pApplet) {
		this.x = x
		this.y = y
		this.rad = r
		this.color = colorIn
		this.pApplet = pApplet
	}
		
	/* Initialize fields to random vals */
	void init() {
		this.x = random( 0, width )
		this.y = random( 0, height )
		this.rad = random( (int)( height*0.05 ), (int)( height*0.225 ) )
		this.color = random( 0, 255 )		
	}
	
	void update() { if ( this.rad ) rad-- }
	
	void render() {
		fill color
		ellipse x, y, rad, rad
	}

	boolean isOver(int mx, int my) {
		(mx-x)*(mx-x) + (my-y)*(my-y) < rad*rad;
	}
	
	boolean isDead() { return rad < 1 }
}