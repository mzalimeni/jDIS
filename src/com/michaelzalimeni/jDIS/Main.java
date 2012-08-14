package com.michaelzalimeni.jDIS;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	/**
	 * Author: Michael Zalimeni
	 * All rights reserved
	 * @param args
	 */
	public static void main(String[] args) {

		//Create BufferedImage from command line filename and a copy to be drawn upon
		BufferedImage img = null;
		BufferedImage swappedImg = null;
		String filename = args[0].trim();
		try {
		    img = ImageIO.read(new File(filename));
		    swappedImg = ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.err.println("ERROR: Bad filename or file does not exist");
			System.exit(1);
		}
		
		//Get dimension of image
		int height = img.getHeight();
		int width = img.getWidth();
		
		//Get Graphics2D for second copy and prepare to redraw each half onto it
		Graphics2D drawSwapped = swappedImg.createGraphics();
		
		//Write each half into the opposite region of drawImage
		drawSwapped.drawImage(img, 0, 0, (width/2), height-1, width/2, 0, width, height-1, null);
		drawSwapped.drawImage(img, width/2, 0, width, height-1, 0, 0, (width/2), height-1, null);
		
		//Write out new image as copy of old one
		String filenameSwapped = filename.substring(0, filename.lastIndexOf('.'));
		String extension = filename.substring(filename.lastIndexOf('.')+1);
		filenameSwapped += "SWP.";
		filenameSwapped += extension;
		
		try {
		    File outputfile = new File(filenameSwapped);
		    ImageIO.write(swappedImg, extension.toLowerCase(), outputfile);
		} catch (IOException e) {
		    System.err.println("ERROR: Could not save file - is it a valid extension?");
		    System.exit(1);
		}
		
		System.exit(0);
	}

}
