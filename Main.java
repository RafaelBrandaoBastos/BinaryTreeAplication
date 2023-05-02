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

  void insert(Node x) {
    raiz = insert(x, raiz);
  }

  Node getRaiz() {
    return raiz;
  }

  public static Node insert(Node newNode, Node parent) {
    if (parent == null) {
      parent = newNode;
    } else if ((newNode.name).compareTo(parent.name) < 0) {
      parent.lft = insert((newNode), parent.lft);
    } else if ((newNode.name).compareTo(parent.name) > 0) {
      parent.rgt = insert((newNode), parent.rgt);
    } else {
      System.out.println("Erro!");
    }
    return parent;
  }

  void search(String name, Node parent) {
    if (parent == null) {
        System.out.print("<-not found");
    } else if (name.compareTo(parent.name) == 0) {
        System.out.print("Found" + parent.cellphone);
    } else if (name.compareTo(parent.name) < 0) {
        search(name, parent.lft);
    } else if (name.compareTo(parent.name) > 0) {
        search(name, parent.rgt);
    } else {
        System.out.print("<-not found");
    }
  }

  void pesquisar(int x) {
  }

  void caminharCentral(Node parent) {
    if (parent != null) {
      caminharCentral(parent.lft);
      System.out.println(parent.name + "");
      caminharCentral(parent.rgt);
    }
  }

  void remover(int x) {
  }
}

public class Main {
  public static void main(String[] args) throws Exception {
    String line = "";
    BinaryTree tree = new BinaryTree();
    Scanner scan = new Scanner(System.in);
    Scanner scan2 = new Scanner(System.in);
    Scanner scan3 = new Scanner(System.in);
    BufferedReader reader = new BufferedReader(new FileReader("notes.txt"));
    String strings[] = new String[2];
    line = reader.readLine();
    while (line != null) {
      strings = line.split(" # ");
      tree.insert(new Node(strings[0], strings[1]));
      line = reader.readLine();
    }
    int var = 3;
    do {
      System.out.print("\nDigital Phone Book Menu \n1-Search\n2-Inorder Traversal\n3-Finish\n\nType an Option:");
      var = scan.nextInt();
      switch (var) {
        case 1:
          System.out.print("Type a name to be searched:");
          String var2 = scan2.nextLine();
          tree.search(var2, tree.getRaiz());
          break;
        case 2:
          System.out.print("\nPre Order Traversal:");
          tree.caminharCentral(tree.getRaiz());
          break;
        default:

      }
    } while (var == 1 || var == 2);
    scan3.close();
    scan2.close();
    scan.close();
    reader.close();
    System.out.println("Programa finalizado");
  }
}