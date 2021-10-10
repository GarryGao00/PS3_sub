import javax.lang.model.util.ElementScanner6;

class RedBlackNode {
    
    // Constructor
    // key should only contain integers
    public int key; // The data/key in the node
    public RedBlackNode p; // the parent node
    public RedBlackNode left; // Left child
    public RedBlackNode right; // Right child
    public Boolean color; // Node color: True -> Red, False -> Black

    /*
     * Constructor
     * RedBlackNode(int val) -> NA 
     *  val: an integer that will be stored in the node as the key
     * 
     * Creates a RBnode with the input as the key value. Assume the input is an
     * integer. Left and right children are both init to null and defult color for a
     * new node is red
     */
    RedBlackNode(int val) {
        this.key = val;
        this.left = null;
        this.right = null;
        this.color = true; // False is black, while True is red
        this.p = null;
    }
// end of RedBlackNode class
}

public class RedBlackTree{

    public RedBlackNode root;

    /*
     * Constructor
     * RedBlackTree() -> NA 
     *  no parameter 
     * Create a RedBlackTree object with a null root
     */

    public RedBlackTree() {
        this.root = null;
    }

    /*
     * leftRotate(RBTree T, RBnode x) -> NA 
     *  T: RBTree object specifying the tree function is modefiying 
     *  x: RBNode object specifying the location of left rotate
     * 
     * Moves node x down to the left. Details: x.right become x.p while x.right.left
     * become x.right
     */
    public void leftRotate(RedBlackNode x) {
        RedBlackNode y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.p = x;
        }

        y.p = x.p;

        if (x.p == null)
            this.root = y;
        else if (x == x.p.left)
            x.p.left = y;
        else
            x.p.right = y;

