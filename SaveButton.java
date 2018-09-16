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
// Online Sources:  No online sources used
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.IOException;
import java.io.PrintWriter;

/**
 * The SaveRoom class will draw a button on the screen that allows the user to click on it to
 * save the layout of the room to the RoomData.ddd file. The button will become a darker 
 * shade of gray when the mouse is on top of it.
 * @author MeghanWhitehead
 *
 */
public class SaveButton extends Button implements DormGUI {
	
	/**
	 * This constructor method will set the position array to have indexes that represent the 
	 * position of the save button on the screen. It creates a label to be printed on the save
	 * button.
	 * @param x, x axis position of button
	 * @param y, y axis position of button
	 * @param processing
	 */
	
	public SaveButton(float x, float y, PApplet processing) {
		super(x, y, processing);
		super.label = "Save Room"; // text on the button
		super.position = new float[]{x, y}; // position of the button in the room
		super.processing = processing;
	}

	/**
	 *  The mouseDown method will print the furniture array to the file RoomData.ddd
	 *  It will print the type, position, and number of rotations by using a getter method in the
	 *  Furniture class. This information will be printed in the order of type, x position, y 
	 *  position, number of rotations. The type will be followed by a colon, and each number will
	 *  be followed by a comma (except for the last one). If the file cannot be created, an 
	 *  exception will be thrown and a warning message will be displayed. 
	 */
	@Override
	public void mouseDown(Furniture[] furniture) {
		try {
			// a new PrintWriter is created and will write to the file named RoomData.ddd
			PrintWriter writer = new PrintWriter("RoomData.ddd");
			// for each furniture object, its type, position and number of rotations are recorded
			for (int i = 0; i < furniture.length; i++) {
				if (furniture[i] != null) {
					// the furniturePosition array will hold each objects position in the room
					float[] furniturePosition = furniture[i].getPosition();
					writer.println(furniture[i].getType() + ": " + furniturePosition[0] + ", " 
							+ furniturePosition[1] + ", " + furniture[i].getRotations());
				}
			}
			writer.close();	// this ensures that the information was written to the file
		}
		catch(IOException e) {
			// exception is caught when the file cannot be written to 
			System.out.println("WARNING: Could not save room contents to file RoomData.ddd.");
		}
		catch (NullPointerException e) {
			System.out.println("WARNING: Could not save room contents to file RoomData.ddd.");
		}
		catch(Exception e) {
			System.out.println("WARNING: Could not save room contents to file RoomData.ddd.");
		}	
	} 	
}