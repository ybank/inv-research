package edu.ucsd.cs;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import junit.framework.TestResult;
import junit.textui.TestRunner;

public class JUnit3Wrapper {
	
	public static void main(String args[]) {
		TestRunner aTestRunner= new TestRunner(new PrintStream(new OutputStream(){
			@Override
			public void write(int b) throws IOException {
				// TODO Auto-generated method stub
				
			}}));
		try {
			TestResult r= aTestRunner.start(args);
			if (!r.wasSuccessful()) 
				System.exit(1);
			System.exit(0);
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(2);
		}
	}

}