        y.left = x;
        x.p = y;
        //System.out.println("leftRotate done");
        return;
    }

    /*
     * rightRotate(RBTree T, RBnode y) -> NA 
     *  T: RBTree object specifying the tree function is modefiying 
     *  y: RBNode object specifying the location of right rotate
     * 
     * Moves node y down to the right. Details: y.left become y.p while y.left.right
     * become y.left
     */
    public void rightRotate(RedBlackNode y) {
        RedBlackNode x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.p = y;
        }
        x.p = y.p;
        if (y.p == null)
            this.root = x;
        else if (y == y.p.right)
            y.p.right = x;
        else
            y.p.left = x;

        x.right = y;
        y.p = x;
        //System.out.println("rightRotate done");
        return;
    }

    /*
     * insert(RBTree T, RBNode z) -> NA 
     *  T: RBTree object specifying the tree which is modefiying 
     *  z: RBNode object which will be inserted into the tree
     * 
     * Inserts a node into the red black tree according to BST rules. Inserted node
     * is automatically set to red according to RBT rules.
     */
    public void insert(RedBlackNode z) {
        RedBlackNode y = null;
        RedBlackNode x = this.root;
        while (x != null) {
            y = x;
        if (z.key < x.key)
            x = x.left;
        else
            x = x.right;
        }

        z.p = y;

        if (y == null){
            this.root = z;
        }
        else if (z.key < y.key){
            y.left = z;
        }
        else{
            y.right = z;
        }
            

        z.left = null;
        z.right = null;
        z.color = true;

        this.insertFixup(z);
        //System.out.println("insertion done");
        return;
    }

    /*
     * insertFixup(RBTree T, RBNode z) -> NA T: RBTree object specifying the tree
     * which is modefiying z: RBNode object which will be inserted into the tree
     * 
     * Helper function of insert(). fix the (unbalanced) RBTree from node z up to
     * the root.
     */
    public void insertFixup(RedBlackNode z) {
        while ((z != this.root) && (z.p.color)) {
            if (z.p == z.p.p.left) { 
                // y is uncle
                RedBlackNode y = z.p.p.right;
                if (y != null && y.color) {
                    z.p.color = false;
                    y.color = false;
                    z.p.p.color = true;
                    z = z.p.p;
                } else {
                    if (z == z.p.right) {
                        z = z.p;
                        this.leftRotate(z);
                    }
                    z.p.color = false;
                    z.p.p.color = true;
                    this.rightRotate(z.p.p);
                }
            }

            else {
                // y is uncle
                RedBlackNode y = z.p.p.left;
                if (y != null && y.color) {
                    z.p.color = false;
                    if (y != null){
                        y.color = false;
                    }
                    z.p.p.color = true;
                    z = z.p.p;
                } 
                else {
                    if (z == z.p.left) {
                        z = z.p;
                        this.rightRotate(z);
                    }
                    z.p.color = false;
                    z.p.p.color = true;
                    this.leftRotate(z.p.p);
                }

            }
        }
        this.root.color = false;
        //System.out.println("insertionFixup done");
        return;
    }


     /*
     * print_tree() -> NA 
     * 
     * print a tree Inserts a node into the red black tree according to BST rules. Inserted node
     * is automaticly set to red according to RBT rules.
     */
    public void print_tree() {
        print_tree_helper(this.root, 0);
    }

    /*
     * print_tree_helper(RedBlackNode node, int level) -> NA 
     *  node: the node that is going to be printed immediately
     *  level: the level of the node deep in the tree, which would be revealed as the length of '-'
     * 
     * a helper function that uses recursion to print out the tree from node. 
     */
    public void print_tree_helper(RedBlackNode node, int level) {
        if (node != null) {
            print_tree_helper(node.left, level + 1);
            for (int i = 0; i < 4 * level; i++) {
                System.out.print("-");
            }
            if (node!= this.root)
                System.out.print(Integer.toString(node.p.key));
            System.out.print("> " + node.key + " ");
            if (node.color == true)
                System.out.println("r");
            else
                System.out.println("b");

            print_tree_helper(node.right, level + 1);
        }

        else {
            for (int i = 0; i < 4 * level + 1; i++) {
                System.out.print("-");
            }
            System.out.println("> " + "nil" + " ");
        }

    }

    /*
     * transplant (RedBlackNode u, RedBlackNode v) -> NA 
     *  u: RBNode object which is the original node 
     *  v: RBNode object which would be the node being transplanted tou's position
     * 
     * Transplants node v and all its children to u's position in RBTree T. In other
     * words, replace u with v.
     */
    public void transplant(RedBlackNode u, RedBlackNode v) {

        if (u.p == null)
            {this.root = v;}
        else if (u == u.p.left)
            {u.p.left = v;}
        else
            {u.p.right = v;}
        
        //System.out.println("u.p = " + Integer.toString(u.p.key));
        if (v != null)
            v.p = u.p;
        return;
    }

    /*
     * treeMinimum (RedBlakcTree T, RedBlackNode x) -> RedBlackNode x 
     *  x: RBNode object specifying the location of the start of minimization
     * 
     * Minimize the node x in tree T by refering x.left to x untill x.left is a NIL
     * node. Returns the finilized node x.
     */
    public RedBlackNode treeMinimum(RedBlackNode x) {
        if (x == null)
            return x;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    /*
     * delete (RedBlakcTree T, RedBlackNode z) -> NA T: RBTree object specifying the
     * tree function is modefiying z: RBNode object specifying the node that is
     * going to be deleted
     * 
     * Delets a node from the red black tree according to BST rules. search the node
     * first, if not find that terminates. if it exists, delete it.
     */
    public void delete(RedBlackNode z) {

        // check if the node is null or does not exist first. if so, break
        if (z == null)
            return;

        //System.out.println("search value of z.key = " + Integer.toString(z.key) + " is " + Boolean.toString(this.searchKey(z.key)));
        if (this.searchKey(z.key) == false)
            return;

        // find the node that has the same key
        RedBlackNode temp = null;
        while (temp != null) {
            if (temp.key == z.key) {
                z = temp;
                break;
            }

            if (temp.key < z.key) {
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        //System.out.println("node find and start deletion.");
        
        // actual delete function
        RedBlackNode y = z;
        Boolean yoc = y.color;
        RedBlackNode x;
        if (z.left == null) {
            x = z.right;
            //System.out.println(Boolean.toString(z.right == null));
            this.transplant(z, z.right);
        } else if (z.right == null) {
            x = z.left;
            this.transplant(z, z.left);
        } else {
            y = this.treeMinimum(z.right);
            yoc = y.color;
            x = y.right;
            if (y.p == z)
                if (x!=null)
                    x.p = y;
            else {
                this.transplant(y, y.right);
                y.right = z.right;
                if (y.right != null)
                    y.right.p = y;
            }
            this.transplant(z, y);
            y.left = z.left;
            y.left.p = y;
            y.color = z.color;
        }

        if (!yoc) {
            this.deleteFixup(x);
        }

        return;
    }

    /*
     * deleteFixup (RedBlackTree T, RedBlackNode x) -> NA T: RBTree object
     * specifying the tree which is modefiying x: RBNode object which will be
     * deleted from the tree
     * 
     * Helper function of delete(). fix the (unbalanced) RBTree from node x up to
     * the root.
     */
    public void deleteFixup(RedBlackNode x) {
        while ((x != this.root) && (x!=null) && (!x.color)) {
            if (x == x.p.left) {
                RedBlackNode w = x.p.right;
                if (w.color) {
                    w.color = false;
                    x.p.color = true;
                    this.leftRotate(x.p);
                    w = x.p.right;
                }
                if ((!w.left.color) && (!w.right.color)) {
                    w.color = true;
                    x = x.p;
                }

                else {
                    if (!w.right.color) {
                        w.left.color = false;
                        w.color = true;
                        this.rightRotate(w);
                        w = x.p.right;
                    }
                    w.color = x.p.color;
                    x.p.color = false;
                    w.right.color = false;
                    this.leftRotate(x.p);
                    x = this.root;
                }

            }

            else {
                // print("2nd branch");
                RedBlackNode w = x.p.left;
                if (w.color) {
                    w.color = false;
                    x.p.color = true;
                    this.rightRotate(x.p);
                    w = x.p.left;
                }
                if ((!w.right.color) && (!w.left.color)) {
                    w.color = true;
                    x = x.p;
                } else {
                    if (!w.left.color) {
                        w.right.color = false;
                        w.color = true;
                        this.leftRotate(w);
                        w = x.p.left;
                    }

                    w.color = x.p.color;
                    x.p.color = false;
                    w.left.color = false;
                    this.rightRotate(x.p);
                    x = this.root;
                }
            }

        }
        if (x!=null)
            x.color = false;
        return;
    }

    /*
     * searchKeyHelper (RedBlackTree T, int key, RedBlackNode current) ->
     * False/RBNode T: RBTree object specifying the tree which is modefiying key:
     * the key value that you want to search current: the node you want to look at
     * first with all its children
     * 
     * This is a helper function for searchKey. It uses recursion, and the base
     * cases are either current node is null, means this key value does not exist
     * and false would be returned, or current.key == key, which means we have the
     * node. In this case the node is then returned.
     */
    public RedBlackNode searchKeyHelper(int key, RedBlackNode current) {
        if (current == null)
            return null;
        if (current.key == key)
            return current;
        else if (current.key > key)
            return this.searchKeyHelper(key, current.left);
        else
            return this.searchKeyHelper(key, current.right);
    }

    /*
     * searchKey (RedBlackTree T, int key) -> Boolean T: RBTree object specifying
     * the tree which is modefiying key: the key value that you want to search
     * 
     * This is the main function that could search if a key is in the tree T and
     * returns the boolean value. It passes the parameters into the helper function
     * for the main part.
     */
    public Boolean searchKey(int key) {
        RedBlackNode current = this.root;
        RedBlackNode temp = this.searchKeyHelper(key, current);
        if (temp == null)
            return false;
        else
            return true;
    }


    public Boolean checkChild(RedBlackNode node) {
        return ((node.left == null || node.left.color == false) && (node.right == null || node.right.color == false));
    }

    public Boolean prop4helper(RedBlackNode node) {
        if (node == null)
            return true;
        if (node.color == true) {
            boolean rst = checkChild(node);
            if (!rst)
                return false;
        }

        boolean x = prop4helper(node.left);
        boolean y = prop4helper(node.right);
        return (x && y);
    }

    public void prop4() {
        boolean rst = prop4helper(this.root);
        if (rst)
            //System.out.println("Property 4 holds");
            return;
        else
            System.out.println("** Property 4 does not hold **");

        return;
    }


// end of RedBlackTree class
}