package com.enernoc.rnd.gprocessing;

import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A Groovy script that is adapted to Processing.org
 */
public class PScriptLoader {
	GroovyClassLoader groovy;

	{ // initializer block
		ClassLoader parentCL = Thread.currentThread().getContextClassLoader();
		if ( parentCL == null ) parentCL = getClass().getClassLoader();
		groovy = new GroovyClassLoader( parentCL );
	}
	
	public PScript load( String scriptLocation ) throws Exception {
		Class<? extends Script> clazz = null;
		try { 
			URL url = new URL( scriptLocation );
			clazz = groovy.parseClass( url.openStream() );
		}
		catch ( MalformedURLException ex ) {
			clazz = groovy.parseClass( new File( scriptLocation ) );
		}
		if ( clazz == null ) throw new IllegalArgumentException(
				"Could not parse '" + scriptLocation + "' as a URL or File" );
    	
		Script script = clazz.newInstance();
		script.run(); // gets closure & variable definitions
		return new PScript( script.getBinding().getVariables() );
    }
}
