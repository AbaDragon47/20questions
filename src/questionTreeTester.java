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
    	overallRoot = new QuestionNode(input.nextLine());
    	overallRoot.addQues(input, overallRoot);//Paste your QuestionGame constructor code here and return your overall Root once the tree is made.
    	return overallRoot;
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
	

	public void addQues(Scanner input, QuestionNode root) {
		QuestionNode temp= root;
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