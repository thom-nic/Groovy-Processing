import javax.swing.*

import processing.core.*;
import java.awt.*;

import groovy.swing.SwingBuilder
import java.awt.BorderLayout as BL


// pseudo main begins here

if (args.size() > 0 && args[0] != null)
   {
   String strGroovyFileExtension = ".groovy"
   String strProcessingSketchFileName = args[0]
	if (strProcessingSketchFileName.indexOf(strGroovyFileExtension) == -1)
		{
		strProcessingSketchFileName += strGroovyFileExtension; 
		}

	def embed = runart.getClassLoader().parseClass(new
		java.io.File(strProcessingSketchFileName)).newInstance()
	
	SwingBuilder swingBuilder = new SwingBuilder()
	JFrame frame = swingBuilder.frame(title:"", layout: new BL()) {
		}
	frame.add(embed, BorderLayout.CENTER);
	
	// important to call this whenever embedding a PApplet.
	// It ensures that the animation thread is started and
	// that other internal variables are properly set.
	embed.init();

	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
	frame.pack()
	frame.setVisible(true)
	
	println "DONE"
	}
else
	{
	println "ERROR: processing_sketch_filename not provided"
	println "usage: runart <processing_sketch_filename>"
	}
