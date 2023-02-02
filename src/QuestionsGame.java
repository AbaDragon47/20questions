import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

// This is a starter file for QuestionsGame.
//
// You should delete this comment and replace it with your class
// header comment.

public class QuestionsGame {
	
	static // class that reprensent tree of Q&As
    // Your code here
	QuestionNode first;
	public QuestionsGame(String object) {
		first= new QuestionNode(object);
	}
	public QuestionsGame(Scanner input) throws FileNotFoundException{
		//should add leaves and branches
		//put in here
		first.addQues(input, first);
	}
	public void saveQuestions(PrintStream output) throws FileNotFoundException {
		if(output==null) {
			throw new FileNotFoundException("There was no file");
		}
		output.print(first.printInOrder(first));
	}
	

    private static class QuestionNode {
        // Your code here
    	//basic normal node and its left's and right's access throu true and falses
    	// and answer node wouldnt have a left or right function==> would be a leaf
    	String data;
    	QuestionNode left, right;
    	
    	private QuestionNode(String input){
    		data= input;
    	}
    	private QuestionNode() {
    		data="";
    	}
    	
   
    	private void addQues(Scanner input, QuestionNode root) {
    		QuestionNode temp= first;
    		if(!input.hasNext()) {
    			return;
    		}
    		if(input.next().equals("Q:")) {
    			if(temp==null)
    				temp=new QuestionNode(input.nextLine());
    			else {
    				temp.left=new QuestionNode(input.nextLine());
    				addQues(input, temp.left);
    			}
    		}
    		else if(input.next().equals("A:")) {
    			if(temp.left==null)
    				temp.left= new QuestionNode(input.nextLine());
    			else {
    				temp.right=new QuestionNode(input.nextLine());
    			}
    			addQues(input,temp.right);
    		}
    	}
    	private String printInOrder(QuestionNode root){
        	String ans="";
        	if(root ==null)
        		return "this root is null";
            ans+=root.left==null? "A:\n"+root.data+"\n": "Q:\n"+root.data+"\n";
            ans+= printInOrder(root.left);
            ans+= printInOrder(root.right);
            return ans;
        }
    	
    	
    	
    }
}
