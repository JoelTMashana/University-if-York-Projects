import javafx.application.Application;
import javafx.stage.Stage; 
import javafx.scene.Scene;
import javafx.scene.shape.Polygon;
import javafx.scene.Group;
import java.util.ArrayList;
import javafx.scene.control.TextArea; 
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;


/**
 * Shapes
 * Interactive program that generates and 
 * manipulates shapes based on user input
 * and displays the output in a GUI
 * @author Joel Mashana
 * @version 1.0 7 Feb 2022
 */

public class Shapes extends Application {	

    /**
     * The following attributes declare the list to
     * contains the shapes, user input value and user 
     * input per action count, the maximum and minimum
     * number of sides value and the decrement and 
     * increment values
     */
	private ArrayList<Polygon> listOfShapes = new ArrayList<>();
	private int userInput;
	private int userInputCount;
	private int minimumNumberOfSides = 3;
	private int maximumNumberOfSides = 6;
	private int incrementValue = 1;
	private int decrementValue = 1;
	
	@Override 
	public void start(Stage stage)
	{		
		
		/**
		 * Polygon objects
		 */
		
		/**
		 * The coordinates for shapes are stored in a
		 * Polygon object which accepts a double array
		 * of x, y, coordinates which together 
		 * represent the position of each of the shapes
		 * vertices. Number of vertices is how the number 
		 * of sides are determined
		 */
		
		/* Create Polygon object */
		Polygon threeSidedShape = new Polygon();
		/* Coordinates for three sides shape */
		threeSidedShape.getPoints().addAll(new Double [] {
				100.0, 70.0,
				70.0, 175.0,
				175.0, 100.0
		});
		
		Polygon fourSidedShape = new Polygon();
		fourSidedShape.getPoints().addAll(new Double [] {
				100.0, 150.0,
				170.0, 150.0,
				170.0, 90.00,
				100.0, 90.00
		});
		
		Polygon fiveSidedShape = new Polygon();
		fiveSidedShape.getPoints().addAll(new Double [] {
				200.0, 50.0,
				400.0, 50.0,
				450.0, 150.0,
				400.0, 250.0,
				200.0, 250.0
		});
		
		Polygon sixSidedShape = new Polygon();
		sixSidedShape.getPoints().addAll(new Double [] {
				200.0, 50.0,
				400.0, 50.0,
				450.0, 150.0,
				400.0, 250.0,
				200.0, 250.0,
				150.0, 150.0
		});
		
		/**
		 * Polygons added to list in order of size
		 * to allow for easy dynamic indexing via 
		 * the user input
		 */
		listOfShapes.add(threeSidedShape);
		listOfShapes.add(fourSidedShape);
		listOfShapes.add(fiveSidedShape);
		listOfShapes.add(sixSidedShape);
		
		
		/**
		 * Graphical User Interface
		 */
		
		/* Group to hold the active shape */
		Group theActiveShape = new Group();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		
		/* Create and configure area to display messages */
		TextArea errorAndPromptMesssageTextArea = new TextArea();
		errorAndPromptMesssageTextArea.setEditable(false);
		errorAndPromptMesssageTextArea.setMaxWidth(288);
		errorAndPromptMesssageTextArea.setMaxHeight(75);
		
		/* Create and configure text field for user input*/
		TextField textInputField = new TextField();         
		textInputField.setMinSize(210,30);         
		textInputField.setMaxSize(210,30);

		/*
		 * Create and configure buttons that will decrease and 
		 * increase the sides of the active shape
		 */
		Button decrementNumberOfSidesButton = new Button();
		decrementNumberOfSidesButton.setText("-1");
		Button incrementNumberOfSidesButton = new Button();
		incrementNumberOfSidesButton.setText("+1");

		HBox inputComponents = new HBox(10);
		inputComponents.setAlignment(Pos.CENTER);
		inputComponents.getChildren().addAll(decrementNumberOfSidesButton, textInputField, incrementNumberOfSidesButton);  
		
		/* Configure the layout of the grid */
		grid.add(theActiveShape, 0, 0, 1, 1);
		grid.add(errorAndPromptMesssageTextArea, 0, 1, 1, 1);
		grid.add(inputComponents, 0, 2, 1, 1);
		
		grid.setHgap(10);
		grid.setVgap(10);
		
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		VBox root = new VBox(25);
		root.setAlignment(Pos.BOTTOM_CENTER);        
		root.getChildren().addAll(grid);
		
		
		/**
		 * Component One
		 */
		
		/* Initial prompt for user input */
		errorAndPromptMesssageTextArea.setText("Message: Please enter an integer value between 3 and 6 inclusive "
											  + "in the input box below.");
		textInputField.setOnKeyReleased( generateInitialShapeEvent -> {					
				
			    /* For testing purposes */
		        resetUserInputCountToZero();
					
				if (theActiveShape.getChildren().size() > 0) {
					theActiveShape.getChildren().clear();
				} else {
					/* do nothing */
				}		
									   			
				if(textInputField.getText().isEmpty()) {
					errorAndPromptMesssageTextArea.setText("Message: Please enter an integer value between 3 and 6 inclusive "
	                                                      + "in the input box below.");
				} else {
					
					/**
					 * Convert string to integer, validate input
					 * handle exceptions and generate shape
					 * on success 
					 * */
				    try {	
				    	setUserInput(Integer.parseInt(textInputField.getText()));
				    	
				    	/* Test */
				    	setUserInputTest(userInput);

				    	if(isValid(userInput)) {
				    		isValidTest(isValid(userInput));
				    		/* Clear message after each event */
				    	    errorAndPromptMesssageTextArea.clear();
				    	       							   
							try {					    	
							    
							    try {	
							        
							    	/* Index list of shapes minus three to account of 
							    	 * shapes starting at value 3 and the 0 index
							    	 */
							    	Polygon initialShape = listOfShapes.get(userInput - 3);
							    	//Key release increases user input count value by 1
							    	incrementUserInputCount();
							    	theActiveShape.getChildren().addAll(initialShape);
								    	
								    if(isEven(userInput)) {
								    	initialShape.setFill(Color.GREY);
								    } else {
								    	initialShape.setFill(Color.BLUE);
								    }
								    			
								    /* Tests */	
							    	isEvenTest(isEven(userInput));
							    	testShapeColour(isEven(userInput), initialShape.getFill());
							    	testUserInputCount(userInputCount);
							    	testShapeExpectedNumberOfSides(initialShape.getPoints().size());
							    	testNumberOfShapesCurrentlyDisplayed(theActiveShape.getChildren().size());										    	
								    	
							    	} catch (NullPointerException error) {
							    		/* Print details of why error
							    		 * occurred and valid actions available  */
							    		errorAndPromptMesssageTextArea.setText("Error: Invalid input. This application currently supports shapes with"
			    								                              + " between 3 and 6 inclusive number of sides. Please try again.");
							    	    System.out.println("NullPointerException caught");
								    	System.out.println(error);
							    	}
							    
							    } catch (IndexOutOfBoundsException error) {
							    	errorAndPromptMesssageTextArea.setText("Error: Invalid input. This application currently supports shapes with"
							    								          + " between 3 and 6 inclusive number of sides. Please try again.");
							    	System.out.println("IndexOutOfBoundsException caught");
							    	System.out.println(error);
							    }
							
						    } else {
						        errorAndPromptMesssageTextArea.setText("Error: Invalid input. You must enter a number between 3 an 6 inclusive. Please try again.");
							    /* Test */
							    isValidTest(isValid(userInput));
						    }
				    	
				    	} catch (NumberFormatException error) {
				    		errorAndPromptMesssageTextArea.setText("Error: Invalid input. You must enter and integer "
				    									+ "between 3 and 6 inclusive. Please try again");
				    		System.out.println("NumberFormatException caught");
				    		System.out.println(error);
				    	}
				    	
				    }
				    
					/**
					 * Component two
					 */

					decrementNumberOfSidesButton.setOnAction( event -> {
				    	    resetUserInputCountToZero();
				    	    
				    	    /**
				    	     * Check user input is valid and more than three
				    	     * to avoid IndexOutOfBoundsException 
				    	     */
				    	     
				    	    if(isValid(userInput) && userInput > minimumNumberOfSides) {
				    		    /* Clear previous shape */
				    		    theActiveShape.getChildren().clear();
				    		    try {	
				    			    errorAndPromptMesssageTextArea.clear();
					    		    /* Reset increment button border colour */
				    			    incrementNumberOfSidesButton.setStyle("-fx-border-color: none;");
				    			    
				    			    try {
				    				    /**
				    				     * 
				    				     */
				    			    	Polygon previousShape = listOfShapes.get(userInput - (3 + decrementValue));
				    				    incrementUserInputCount();
				    				    theActiveShape.getChildren().add(previousShape);				    			

					    			    if(isEven(userInput - decrementValue)) {
					    			    	previousShape.setFill(Color.GREY);
					    			    } else {
					    			    	 previousShape.setFill(Color.BLUE);
					    			    } 
					    					    			
					    			    decrementUserInput();
					    			    
					    			    /* Tests */
					    			    isEvenTest(isEven(userInput));
					    			    testShapeColour(isEven(userInput), previousShape.getFill());
					    			    decrementUserInputTest(userInput);
					    			    testUserInputCount(userInputCount);
					    			    testShapeExpectedNumberOfSides(previousShape.getPoints().size());
					    			    testNumberOfShapesCurrentlyDisplayed(theActiveShape.getChildren().size());
					    			
				    			} catch (NullPointerException error) {
				    		        errorAndPromptMesssageTextArea.setText("Error: Invalid input. This application currently supports shapes with"
		    								                              + " between 3 and 6 inclusive number of sides. Please try again.");
						    		System.out.println("NullPointerException caught");
							    	System.out.println(error);
				    			}
				    			    
				    		} catch (IndexOutOfBoundsException error) {
				    			errorAndPromptMesssageTextArea.setText("Error: Invalid input. This application currently supports shapes with"
						    								          + " between 3 and 6 inclusive number of sides. Please try agian.");
						    	System.out.println("IndexOutOfBoundsException caught");
						    	System.out.println(error);
				    		}
				    		    
				    	} else {
				    	    errorAndPromptMesssageTextArea.setText("Error: Invalid input. This application currently supports shapes with"
    								                              + " a minimum of 3 sides. Please increment.");
				    		/**
				    		 * Set button border colour to red 
				    		 * to indicate inactive state
				    		 */
				    	    decrementNumberOfSidesButton.setStyle("-fx-border-color: red;");
				    	}				    	
				    });
				    
					/**
					 * Component Two continued
					 */
					incrementNumberOfSidesButton.setOnAction( event -> {
		
				    	    resetUserInputCountToZero();
				    	    
				    	    if(isValid(userInput) && userInput < maximumNumberOfSides) {
				    		    
				    	    	theActiveShape.getChildren().clear();
				    		    
				    		    try {	
				    		    	
				    			    decrementNumberOfSidesButton.setStyle("-fx-border-color: none;");
				    			    errorAndPromptMesssageTextArea.clear();	
				    			    
				    			    try {
				    				    Polygon nextShape = listOfShapes.get(userInput - (3 - incrementValue));
				    				    incrementUserInputCount();

				    				    theActiveShape.getChildren().add(nextShape);					    			
						    		    
						    		    if(isEven(userInput + incrementValue)) {
						    		    	nextShape.setFill(Color.GREY);
						    		    } else {
						    		    	nextShape.setFill(Color.BLUE);
						    		    }
						    			
						    		    incrementUserInput();
						    		    
						    		    /* Test */
						    		    incrementUserInputTest(userInput);
						    		    isEvenTest(isEven(userInput));
						    		    testShapeColour(isEven(userInput), nextShape.getFill());
						    		    testUserInputCount(userInputCount);
						    		    testShapeExpectedNumberOfSides(nextShape.getPoints().size());
						    		    testNumberOfShapesCurrentlyDisplayed(theActiveShape.getChildren().size());
						    		    
				    			} catch (NullPointerException error) {
				    			    errorAndPromptMesssageTextArea.setText("Error: Invalid input. This application currently supports shapes with"
		    								                              + " between 3 and 6 inclusive number of sides. Please try again.");
						    		System.out.println("NullPointerException caught");
							    	System.out.println(error);
				    			}
				    			
				    		} catch (IndexOutOfBoundsException error) {
				    			errorAndPromptMesssageTextArea.setText("Error: Invalid input. This application currently supports shapes with"
						    								          + " between 3 and 6 inclusive number of sides. Please try agian.");
						    	System.out.println("IndexOutOfBoundsException caught");
						    	System.out.println(error);
				    		}
				    	} else {
				    	    errorAndPromptMesssageTextArea.setText("Error: Invalid input. This application currently supports shapes with"
    								                              + " a maximum of 6 sides. Please decrement.");
				    		incrementNumberOfSidesButton.setStyle("-fx-border-color: red;");
				    	}			    
				    });
			});
		
	   
	    
		
		/**
		 * Configure scene, set scene, 
		 * set title and show stage
		 */
		Scene scene = new Scene(root, 400, 400);         
		stage.setScene(scene);         
		stage.setTitle("Shapes");        
		stage.show();
	}
	
