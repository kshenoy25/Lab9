import java.util.*;

public class BSTtester {
    public static void main (String [] args) {
        BST<Integer> tree = new BST<Integer>(10);
        Scanner kb = new Scanner(System.in);
        Random rg = new Random();
        System.out.println("Building binary search tree with known values");
        tree.insert(15);
        tree.insert(8);
        tree.insert(3);
        tree.insert(6);
        tree.insert(12);
        tree.insert(16);
        tree.insert(7);
        tree.print();
        Object[] treeData = tree.buildArray(tree.getRoot());
        for (int x=0; x<treeData.length; x++) {
            System.out.print(treeData[x] + " ");
        }
        System.out.println();
        // checking for numless method run
        System.out.println("Here is numless:");
        System.out.println(tree.numLess(14));

        System.out.println("Here is numGreater:");
        System.out.println(tree.numGreater(7));


        ///////////////////////
        int target = rg.nextInt(20)+1;
        System.out.println("Looking for " + target);
        if(tree.findTarget(target))
            System.out.println("Found");
        else
            System.out.println("Not found");
        System.out.println("Building binary search tree with random values");
        tree = new BST<Integer>(rg.nextInt(50)+1);
        for (int x=0; x<14; x++)
            tree.insert(rg.nextInt(50)+1);
        tree.print();
        target = tree.getRoot().getData();
        while(target != 0) {
            System.out.print("Enter value to remove (or 0 to quit): ");
            target = kb.nextInt();
            tree.remove(target);
            tree.print();
        }

    }
}