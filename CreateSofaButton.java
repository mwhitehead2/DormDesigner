//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P4 Dorm Designer 4000
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
 * The CreateSofaButton class will draw a button on the screen that allows the user to click on it
 * to add a sofa to the room. The button will become a darker shade of gray when the mouse is on 
 * top of it. When it is clicked, it will create a new sofa object and pass it to the Furniture 
 * class.
 * @author MeghanWhitehead
 *
 */
public class CreateSofaButton extends Button implements DormGUI{
	private static final int WIDTH = 96; // final width of the sofa button
	private static final int HEIGHT = 32; // final height of the sofa button
	private PApplet processing;
	private float[] position; // array used to create the position of the button within the room
	private String label; // this will be the text displayed on the button
	 
	/**
	 * This constructor method will set the position array to have indexes that represent the 
	 * position of the sofa button on the screen. It creates a label to be printed on the sofa
	 * button.
	 * @param x, x axis position of button
	 * @param y, y axis position of button
	 * @param processing
	 */
	public CreateSofaButton(float x, float y, PApplet processing) {
		super(x, y, processing);
		label = "Create Sofa"; // will be displayed on the button
		position = new float[]{x, y};
		this.processing = processing;
	}
	/**
	 * The update method will draw the sofa button on the screen, with text. When the mouse is 
	 * over the button, the color will become darker.
	 */
	public void update() { 
		// creates a gray rectangle with black text in the center
		processing.fill(200); // gray fill
		processing.rect(102, 8, 198, 40); // position of rectangle
		processing.fill(0); // black text
		processing.text(label, 150, 24); // position the text is centered around 
		
		// When the mouse is over, the button becomes darker but stays in the same position with 
		// the same words
		if (isMouseOver()) {
			processing.fill(100); // darker gray fill
			processing.rect(102, 8, 198, 40);
			processing.fill(0);
			processing.text(label, 150, 24);
		}
	}	
	
	/**
	 * This method will determine if the mouse is over the button by using the position of the 
	 * button and the position of the mouse. If the mouse is over the button, it will return true
	 * and vice versa.
	 * @return true if the mouse is over the bed button
	 * @return false if the mouse is not over the bed button
	 */
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

	/**
	 * This method will create a new Furniture object when the user clicks on the sofa button
	 * and sends the references to the Furniture class to make changes to.
	 * @return the new furniture object that has been created
	 */
	@Override
	public void mouseDown(Furniture[] furniture) {
		for (int i = 0; i < furniture.length; i++) {
			// This will update the positions of furniture in the furniturePositions array by 
			// using the getter methods in the Furniture class
			if (furniture[i] == null) {
				Furniture sofa = new Furniture("sofa", processing);
				furniture[i] = sofa;
			}
		}
	}
	
	public void mouseUp(){}
}