	/**
	 * Methods
	 */
	
	/**
	 * Purpose: Sets user input value
	 * @param userInputIn
	 */
	public void setUserInput(int userInputIn)
	{
	    userInput = userInputIn;
	}
	
	/**
	 * Purpose: Check if user input is even
	 * @param userInputIn
	 * @return boolean
	 */
	public boolean isEven(int userInputIn)
	{		
		return userInputIn % 2 == 0 ? true : false;	
	}
	
	
	/**
	 * Purpose: Check if user input is valid	
	 * @param userInputIn
	 * @return boolean
	 */
	public boolean isValid(int userInputIn)
	{
		return (userInputIn >= minimumNumberOfSides) && (userInputIn <= maximumNumberOfSides);
	}
	
	/**
	 * Purpose: Overloaded, when new shapes added
	 * limit of 6 can be lifted
	 * @param userInputIn
	 * @param placeNoUpperLimitOnNumberOfShapeSidesIn
	 * @return boolean
	 */
	public boolean isValid(int userInputIn, boolean placeNoUpperLimitOnNumberOfShapeSidesIn)
	{
		if(placeNoUpperLimitOnNumberOfShapeSidesIn == true)
			//Shape must still have at least 3 three sides
			return userInputIn >=3;
		else 
			return false;
	}
	
