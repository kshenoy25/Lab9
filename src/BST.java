import java.util.*;
public class BST<E extends Comparable<E>> {
    private BTNode<E> root;
    private int count;

    public BST(E data) {

        root = new BTNode(data, null, null);
    }

    public BTNode<E> getRoot() {
        return root;
    }

    public boolean findTarget (E data) {
        return findTarget(root, data);
    }

    private boolean findTarget(BTNode<E> current, E target) {
        if (current == null) return false;
        if (current.getData().compareTo(target) < 0)
            return findTarget(current.getRight(), target);
        if (current.getData().compareTo(target) > 0)
            return findTarget(current.getLeft(), target);
        // it's not null, it's not less, it's not more - must be it:
        return true;
    }



    public void numLess(BTNode<E> current, E target){
        if (current == null) return;

        numLess(current.getLeft(), target);
        if(current.getData().compareTo(target) < 0){
            count++;
        }
        numLess(current.getRight(), target);
    }

    public int numLess(E data) {
        count =0;
        numLess(root, data);
        return count;
    }


    public void numGreater(BTNode<E> current, E target){
        if (current == null) return;

        numGreater(current.getLeft(), target);
        if(current.getData().compareTo(target) > 0){
            count++;
        }
        numGreater(current.getRight(), target);
    }

    public int numGreater(E data) {
        count =0;
        numGreater(root, data);
        return count;
    }
   // public int numGreater(E value){


    //}


    public void insert (E data) {
        root = insert(root, data);
    }

    private BTNode<E> insert(BTNode<E> current, E data) {
        E d1 = current.getData();
        E d2 = data;
        if (d2.compareTo(d1)<=0) {
            if (current.getLeft() == null)
                current.setLeft(new BTNode(data,null,null));
            else
                insert(current.getLeft(),data);
        }
        else {
            if (current.getRight() == null)
                current.setRight(new BTNode(data,null,null));
            else
                insert(current.getRight(), data);
        }
        return current;
    }
    // removal algorithm by Sedgewick & Wayne: adds the public/private
    // method pair & a whole lot less code than my version
    public void remove (E target) {
        root = remove(root, target);
    }
    public BTNode<E> remove (BTNode<E> current, E target) {
        if (current == null) return null;
        int cmp = current.getData().compareTo(target);
        if(cmp > 0) current.setLeft(remove(current.getLeft(), target));
        else if (cmp < 0)  current.setRight(remove(current.getRight(), target));
        else { // data is in current node
            if (current.getRight() == null) return current.getLeft();
            if (current.getLeft() == null) return current.getRight();
            BTNode<E> tmp = current;
            current = new BTNode(tmp.getRight().getLeftmostData(), null, null);
            BTNode<E> rc = tmp.getRight().removeLeftmost();
            current.setRight(rc);
            current.setLeft(tmp.getLeft());
        }
        return current;
    }

    public Object [] buildArray(BTNode<E> root) {
        Stack<BTNode<E>> parents = new Stack<BTNode<E>>();
        int size = (int)root.treeSize(root);
        int ct=0;
        Object []array =  new Object[size];
        BTNode<E> current = root;
        Object data;
        // perform (non-recursive) preorder traversal, place elements in array;
        // code is based on solution found at
        // http://www.codeguru.com/forum/showthread.php?t=401922,
        // attributed there to "nitiraj"
        while (true) {
            while (current != null) {
                data = current.getData();
                array[ct] = data;
                ct++;
                parents.push(current);
                current = current.getLeft();
            }
            if(!parents.empty()) {
                current = parents.pop();
                current = current.getRight();
            }
            else
                break;
        } // ends loop              
        return array;
    } // ends method 

    public void print() {
        root.print(0);
    }
}