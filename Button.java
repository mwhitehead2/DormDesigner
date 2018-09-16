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

/**
 * The Button class is the super class for all other button classes. It holds shared methods to
 * draw each button with text. When the mouse is over the button, it will become a darker shade.
 * When a button is clicked, it will print "A button was clicked" unless the child class has a 
 * method to override. 
 * @author MeghanWhitehead
 *
 */
public class Button implements DormGUI {
	protected final int WIDTH = 96; // width of each button
	protected final int HEIGHT = 32; // height of each button
	protected PApplet processing;
	protected float[] position; // array to hold the position of each button
	protected String label; // text to be printed on each button
	 
	public Button(float x, float y, PApplet processing) {
		this.label = "Button"; // default label
		this.position = new float[]{x, y}; // initializes the array to the button's position
		this.processing = processing; 
	}
	 
	public void update() {
		// creates a gray rectangle with black text in the center
		processing.fill(200); // gray fill
		processing.rect((position[0]-(WIDTH/2)), position[1]-(HEIGHT/2), position[0]+(WIDTH/2), 
				+ position[1]+(HEIGHT/2));
		//processing.rect(602, 8, 698, 40); // position of rectangle
		processing.fill(0); // black fill text
		processing.text(label, position[0], position[1]); // position text is centered around
		
		// When the mouse is over, the button becomes darker but stays in the same position with 
		// the same words
		if (isMouseOver()) {
			processing.fill(100); // darker fill gray
			processing.rect((position[0]-(WIDTH/2)), position[1]-(HEIGHT/2), position[0]+(WIDTH/2), 
					+ position[1]+(HEIGHT/2));
			processing.fill(0);
			processing.text(label, position[0], position[1]);
		}
	}
	
	// default for when a button is clicked
	public void mouseDown(Furniture[] furniture) {
		System.out.println("A button was pressed.");
	}
	
	// does not do anything, but is necessary for compilation 
	public void mouseUp() {}
	
	// checks to see where the mouse is in relation to the buttons
	public boolean isMouseOver() {
		if (processing.mouseX <= (position[0] + WIDTH/2) &&
				(processing.mouseX >= (position[0] - WIDTH/2)) &&
				(processing.mouseY <= (position[1] + HEIGHT/2)) &&
				(processing.mouseY >= (position[1] - HEIGHT/2))) {
			return true;
		}
		else {
			return false;
		}
	}
}
