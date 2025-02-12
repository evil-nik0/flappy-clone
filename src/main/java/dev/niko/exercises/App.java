package dev.niko.exercises;

import java.io.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
    	BufferedReader reader = new BufferedReader(new InputStreamReader( App.class.getResourceAsStream("/testing.txt") ));
    	
        System.out.println(reader.readLine());
    }
}
