/**
 * 
 */
package com.enernoc.rnd.gprocessing;

import groovy.lang.Closure;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovySystem;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import processing.core.PApplet;

/**
 * @author tnichols
 */
public class PScript extends PApplet { 
	private static final long serialVersionUID = 1834598132024769892L;

	/* Holds 'instance' variables between calls to 'setup' and 'draw.'
	 * TODO should be a thread-safe map?
	 */
	Map<String,Object> binding;
	
	List<String> closures = Arrays.asList( "setup", "draw", "mouseClicked", 
			"keyPressed" ); 
	
	Closure setupClosure = new Closure(this) {};
	Closure drawClosure = new Closure(this) {};
	Closure keyClosure = new Closure(this) {};
	Closure mouseClosure = new Closure(this) {};
	final protected Logger log = LogManager.getLogManager().getLogger( getClass().getName() );
	private GroovyClassLoader classLoader;
	
	public PScript( Map<String,Object> binding ) {
		this( binding, null );
	}
	
	public PScript( Map<String,Object> binding, GroovyClassLoader gcl ) {
		this.binding = binding;
		if ( gcl == null ) new GroovyClassLoader( getClass().getClassLoader() );
		this.classLoader = gcl;
	}
	
	public void init() {
		Closure c = (Closure)binding.get("setup");
		if ( c != null ) {
			c.setDelegate( this );
			this.setupClosure = c;
		}
			
		c = (Closure)binding.get("draw");
		if ( c != null ) {
			c.setDelegate( this );
			this.drawClosure = c;
		}
		c = (Closure)binding.get("keyPressed");
		if ( c != null ) {
			c.setDelegate( this );
			this.keyClosure = c;
		}
		c = (Closure)binding.get("mouseClicked");
		if ( c != null ) {
			c.setDelegate( this );
			this.mouseClosure = c;
		}
		
		super.init();
	}
	
	@Override public void setup() {
		setupClosure.call();
	}
	
	@Override public void draw() {
		drawClosure.call();
	}
	
	@Override public void keyPressed() {
		keyClosure.call();
	}
	
	@Override public void mouseClicked() {
		mouseClosure.call();
	}
	
	public void display() {
		JFrame f = new JFrame( getClass().getSimpleName() );
		f.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
		f.add( this );
		this.init();
		f.pack();
		f.setVisible( true );
	}
	
	/**
	 * Method that can be called from the script to load supporting classes
	 * from a given directory.
	 * @param dir
	 */
	protected void source( String resource ) throws IOException {
		File f = new File( resource );
		if ( ! f.exists() )
			throw new IllegalArgumentException( "Location does not exist: " 
					+ f.getAbsolutePath() );
		
		this.evalSource( f );
		// allow access to resources like images & fonts:
		if ( f.isDirectory() ) classLoader.addClasspath( resource );		
	}
	
	private void evalSource( File file ) throws IOException {
		if ( file.isDirectory() ) { 
			for ( File f : file.listFiles() ) evalSource( f );
			return;
		}
		
		String fileName = file.getName();
		if ( fileName.endsWith( ".jar" ) ) {
			log.info( "Adding JAR to classpath: " + fileName );
			classLoader.addClasspath( file.getAbsolutePath() );
		}
		else if ( fileName.endsWith( ".groovy" ) ) {
			log.info( "Parsing Groovy file: " + fileName );
			classLoader.parseClass( file );				
		}
	}
	
	public Object propertyMissing( String key ) {
		return binding.get( key );
	}
	
	public void propertyMissing( String key, Object val ) {
		binding.put( key, val );	
	}
}