	/**
	 * Purpose: decrements user input value,
	 * @return next shape number of sides
	 */
	public int decrementUserInput()
	{
		return userInput--;
	}
	
	
	/**
	 * Purpose: increments user input value,
	 * @return next shape number of sides
	 */
	public int incrementUserInput()
	{
		return userInput++;
	}
	

	/**
	 * Purpose: increments user input count
	 * to zero, for testing purposes
	 * to see how many inputs are associated
	 * with each action
	 */
	public void incrementUserInputCount()
	{
		userInputCount++;
	}
	
	/**
	 * Purpose: resets user input count
	 * to zero, for testing purposes
	 * to see how many inputs are associated
	 * with each action
	 */
	public void resetUserInputCountToZero()
	{
		userInputCount = 0;
	}

	/**
	 *  Test Methods
	 */
	
	
	//Test should show expected output from isValid method
	/**
	 * Purpose: test isValid method
	 * @param isValidExpecteOutputIn
	 */
	public void isValidTest(boolean isValidExpecteOutputIn)
	{		
		System.out.println("Input valid: " + isValidExpecteOutputIn);
	}
	
	/**
	 * Purpose: Test is even method
	 * @param isEvenActualOutputIn
	 */
	public void isEvenTest(boolean isEvenActualOutputIn)
	{
		System.out.println("Input even: " + isEvenActualOutputIn);
	}
	
