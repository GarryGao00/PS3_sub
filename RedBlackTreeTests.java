import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RedBlackTreeTests {
    

    public static void main(String [] args){
        int[] myarry = new int[]{10, 80, 92, 42, 28, 26, 73, 45, 41, 97};
        RedBlackTree T = new RedBlackTree();
        RedBlackNode x = new RedBlackNode(0);
        List<RedBlackNode> nodeArray = new ArrayList<>();
        for (int i = 0; i < myarry.length; i++ ) {
            x = new RedBlackNode(myarry[i]);
            T.insert(x);
            T.prop4();
            nodeArray.add(x);
        }
        T.print_tree();
        System.out.print("insertion and print tree done \n");
        for (int i = 0; i < myarry.length; i++) {
            x = nodeArray.get(i);
            T.delete(x);
            T.prop4();
        }
        T.print_tree();
    }
}

