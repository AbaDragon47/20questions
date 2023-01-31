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
		
	}
	public QuestionsGame(Scanner input) {
		//should add leaves and branches
		QuestionNode temp= first;
		if(input.next().equals("Q:")) {
			temp.left=temp.addQues(input.next(), temp.left);
			//keep going left
		else
			
		}
		
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
    	private QuestionNode addAnswer(String input, QuestionNode root ) {
    		
    		
    	}
    	public QuestionNode addQues(String input, QuestionNode root) {
    		root= new QuestionNode(input);
    		return root;
    	}
    	
    }
}
