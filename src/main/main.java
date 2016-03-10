package main;

import helpers.fileTransformator;
import tests.Utests;
import helpers.myLogger;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Test methods
		//Utests.runTests();
		//myLogger.record(myLogger.FATAL, "Just kidding, all goes good!");
		fileTransformator.readXMLFile("testGPX.GPX");
	}

}
