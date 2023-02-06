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
		input.nextLine();
    	
    	first= new QuestionNode(input.nextLine());
    	//QuestionNode first=overallRoot;
    	//first= new QuestionNode();
    	
    	addQues(input, first);
	}
	public void saveQuestions(PrintStream output) throws FileNotFoundException {
		if(output==null) {
			throw new FileNotFoundException("There was no file");
		}
		output.print(first.printInOrder(first));
	}
	public void addQues(Scanner input, QuestionNode root) {
		QuestionNode temp= root;
		if(!input.hasNext()) {
			return;
			//stop method
		}
		String letter=input.nextLine();
		if(temp==null) {
			//if first thing
			String word=input.nextLine();
			temp=new QuestionNode(word);
			addQues(input,temp.left);
		}
		if(letter.equals("Q:")) {
			String word=input.nextLine();
			if(temp.left!=null) {
				temp.right= new QuestionNode(word);
				addQues(input,temp.right);
			}
				
			else {
				temp.left=new QuestionNode(word);
				addQues(input, temp.left);
			}

		}
		else if(letter.equals("A:")) {
			if(temp.left==null) {
				temp.left= new QuestionNode(input.nextLine());
				addQues(input,temp);
			}
				
			else {
				temp.right=new QuestionNode(input.nextLine());
				addQues(input, first);
			}
			
		}
	}
	

	public void play() {
		QuestionNode tempGame=first;
		Scanner game= new Scanner(System.in);
		System.out.println("Hi! Lets play!");
		
		while(tempGame.left!=null&& tempGame.right!=null) {
			System.out.println(tempGame.data);
			String ans=game.nextLine();
			if(ans.toUpperCase().charAt(0)=='N') 
				tempGame=tempGame.right;
			
			else if(ans.toUpperCase().charAt(0)=='M'||ans.toUpperCase().charAt(0)=='Y')
				tempGame=tempGame.left;
			else
				System.out.println("Sorry not a valid answer, please choose [Y]es or [N]o");
			
		}
		System.out.println("I have an answer! "+tempGame.data+"\n\nIs this right?");
		if(game.nextLine().toUpperCase().charAt(0)=='N') {
			tempGame.right= new QuestionNode(tempGame.data);
			System.out.println("What were you thinking of?");
			tempGame.left= new QuestionNode(game.nextLine());
			System.out.println("Whats a better question to get that answer?");
			tempGame.data=game.nextLine();
			System.out.println("Thanks!");
			
		}
		else
			System.out.println("YAYYYY! You got 2 points. Remember.... ");
		
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
    	

    	private String printInOrder(QuestionNode root){
        	String ans="";
        	if(root ==null)
        		return"";
            ans+=root.left==null? "A:\n"+root.data+"\n": "Q:\n"+root.data+"\n";
            ans+= printInOrder(root.left);
            ans+= printInOrder(root.right);
            return ans;
        }
    	
    	
    	
    }
}
