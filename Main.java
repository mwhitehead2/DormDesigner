import java.util.ArrayList;
import java.util.Arrays;

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
 * The class named 'Main' is used to create a bedroom, and allows the user to add up to 
 * six pieces of furniture, including a bed and a sofa, that can be moved around. 
 * @author meghanwhitehead
 *
 */
public class Main {
	
	private PApplet processing;
	private PImage backgroundImage;
	private ArrayList<DormGUI> guiObjects;
	// Max number of furniture that LoadButton will be allowed to load at once.    
	private final static int MAX_LOAD_FURNITURE = 100; 

	
	public Main(PApplet processing) {
		// loads the background image
		this.backgroundImage = processing.loadImage("images/background.png");
		// initializes processing
		this.processing = processing;
		
		// creates an ArrayList for objects to be stored in
		guiObjects = new ArrayList<DormGUI>();
		
		// creates objects for the buttons displayed on the screen
		guiObjects.add(new LoadButton(750, 24, processing));
		guiObjects.add(new SaveButton(650, 24, processing));
		guiObjects.add(new ClearButton(550, 24, processing));
		guiObjects.add(new CreateFurnitureButton("bed", 150, 24, processing));
		guiObjects.add(new CreateFurnitureButton("sofa", 50, 24, processing));
		guiObjects.add(new CreateFurnitureButton("dresser",250, 24, processing));
		guiObjects.add(new CreateFurnitureButton("desk", 350, 24, processing));
		guiObjects.add(new CreateFurnitureButton("sink", 450, 24, processing));
	}

	/**
	 * The main method is used to call another method, Utility.startApplication(),
	 * that will automatically run the rest of the program for us. 
	 * @param args
	 */
	public static void main(String[] args) {
		// begins and runs the program
		Utility.startApplication();
	}
	     
	/**
	 * This method creates a new Furniture[] for the old mouseDown() methods
	 * to make use of.  It does so by copying all Furniture references from
	 * this.guiObjects into a temporary array of size MAX_LOAD_FURNITURE.
	 * @return that array of Furniture references.
	 */
	private Furniture[] extractFurnitureFromGUIObjects() {
	    Furniture[] furniture = new Furniture[MAX_LOAD_FURNITURE];
	    int nextFreeIndex = 0;
	    for(int i=0;i<guiObjects.size() && nextFreeIndex < furniture.length;i++)
	        if(guiObjects.get(i) instanceof Furniture)
	            furniture[nextFreeIndex++] = (Furniture)guiObjects.get(i);        
	    return furniture;        
	}    
	/**
	 * This method first removes all Furniture references from this.guiObjects,
	 * and then adds back in all of the non-null references from it's parameter.
	 * @param furniture contains the only furniture that will be left in 
	 *   this.guiObjects after this method is invoked (null references ignored).
	 */
	private void replaceFurnitureInGUIObjects(Furniture[] furniture) {
	    for(int i=0;i<guiObjects.size();i++)
	        if(guiObjects.get(i) instanceof Furniture)
	            guiObjects.remove(i--);
	    for(int i=0;i<furniture.length;i++)
	        if(furniture[i] != null)
	            guiObjects.add(furniture[i]);
	}
	
	/**
	 * The update() method is what updates the image of the dorm room as the user tells it to
	 * change. It will change the bedPositions array and the furniture array based on how many 
	 * beds the user has specified. If the user wishes to drag the bed or sofa, the bed or sofa
	 *  will follow along with the mouse once the mouse is clicked, and then let go of the bed 
	 *  when the mouse is unclicked.
	 * @param takes no parameters
	 */
	public void update() {	
		// sets background color to blue
		processing.background(100,150,250);
		
		// draws the background image
		processing.image(backgroundImage, processing.width/2, processing.height/2);
		
		// updates the furniture and button objects to continuously draw them
		for (int i = 0; i < guiObjects.size(); i++) {
			if (guiObjects.get(i) != null) {
				guiObjects.get(i).update();
			}
		}
	}
	/**
	 * The mouseDown() method will determine if the mouse is within bounds of a piece of furniture 
	 * or button when the user clicks. It does so by calling the .isMouseOver() method from all 
	 * three of the other classes. If the mouse is not over a piece of furniture or button, nothing 
	 * will happen. If the mouse is over a button, and the user clicks, it will call the .mouseDown() 
	 * method to add anther piece of furniture and adds its position to the furniture array.
	 * @param takes no parameters
	 */
	public void mouseDown() {	
		Furniture[] furniture = {null}; // creates a furniture array
		/*
		 *  The following for loop will go through each object that has been created to see if when
		 *  the mouse is clicked, it is over one of those objects. Then, a helper method is called
		 *  to extract all furniture objects from the arrayList. These objects are then added to the
		 *  furniture array, and the furniture objects are deleted from the arrayList.
		 */
		for (int j = 0; j < guiObjects.size(); j++) {
			if (guiObjects.get(j) != null && guiObjects.get(j).isMouseOver()) {
				furniture =  extractFurnitureFromGUIObjects();
				guiObjects.get(j).mouseDown(furniture);
				guiObjects.add(furniture[j]);
				replaceFurnitureInGUIObjects(furniture);
				break;
			}
		}
	}

	/**
	 * The mouseUp() method will be called when the user releases the mouse. The .mouseUp()
	 * method will be called so that no bed will be following the mouse. This results in the furniture 
	 * that was being dragged to be dropped in the location of the mouse when it was unclicked.
	 * @param takes no parameters
	 */
	public void mouseUp() {
		// iterates through each furniture piece and drops it in the location of the mouse 
		for (int i = 0; i < guiObjects.size(); i++) {
			if (guiObjects.get(i) instanceof Furniture) {
				guiObjects.get(i).mouseUp();
			}
		}
	}
	/**
	 * The keyPressed() method detects when the user has pressed a key on the keyboard. If 
	 * the key that was pressed is a 'd' or 'D', then furniture will be deleted from the 
	 * room. If the user presses either 'r' or 'R", then the furniture will be rotated 90 degrees.
	 * If any other key is pressed, nothing will happen. 		
	 * @param takes no parameters
	 */
	public void keyPressed() {
	
		// checks the key that is pressed, and deletes furniture if the user presses 'd' or 'D'
		if (processing.key == 'd' || processing.key == 'D') {
			// cycles through each index of the bedPositions array to see if the mouse is over furniture
			for (int i = 0; i < guiObjects.size(); i++) {
				if (guiObjects.get(i) != null) {
					if (guiObjects.get(i) instanceof Furniture) {
						// changes the index to null so that furniture will no longer appear
						if (guiObjects.get(i).isMouseOver()) {
							guiObjects.set(i, null); 
							break;
						}
					}
				}
			}
		}
		if (processing.key == 'r' || processing.key == 'R') {
			// cycles through each index of the bedPositions array to see if the mouse is over a bed
			for (int i = 0; i < guiObjects.size(); i++) {
				if (guiObjects.get(i) != null ) {
					// calls the .rotate() method that will change the position of the furniture	
					if (guiObjects.get(i).isMouseOver()) {
						if (guiObjects.get(i) instanceof Furniture) {
							((Furniture) guiObjects.get(i)).rotate();
							break;
						}
					}
				}

			}
		}
	}
}