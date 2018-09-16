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
 * The CreateFurnitureButton class will create a new piece of furniture when a furniture button
 * has been clicked. 
 * @author meghanwhitehead
 *
 */
public class CreateFurnitureButton extends Button implements DormGUI {

	private String type; // type of furniture object to be created
	
	/**
	 * This constructor method will call the super constructor to initialize the objects position
	 * and label
	 * @param type: type of furniture object to be created
	 * @param x: x position of the button on the screen
	 * @param y: y position of the button on the screen
	 * @param processing
	 */
	public CreateFurnitureButton(String type, float x, float y, PApplet processing) {
		super(x, y, processing); // call to the super constructor
		this.type = type; // type of furniture object to be created
		super.label = "Create " + type.substring(0, 1).toUpperCase() + type.substring(1); // text on button
		super.position = new float[]{x, y}; // position of the button
		super.processing = processing;
	}
	/**
	 * Does nothing!
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * This method will create a new furniture object of the specified type, and add it to the 
	 * furniture array.
	 * @param furniture: the array that holds the furniture objects
	 */
	@Override
	public void mouseDown(Furniture[] furniture) {
		for (int i = 0; i < furniture.length; i++) {
			// This will update the positions of furniture in the furniturePositions array by 
			// using the getter methods in the Furniture class
			if (furniture[i] == null) {
				Furniture newFurniture = new Furniture(type, processing); // new furniture object
				furniture[i] = newFurniture; // furniture object added to the array
				break;
			}
		}		
	}
}


