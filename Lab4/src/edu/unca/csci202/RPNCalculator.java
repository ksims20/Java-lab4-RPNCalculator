package edu.unca.csci202;

	import java.util.Scanner;
	import java.util.Stack;
	import java.util.EmptyStackException;
		
	//@Author Kevin Sanft / Kamren Sims
	//A class that is a calculator for RPNs uses stacks to hold the operators/operands. Performs operations based on user input.
	
	
	public class RPNCalculator {

	    private Stack<Double> calcStack;
	    
	    
	    public RPNCalculator() {
	        calcStack = new Stack<Double>();
	        
	    }

	    public void run() throws OperandsException {
	        Scanner inputLineReader = new Scanner(System.in);
	        String line = "";
	        boolean quit = false;
	        
	        while (!quit) {
	            while (line.equals("")) {
	                System.out.print(":::> ");
	                line = inputLineReader.nextLine().trim();
	            }
	            quit = interpretExpression(line);
	            //if proper expression, result will be only element on stack
	            printResult(quit);
	            line = "";
	        }
	    
	    }
	    
	    //takes a user inputed string, decides whether its a operator or an operand. Depending on the user input performs the operation.
	    
	    private boolean interpretExpression(String line) {
	    	ClearStack();
	        Scanner lineParser = new Scanner(line);
	        String token = "";
	        boolean quit = false;
	        double operand = 0.0;
       
	        while (lineParser.hasNext()) {
	            token = lineParser.next();
	       //     System.out.println("processing token "+token);          
	           
	            if (token.equals("q")) { 
	                quit=true;
	                break;
	               } 
	          	            
	     if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
	          try {
	            	double b = calcStack.pop();  //if token contains one of the above strings, it completes the operations below
	        		double c = calcStack.pop();          		
	        		   switch (token) {
            	    	case   "+"  :  calcStack.push(c+b); break;  
            	    	case   "-"  :  calcStack.push(c-b); break;
            	    	case   "*"  :  calcStack.push(c*b); break;
            	    	case   "/"  :  calcStack.push(c/b); break;
	        		   }      		   
	            } catch(EmptyStackException e) {
	            	System.out.println("Invalid input: not enough operands"); //catches if they aren't any operands on the calcStack
        			calcStack.push(Double.NaN);
	            } 
	            }else {
	            	try {
	            	operand = Double.parseDouble(token);  //Tries to turn that string into a double, if it's not a number it's an invalid character
  	                calcStack.push(operand);
	            	} catch (Exception e) {
	            		calcStack.clear();
	            		System.out.println("Invalid input: unrecognized character"); //catches if token is not a number
	            		calcStack.push(Double.NaN);
	            		break;
	            	}
	            }          
	        }
	        return quit;
	    }
	    
	    //Clears the stack, no parameters.
	    
	    public void ClearStack() {
	    	calcStack.clear();
	    }
	    
	    //Prints the result of what's on top of the stack,if the user doesn't type in "q".
	    //Parameters = quit. 
	    
	    private void printResult(boolean quit) {
	    	if(calcStack.size() > 1) {			//The calcStack shouldn't be more than 2, if it is then the user type in too many operands.
	        	System.out.println("Invalid input: too many operands");
	        	calcStack.clear();	
	        	calcStack.push(Double.NaN);
	        }
	    	
	    	if (!quit) {
	            System.out.println(calcStack.pop());
	        }
	        
	       
	    
	    }

	    public static void main(String [] args) throws OperandsException {
	        RPNCalculator calc = new RPNCalculator();
	        calc.run();
	        
	    }

	}
	
