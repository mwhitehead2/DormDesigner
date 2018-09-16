//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P5 Dorm Designer 5000
// Files:           Main.java, CreateBedButton.java, CreateSofaButton.java,
//					Furniture.java, SaveButton.java, LoadButton.java
// Course:          CS300, Spring 2018
//
// Author:          Meghan Whitehead
// Email:           mwhitehead2@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Lecturer's Name: N/A
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _x_ Write-up states that pair programming is allowed for this assignment.
//   _x_ We have both read and understand the course Pair Programming Policy.
//   _x_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         Piazza questions and answers
// Online Sources:  I used the website titled StackOverflow to figure out how 
//					test if there was a number within a string
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The LoadButton class will create a button in the room. This button will be gray with black text, 
 * "Load Room", and will become a darker gray when the mouse is over the button (checked by the 
 * mouseIsOver method). When the button is clicked, the mouseDown method will be called. The data
 * stored in the RoomData.ddd file will be read, and new furniture objects will be created in the 
 * locations specified by the file. If the file is not formatted correctly, the image does not 
 * exist, or the file does not exist, a warning message will be displayed. When the file does not 
 * exist, there will be no objects added. When an image does not exist or it is formatted 
 * incorrectly, that line will be skipped and the rest of the objects will be loaded. 
 * @author MeghanWhitehead
 *
 */
public class LoadButton extends Button implements DormGUI {
	
	/**
	 * This constructor method will set the position array to have indexes that represent the 
	 * position of the load button on the screen. It creates a label to be printed on the bed
	 * button.
	 * @param x, x axis position of button
	 * @param y, y axis position of button
	 * @param processing
	 */
	public LoadButton(float x, float y, PApplet processing) {
		super(x, y, processing);
		super.label = "Load Room"; // will be displayed on the button
		super.position = new float[]{x, y}; // array to hold the position of the load button in the room
		super.processing = processing;
	}

	/**
	 * The checkString method will be sent strings from the mouseDown method to check if they are 
	 * numbers or not numbers. It will do this by attempting to convert the string to a float. If
	 * it is a number, this will work and the method will return true. If it is not a number, an
	 * exception will be thrown and the method will return false.
	 * @param content, string to be checked
	 * @return true or false
	 */
	public boolean checkString(String content) {
		try {
			Float.parseFloat(content);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 *  This method will load data from the file RoomData.ddd. It does so by first creating a 
	 *  new file, and putting it into a scanner. The scanner then reads the file line by line. 
	 *  Each line of the file is split into an array by colons and commas. The zeroth index of 
	 *  the array is the object type, the first is the x coordinate, the second is the y 
	 *  coordinate, and the third is the number of rotations. These numbers are sent to the 
	 *  Furniture class and a new Furniture object is created in the location specified by the 
	 *  file.
	 */
	@Override
	public void mouseDown(Furniture[] furniture) {
		try {
			String[] line = null; // initializes the array to hold each line of the file
			int count = 0; // keeps track of how many Furniture objects have been created
			File file = new File("RoomData.ddd"); // creates the file
			Scanner scanner = new Scanner(file); // loads the file into the scanner to be read
			
			for (int i = 0; i < furniture.length; i++){
				furniture[i] = null; // deletes all of the previous Furniture objects 
			}
			
			// the scanner will be read until there is nothing left in the file
			while (scanner.hasNextLine()) {
				// the max number of furniture objects is 6, and if the file tries to load more 
				// the loop will break and a warning message will be displayed
				if (count > 100) {
					System.out.println("WARNING: Unable to load more furniture.");
					break;
				}
				String next = scanner.nextLine(); // reads the next line of the scanner
				
				// checks to see if there is information within the string
				if (!next.isEmpty()) {
					// splits the information by colons and commas
					line = next.split(":|,");
					
					// trims the created strings so there is no whitespace
					for (int i = 0; i < line.length; i++) {
						line[i] = line[i].trim();
					}
					
					// creates a new image file with the type given in the zeroth index of array
					File image = new File("images/" + line[0] + ".png");
					
					// checks to make sure there are a correct number of elements, and that the 
					// zeroth index is a string and the rest are floats
					if (line.length != 4 || checkString(line[0]) || !checkString(line[1]) || 
							!checkString(line[2]) || !checkString(line[3])) {
						// if not, a warning message will be displayed
						System.out.println("WARNING: Found incorrectly formatted line in file: " + 
							Arrays.toString(line));
					} else if (!image.exists()) {
						// if the image of the zeroth index does not exist a warning message is 
						// displayed
						System.out.println("WARNING: Could not find an image for a furniture object of type: " + 
								line[0].toString());
					} else {
						// if everything is correct, a new Furniture object is created
						Furniture furnitureObject = new Furniture(line[0], Float.parseFloat(line[1]), 
								Float.parseFloat(line[2]), Float.parseFloat(line[3]), processing);
						furniture[count] = furnitureObject;
						count += 1; // records that a Furniture object has been created
					}
				}
			}
		} catch(IOException e) {
			// if the file could not be loaded, an exception is caught and message displayed
			System.out.println("WARNING: Could not load room contents from file RoomData.ddd.");
		} 
	}
}