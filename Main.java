import java.io.*;
import java.io.BufferedReader;
import java.util.*;
import java.io.FileReader;

class Node {
  String name;
  Node lft;
  Node rgt;
  String cellphone;
  Node(String name, String cellphone) {
    this(name, cellphone, null, null);
  }
  Node(String name, String cellphone, Node lft, Node rgt) {
    this.cellphone = cellphone;
    this.name = name;
    this.lft = lft;
    this.rgt = rgt;
  }
}

class BinaryTree {
  Node raiz;
  BinaryTree() {
    raiz = null;
  }
  Node getRaiz() {
    return raiz;
  }

  //Insert in Binary Tree
  void insert(Node x) {
    raiz = insert(x, raiz);
  }
  public static Node insert(Node newNode, Node parent) {
    if (parent == null) {
      parent = newNode;
    } else if ((newNode.name).compareTo(parent.name) < 0) {
      parent.lft = insert((newNode), parent.lft);
    } else if ((newNode.name).compareTo(parent.name) > 0) {
      parent.rgt = insert((newNode), parent.rgt);
    } else {
      System.out.println("Erro, already exists!");
    }
    return parent;
  }

  //Remove from Binary Tree
  void remotion(String x, Node raiz) throws Exception {
    raiz = remove(x, raiz);
  }
  Node remove(String x, Node parent) throws Exception {
    if (parent == null) {
      throw new Exception("Erro!");
    } else if((x).compareTo(parent.name) < 0) {
      parent.lft = remove(x, parent.lft);
    } else if((x).compareTo(parent.name) > 0) {
      parent.rgt = remove(x, parent.rgt);
    } else if(parent.rgt == null) {
      parent = parent.lft;
    } else if(parent.lft == null) {
      parent = parent.rgt;
    } else {
      parent.lft = biggertLft(parent, parent.lft);
    }
    return parent;
  }
  Node biggertLft(Node parent, Node j) {
    if (j.rgt == null)  {
      parent.name=j.name; j=j.lft;
    } else {
      j.rgt = biggertLft(parent, j.rgt);
    }
    return j;
  }

  //Search in Binary Tree
  void search(String name, Node parent) {
    if (parent == null) {
      System.out.println("->Not Found");
    } else if (name.compareTo(parent.name) == 0) {
      System.out.println("->Found: " + parent.cellphone);
    } else if (name.compareTo(parent.name) < 0) {
      search(name, parent.lft);
    } else if (name.compareTo(parent.name) > 0) {
      search(name, parent.rgt);
    } else {
      System.out.println("->Contact Not Found");
    }
  }

  //Tree Traversals
  void PreOrderTraversal(Node parent) {
    if (parent!= null) {
      System.out.println(parent.name + " " + parent.cellphone);
      PreOrderTraversal(parent.lft);
      PreOrderTraversal(parent.rgt);
    }
  }
  void PostOrderTraversal(Node parent) {
    if (parent!= null) {
      PostOrderTraversal(parent.lft);
      PostOrderTraversal(parent.rgt);
      System.out.println(parent.name + " " + parent.cellphone);
    }
  }
  void InOrderTraversal(Node parent) {
    if (parent != null) {
      InOrderTraversal(parent.lft);
      System.out.println(parent.name + " " + parent.cellphone);
      InOrderTraversal(parent.rgt);
    }
  }
  //Cleaning list file
  void ListClear(Node parent) throws Exception {
    BufferedReader reader = new BufferedReader(new FileReader("list.txt"));
    BufferedWriter bw = new BufferedWriter(new FileWriter("list.txt"));
    String line = "";
    line = reader.readLine();
    while (line != null) {
      bw.flush();
      line = reader.readLine();
    }
    bw.close();
    reader.close();
  }
  //Update content in list file
  void ListUpdater(Node parent) throws Exception {
    FileWriter fw = new FileWriter("list.txt", true);
    if (parent != null) {
      ListUpdater(parent.lft);
      fw.write(parent.name + " # " + parent.cellphone + "\n");
      ListUpdater(parent.rgt);
    }
    fw.close();
  }
}

public class Main {
  public static void main(String[] args) throws Exception {
    Scanner scan = new Scanner(System.in);
    String line = "";
    String straux = "";
    String straux2 = "";
    Scanner scan1 = new Scanner(System.in);
    Scanner scan2 = new Scanner(System.in);
    Scanner scan3 = new Scanner(System.in);
    Scanner scan4 = new Scanner(System.in);
    int var = 3;
    BinaryTree tree = new BinaryTree();
    BufferedReader reader = new BufferedReader(new FileReader("list.txt"));
    String strings[] = new String[2];
    line = reader.readLine();
    while (line != null) {
      strings = line.split(" # ");
      tree.insert(new Node(strings[0], strings[1]));
      line = reader.readLine();
    }
    do {
      System.out.print("\nDigital Phone Book \n1-Search\n2-InOrderTraversal\n3-PreOrderTraversal\n4-PostOrderTraversal\n5-Insert\n6-Remove\n7-Finish\n\nType an Option:");
      var = scan.nextInt();
      switch (var) {
        case 1:
          System.out.print("Type a name to be searched:");
          String var2 = scan1.nextLine();
          tree.search(var2, tree.getRaiz());
          break;
        case 2:
          System.out.println("\n->InOrder Traversal:");
          tree.InOrderTraversal(tree.getRaiz());
          break;
        case 3:
          System.out.println("\n->PreOrder Traversal:");
          tree.PreOrderTraversal(tree.getRaiz());
          break;
        case 4:
          System.out.println("\n->PostOrder Traversal:");
          tree.PostOrderTraversal(tree.getRaiz());
          break;
        case 5:
          System.out.print("Type a name to be inserted on the list:");
          straux = scan2.nextLine();
          System.out.print("Type the phone number:");
          straux2 = scan3.nextLine();
          tree.insert(new Node(straux, straux2));
          tree.ListClear(tree.getRaiz());
          tree.ListUpdater(tree.getRaiz());
          break;
        case 6:
          System.out.print("Type a name to be romoved from list:");
          straux = scan4.nextLine();
          tree.remotion(straux, tree.getRaiz());
          tree.ListClear(tree.getRaiz());
          tree.ListUpdater(tree.getRaiz());
          break;
        default:
      }
    } while (var >= 1 && var <=6);
    scan.close();
    scan1.close();
    scan2.close();
    scan3.close();
    scan4.close();
    reader.close();
    System.out.println("\nExecution Ended");
  }
}