	/**
	 * Test setUserInputMethod
	 * @param expectedUserInputIn
	 */
	public void setUserInputTest(int expectedUserInputIn)
	{
		System.out.println("Initial user input: " + expectedUserInputIn);
	}

	/**
	 * Purpose: Test decrementUserInputMethod
	 * @param expectedDecrementUserInputValueIn
	 */
	public void decrementUserInputTest(int expectedDecrementUserInputValueIn)
	{
		System.out.println("After decrement, user input value is equal to: " + expectedDecrementUserInputValueIn);
	}
	
	/**
	 * Purpose: Test incrementUserInputMethod
	 * @param expectedIncrementCounterValueIn
	 */
	public void incrementUserInputTest(int expectedIncrementCounterValueIn)
	{
		System.out.println("After increment, user input value is equal to: " + expectedIncrementCounterValueIn);
	}
	
	/**
	 * Purpose: Test incrementUserInputCount and
	 * resetUserInputCount methods
	 * @param userInputCountIn
	 */
	public void testUserInputCount(int userInputCountIn)
	{
		System.out.println("The shape was succesfully generated or manipulated after " + userInputCountIn + " user input(s)");
	}
	
	/**
	 * Purpose: Test the number of sides active
	 * shape has by calculating the number
	 * of vertices
	 * @param numberOfCoordinatesIn
	 */
	public void testShapeExpectedNumberOfSides(int numberOfCoordinatesIn)
	{
		int numberOfVertices = numberOfCoordinatesIn / 2;
		System.out.println("The current shape has " + numberOfVertices + " sides");
	}
	
	/**
	 * Purpose: Test the number of shapes 
	 * displayed per user input
	 * @param expectedNumberOfShapesIn
	 */
	public void testNumberOfShapesCurrentlyDisplayed(int expectedNumberOfShapesIn)
	{
		System.out.println("There is currently " + expectedNumberOfShapesIn +" shape(s) being displayed");
	}
	
	/**
	 * Purpose: Test shape has correct colour 
	 * and that shape has one colour
	 * at a time
	 * @param resultOfIsEven
	 * @param hexCodeIn
	 */
	public void testShapeColour(boolean resultOfIsEven, Paint hexCodeIn)
	{
		if (resultOfIsEven == true) {
			System.out.println("The current shape has an even number of sides therefore the colour is " +
								hexCodeIn + ", where Grey = 0x808080ff and Blue = 0x0000ffff");
		} else if (resultOfIsEven == false) {
			System.out.println("The current shape has an odd number of sides therefore the colour is " +
								hexCodeIn + ", where Grey = 0x808080ff and Blue = 0x0000ffff");
		}
	}	
	
	public static void main(String[] args) {
		launch(args);	
	}
}
