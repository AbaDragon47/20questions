import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//import QuestionsGame.QuestionNode;

public class questionTreeTester
{
    QuestionNode overallRoot;
    public static void main(String[] args) throws IOException{
        questionTreeTester tester = new questionTreeTester();
        BinaryTreePrinter printer = new BinaryTreePrinter();

        Scanner file = new Scanner(new File("spec-questions.txt"));
        printer.printPreOrder(System.out, tester.treeMaker(file)); 
    }

    public QuestionNode treeMaker(Scanner input) 
    {
    	input.nextLine();
    	
    	overallRoot= new QuestionNode(input.nextLine());
    	//QuestionNode first=overallRoot;
    	//first= new QuestionNode();
    	
    	addQues(input, overallRoot);
    	return overallRoot;
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
				addQues(input, overallRoot);
			}
			
		}
	}
}
class QuestionNode
{
    //Paste your QuestionNode code here
        /* My code is assuming nodes have data, left and right */
	String data;
	QuestionNode left, right;
	
	public QuestionNode(String input){
		data= input;
	}
	public QuestionNode() {
		data="";
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

class BinaryTreePrinter {

    public BinaryTreePrinter() {}
    
    //Assumes your nodes have data, left and right
	private String traversePreOrder(QuestionNode root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.data);
        
        String pointerRight ="[R]--";
        String pointerLeft = (root.right != null) ? "[L]--" : "[L]--";

        traverseNodes(sb, "", pointerLeft, root.left, root.right != null);
        
        traverseNodes(sb, "", pointerRight, root.right, false);

        return sb.toString();
    }
    private void traverseNodes(StringBuilder sb, String padding, String pointer, QuestionNode node, boolean hasRightSibling) {

        if (node != null) {

            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.data);

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("|    ");
            } else {
                paddingBuilder.append("     ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "[R]--";
            String pointerLeft = (node.right != null) ? "[L]--" : "[L]--";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.left, node.right != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.right, false);

        }

    }

    public void printPreOrder(PrintStream os, QuestionNode overallRoot) {
        os.print(traversePreOrder(overallRoot));
    }

	
}