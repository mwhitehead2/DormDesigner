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
import java.util.Arrays;

/**
 * The furniture class is used to update the locations of each furniture object, to detect
 * if the mouse is over a furniture object, allows the user to drag a furniture object 
 * if they click on the furniture, and allows the user to rotate furniture 90 degrees.
 * @author MeghanWhitehead
 *
 */
public class Furniture implements DormGUI{

	private PApplet processing;
	private PImage image;
	private float[] position;
	private boolean isDragging;
	private int rotations;
	private String type;
	
	/**
	 * The furniture constructor is used to initialize each variable of the class
	 * @param furniture, differentiates between types of furniture
	 * @param processing
	 */
	public Furniture(String furniture, PApplet processing) { 				
		// initializes processing
		this.processing = processing;
		// initializes position array with bed being in the center of the room
		this.position = new float[]{processing.width/2, processing.height/2};
		// initializes isDragging boolean
		this.isDragging = false;
		// type of furniture chosen
		this.type = furniture;
		// loads the image of specified furniture type
		this.image = processing.loadImage("images/" + this.type + ".png");
	}
	
	public Furniture(String type, float x, float y, float rotations, PApplet processing) {
		// initializes to the position specified in the file being loaded
		this.position = new float[]{x, y};
		// initializes the number of rotations specified in the file being loaded
		this.rotations = (int)rotations;
		// objects are not being dragged until they have been initialized
		this.isDragging = false;
		// initializes processing
		this.processing = processing;
		// initializes the type specified in the file being loaded
		this.type = type;	
		// loads the image based on the type specified
		this.image = processing.loadImage("images/" + this.type + ".png");	
	}
	
	/**
	 * This getter method allows the position of each furniture object to be accessible to 
	 * other methods.
	 * @return the position of the specified furniture object
	 */
	public float[] getPosition() {
		return this.position;
	}
	
	/**
	 * This getter method allows the type of each furniture object to be accessible to 
	 * other methods.
	 * @return the type of the specified furniture object
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * This getter method allows the number of rotations of each furniture object to be accessible 
	 * to other methods.
	 * @return the number of rotations of the specified furniture object
	 */
	public int getRotations() {
		return this.rotations;
	}

	/**
	 * The update method continuously draws the furniture images at the locations that the user has
	 * chosen to place them. This method is called each time a change, such as dragging and rotating,
	 * has been made to a furniture object.
	 */
	public void update() { 
		// draws the furniture image(s)
		for (int i = 0; i < this.position.length; i++) {
			// makes sure that there is furniture at the position
			if (this.position != null) {
				// allows the image to be rotated
				this.processing.image(this.image, this.position[0], this.position[1], 
						this.rotations*PApplet.PI/2);
			}
		}
		// draws the bed image along with the mouses position as the user is dragging it
		if (this.isDragging) {
			// the positions of the furniture will be updated to the location of the mouse
			this.position = new float[]{this.processing.mouseX, this.processing.mouseY};
			// the furniture will follow the mouse when the user is dragging the furniture
			this.processing.image(this.image, this.processing.mouseX, this.processing.mouseY, 
					this.rotations*PApplet.PI/2);
		}
	}

	/**
	 * When the user clicks the mouse, this method is called and changes the isDragging boolean to 
	 * true, which will then make the furniture image follow the mouse.
	 */
	public void mouseDown(Furniture[] furniture) {
		this.isDragging = true;
	}

	/**
	 * When the user un-clicks the mouse, this method will be called and changes the isDragging
	 * boolean to false, which will drop the furniture at the location of the mouse at that time.
	 */
	public void mouseUp() { 
		this.isDragging = false;
	}

	/**
	 * This method will check the location of the mouse and the location of the furniture, if the 
	 * mouse is over a piece of furniture, the return value will be true. If the mouse is not over
	 * a piece of furniture, the return value will be false.
	 * @return true/false
	 */
	public boolean isMouseOver() { 
		// if the image has been rotated an even number of times width aligns with x and height aligns with y
		if (this.rotations % 2 == 0) {
			if (((this.position[0] - (this.image.width/2)) <= this.processing.mouseX)
					&& ((this.position[0] + (this.image.width/2)) >= this.processing.mouseX)
					&& ((this.position[1] - (this.image.height/2)) <= this.processing.mouseY)
					&& ((this.position[1] + (this.image.height/2)) >= this.processing.mouseY)) {
				return true;
			}
		} else {
			// if the image has been rotated an odd number of times, width aligns with Y and height aligns with X
			if (((this.position[0] - (this.image.height/2)) <= this.processing.mouseX)
					&& ((this.position[0] + (this.image.height/2)) >= this.processing.mouseX)
					&& ((this.position[1] - (this.image.width/2)) <= this.processing.mouseY)
					&& ((this.position[1] + (this.image.width/2)) >= this.processing.mouseY)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 *  The following method keeps track of how many times each furniture object has been rotated.
	 *  This allows the image to be drawn in the correct way, and allows the isMouseOver() method
	 *  to correctly decide if the mouse is over a piece of furniture.
	 */
	public void rotate() { 
		this.rotations += 1;
	}
}