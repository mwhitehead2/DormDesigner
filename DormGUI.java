
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
 * The following interface will help to run the program by calling the shared methods
 * within the button class
 * @author MeghanWhitehead
 *
 */
public interface DormGUI {
	public void update();
	public void mouseDown(Furniture[] furniture);
	public void mouseUp();
	public boolean isMouseOver();
}
