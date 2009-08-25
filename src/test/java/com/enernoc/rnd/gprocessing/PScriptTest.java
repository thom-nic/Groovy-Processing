package com.enernoc.rnd.gprocessing;

import java.io.InputStreamReader;

import org.junit.Test;

/**
 * Unit test for simple PScriptLoader.
 */
public class PScriptTest {

    @Test public void testScript() throws Exception {
    	PScript ps = new PScriptLoader().load( "src/scripts/Sketch2.groovy" );
    	ps.display();
    	new InputStreamReader( System.in ).read();
    }
    
    @Test public void testAdvancedScript() throws Exception {
    	PScript ps = new PScriptLoader().load( "src/scripts/CirclesDemo.groovy" );
    	ps.display();
    	new InputStreamReader( System.in ).read();
    }
}
