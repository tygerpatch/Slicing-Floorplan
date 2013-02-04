import gui.FileChooserPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.datastructures.NodeQueue;

/**
 Program request text file containing floor plan to use.
 Floor plan is read in and stored as a binary tree.
 User can then selected to graphically display tree
  or display the rooms that the floor plan represents.

 @author Todd Gerspacher
*/
public class FloorPlan implements ActionListener
{
 private Tree tree;
 private JTextField txtField;
 private JButton browse;
 private JButton rooms;
 private JButton nodes;
 
 /**
  This method runs the application
 */
 public void run()
 {
  // this frame will contain the buttons to select the file to use
  //  and weather to display the nodes or the rooms graphically
  JFrame frm = new JFrame("Floor Plan");
  JPanel p = new JPanel(new BorderLayout());

  FileChooserPanel fileChooserPanel = new FileChooserPanel();

  txtField = fileChooserPanel.getTextField();
  browse = fileChooserPanel.getButton();
  browse.addActionListener(this);

  p.add( fileChooserPanel, BorderLayout.NORTH);
  p.add( drawPanel() );

  frm.setContentPane(p);
  frm.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  frm.pack();
  frm.setVisible(true);
 }

 /**
  This method creates a JPanel containing buttons
   That allow user either to graphically display
   the nodes the floor plan tree Or display the rooms it describes

  @return JPanel the JPanel created in method
 */
 private JPanel drawPanel()
 {
  JPanel p = new JPanel(new GridLayout(1,5));

  // this JButton will allow user to display the rooms
  //  as described by the given text file
  rooms = new JButton("ROOMS");
  rooms.addActionListener(this);

  // this JButton will allow user to display the nodes
  //  of the binary floor plan tree
  nodes = new JButton("NODES");
  nodes.addActionListener(this);
		
  p.add( new JLabel("") );
  p.add( rooms );
  p.add( new JLabel("") );
  p.add( nodes );
  p.add( new JLabel("") );
		
  return p;
 }
 
 /**
  This method is called whenever the user clicks on one of the three JButtons: 
   
   The one to search for the text file to use in program
   The one to graphically display the nodes of the floor plan tree
   The one to display the rooms as is described by the text file

  @param ActionEvent contains source of who called this method
 */ 
 public void actionPerformed(ActionEvent evt)
 {
  // get the reference of the object that triggered the event
  Object obj = evt.getSource();

  // if that object was the browse button
  //  then call a method that deals with looking for a file to use		
  if( obj == browse )
   selectFile();

  // if that object was the rooms button
  //  then call method to display rooms in a separate JFrame
  if( obj == rooms )
   drawRooms();

  // if the object was the nodes button
  //  then call method to display nodes in a separate JFrame
  if( obj == nodes )
   drawNodes();
 }
 
 /**
  This method handles the process of allowing the user to search
   Through the directory to find a text file to use.
 */ 
 private void selectFile()
 {
  JFileChooser fileChooser = new JFileChooser(); 
		
  if( fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION )
  {
   // if user approved of selected file, then change the text
   //  in the text field to display the path of selected file.
   txtField.setText( fileChooser.getSelectedFile().toString() );

   // next call method that handles the creation of the tree
   getTree();
  }
 }

 /**
  This method handles the creation of floor plan tree
 */ 
 private void getTree()
 {
  String str;
  
  // handle the reading of file in a try/catch block
  //  in case of input/output errors (like file not found) 		
  try
  {
   BufferedReader reader = new BufferedReader
    (new FileReader(txtField.getText()));
				
   str = reader.readLine();
   tree = new Tree();

   for(int i = 0; i < str.length(); i++)
    tree.add( str.charAt(i) );
  }
  catch(IOException e)
  {
   // inform user of error in message box
   JOptionPane.showMessageDialog(null, e.getMessage());  			
  }
 }
 
 /**
  This method handles the drawing of the nodes in the tree
   In a JFrame
 */
 private void drawNodes()
 {
  // if tree is null, then don’t do anything
  if( tree == null )
   return;
		
  JFrame frame = new JFrame("Tree");

  // Use a class that extends from JPanel to handle drawing of nodes
  //  pass in the size to use for shapes
  JPanel panel = new NodePanel(tree.getNodeInfoList(), 25);

  frame.setContentPane( panel );
  frame.setSize(200, 200);
  frame.setVisible(true);

  // NOTE: user can close this frame without terminating program
 }
 
 /**
  This method handles the drawing of the rooms as is described
   By the text file.
 */
 private void drawRooms()
 {
  // if tree is null, then don’t do anything
  if( tree == null )
   return;

  JFrame frame = new JFrame("Rooms");

  // Use a class that extends from JPanel to handle drawing of rooms
  //  pass in the tree Floor Plan tree to use
  JPanel panel = new RoomPanel(tree);

  frame.setContentPane( new RoomPanel(tree) );
  frame.setSize(200, 200);
  frame.setVisible(true);

  // NOTE: user can close this frame without terminating program
 }
 
 /**
  This is the main method that runs the application from the console
  No arguments are required to run application
 */
 public static void main(String[] args)
 {
  FloorPlan test = new FloorPlan();
  test.run();

  // Tree.main(args); // test Tree class
  // NodeInfoList.main(args); // test NodeInfoList class
  // NodePanel.main(args); // test NodePanel class
 }
}

/**
 This class represents the nodes of the floor plan tree on a graph
 NOTE: uses integer for coordinates
 @author Todd Gerspacher
*/
class NodeInfo
{
 private int x;
 private int y;
 private char ch;
 private int num;
 
 /**
  Constructor creates a new NodeInfo object with the given arguments
  @param x the int x coordinate of node on graph
  @param y the int y coordinate of node on graph
  @param ch the char value stored in node
  @param num the number of the nodes,
    The root node will have a number of 1
    It’s left child’s number will be its parent * 2
    It’s right child’s number will be its parent * 2 and then + 1
 */
 public NodeInfo(int x, int y, char ch, int num)
 {
  this.x = x;
  this.y = y;
  this.ch = ch;
  this.num = num;
 }
 
 /**
  This method returns the x coordinate of where to display Node on graph
  @param int the x-coordinate of node
 */
 public int x()
 {
  return x;
 }

 /**
  This method returns the y coordinate of where to display Node on graph
  @param int the y-coordinate of node
 */
 public int y()
 {
  return y;
 }
 
 /**
  This method returns the char value stored in node
  @param ch the char value
 */
 public char ch()
 {
  return ch;
 }

 /**
  This method returns the number of the node
  @param int the number of the node
 */
 public int num()
 {
  return num;
 }
 
 /**
  This method overrides Object’s equals method
  This method is called to determine if a given Object
   Is an instance of NodeInfo and if its number is the same as this
   NodeInfo’s number
  @param obj the Object that is being tested 
 */
 public boolean equals(Object obj)
 {
  NodeInfo tmp;
		
  if( obj instanceof NodeInfo )
  {
   tmp = (NodeInfo)obj;
   return (num == tmp.num());
  }
		
  return false;
 }
 
 /**
  This method allows a NodeInfo object to be  represented as a String
  @param String the String representation of this NodeInfo object
 */
 public String toString()
 {
  return new String("(" + x + "," + y + ") " + ch + " #" + num);
 }
}

/**
 This class is used to hold a list of NodeInfo objects
 @author Todd Gerspacher
*/
class NodeInfoList extends LinkedList<NodeInfo>
{
 /**
  This method overrides the LinkedList’s get method
   To allow user to retrieve a NodeInfo object based on it’s number

  @param num the number of the NodeInfo object to get
  @return NodeInfo the desired NodeInfo with requested number,
    If the NodeInfo object was not found in list, then a null is returned
 */
 public NodeInfo get(int num)
 {
  // create a temporary NodeInfo object to use in NodeInfo’s equals method
  NodeInfo tmp = new NodeInfo(0, 0, '?', num);
		
  if( contains(tmp) )
   return super.get( indexOf(tmp) );
		
  return null;
 }
 
 /**
  This method overrides LinkedList’s corresponding add method to ensure
   That their no NodeInfo’s with the same number

  @param obj the NodeInfo object to be potentially added to list
  @return true if NodeInfo was added, false otherwise
 */
 public boolean add(NodeInfo obj)
 {
  if( !contains(obj) )
   return super.add(obj);
		
  return false;
 }

 /**
  This method overrides LinkedList’s corresponding add method to ensure
   That their no NodeInfo’s with the same number
  Method also eliminates users ability to place NodeInfo object anywhere in list

  @param indx
  @param obj the NodeInfo object to be potentially added to list
 */
 public void add(int indx, NodeInfo obj)
 {
  add(obj);
 }

 /**
  This method overrides LinkedList’s corresponding add method to ensure
   That their no NodeInfo’s with the same number

  @param obj the NodeInfo object to be potentially added to list
 */
 public void addFirst(NodeInfo obj)
 {
  add(obj);
 }
 
 /**
  This method overrides LinkedList’s corresponding add method to ensure
   That their no NodeInfo’s with the same number

  @param obj the NodeInfo object to be potentially added to list
 */
 public void addLast(NodeInfo obj)
 {
  add(obj);
 }
 
 /**
  This method allows the NodeInfoList to be represented as a String

  @return String the String representation of the NodeInfoList
 */
 public String toString()
 {
  String str = "["; // inclose NodeInfo’s in square brackets
  ListIterator<NodeInfo> it = listIterator(0);

  // iterate through NodeInfo’s, concatenating their String representations
  while( it.hasNext() )
   str = str + it.next().toString() + ",";

  // determine if a ‘,’ needs to be removed
  if( str.charAt(str.length()-1) == ',' )
   str = str.substring(0, str.length()-1);
		
  str = str + "]"; 
		
  return str;		
 }
 
 /**
  This method tests creating a NodeInfoList, attempting to add a NodeInfo
   With a duplicate number, and also displaying NodeInfoList in a String
 */
 public static void main(String[] args)
 {
  NodeInfoList list = new NodeInfoList();
		
  list.add( new NodeInfo(1, 2, 'a', 1) );
  list.add( new NodeInfo(3, 4, 'b', 2) );
  list.add( new NodeInfo(5, 6, 'c', 1) );
		
  System.out.println("List : " + list + '\n');
  System.out.println("NodeInfo with a number of 1 : " + list.get(1) + '\n');
 }
}

/**
 This class will be used to represent the binary tree used 
  in the Floor Plan program

 @author Todd Gerspacher
*/
class Tree
{
 private int visited = 0;             // used in generating NodeInfoList
 private NodeQueue<Rectangle> queue;  // used in drawing the rooms

 /**
   This class will represent the nodes in the tree

   @author Todd Gerspacher
 */
 private static class Node
 {
  public Node left;
  public char ch;
  public Node right;

  /**
   Constructor creates a new Node object
   
   @param left the Node’s left child
   @param ch the char value to store in Node
   @param right the Node’s right child
  */
  public Node(Node left, char ch, Node right)
  {
   this.left = left;
   this.ch = ch;
   this.right = right;
  }	
 }
 
 private Node root; // reference to the root node of the tree

 /**
  This method will determine if a given ch value denotes a cut
  ‘-’ is a horizontal cut and ‘|‘ is a vertical cut

  @param ch the char to be tested
  @return true if char represents a cut, false otherwise
 */
 public static boolean isCut(char ch)
 {
  return ( (ch == '|') || (ch == '-') );
 }

 
 /**
  This method allows will determine a creates 
   A new node with the given ch value

  @param ch the char value created Node should have
 */
 public void add(char ch)
 {
  if( root == null )
   root = new Node(null, ch, null);
  else
   root = add(root, ch);
 }

 /**
  This potentially recursive method does an inorder traversal through
   The tree to find the next available position in the tree and
   Then creates and add’s a new node with the given ch value

  @param parent the potential parent of the Node being added
  @param ch the char value created Node should have
 */
 private Node add(Node parent, char ch)
 {
  Node temp;

  if( isCut(parent.ch) )
  {
   if( parent.left == null )
    return new Node(new Node(null, ch, null), parent.ch, parent.right);
			
   if( parent.left != (temp = add(parent.left, ch)) )
    return new Node(temp, parent.ch, parent.right);
			
   if( parent.right == null )
    return new Node(parent.left, parent.ch, new Node(null, ch, null));
			
   if( parent.right != (temp = add(parent.right, ch)) )
    return new Node(parent.left, parent.ch, temp);
  }
	
  return parent;
 }

 /**
  This method returns the string represenetion of this FPTree object (using preorder traversal).

  For example if the following was this FPTree, with / and \ representing references to child nodes,

       -
      / \
     |   C
    / \
    A  B

  then this toString method method will return the following string:

  root : -\n
  +left : root : |\n
  ++left : root : A\n
  ++right : root : B\n
  +right : root : C\n

  NOTE: The newline character is represented here, but will not be display in returned String.
  NOTE: +'s represent depth of the node. For example, the root has no +'s because it has a depth of 0.
  
  @return String representation of this FPTree object      
 */
 public String toString()
 {
  if( root == null )
   return "";
		
  return toString(root, "+");
 }

 /**
  This method does the actual conversion from FPTree to its String representation.

  If the root is not null, then this method:
   First concatenates "root : " with the node's value and also '\n' (newline)
   Next if the node's left reference is not null, it concatenates its String representation
   If the node's right reference is not null either, it is concatenated to the String also.
   
  Finally the method returns concatenated String.
  
  NOTE: If the Node has a null reference value, then its returned String is "".

  @param n Node to be represented as a String
  @param depthRep String value to use to represent Node's depth
  @return String representation of given Node
 */
 private String toString(Node node, String depthRep)
 {
  String str = "root : " + node.ch + '\n';

  if( node.left != null )
  {
   str = str + depthRep + "left : ";
   str = str + toString(node.left, depthRep + depthRep.charAt(0));
  }

  if( node.right != null )
  {
   str = str + depthRep + "right : ";
   str = str + toString(node.right, depthRep + depthRep.charAt(0));
  }
		
  return str;
 }

 /**
  This method creates a NodeInfoList of containing all the node’s in the tree
  (author: Michael T. Goodrich, title: Roberto Tamassia, Data Structures & Algorithms  in Java,
     publisher: John Wiley & Sons, Inc., edition: Fourth edition, page number: 273)

  @return NodeInfoList the created NodeInfoList
 */
 public NodeInfoList getNodeInfoList()
 {
  visited = 0;
  return nodeInfos(root, 0, 1);
 }
 
 /**
  Method that actually creates the NodeInfo for each node in the FPTree.

  @param node the Node to find NodeInfo for
  @param depth Number of ancestors for the given node, excluding the node itself
  @param num Number to use to represent the given node

  (author: Michael T. Goodrich, title: Roberto Tamassia, Data Structures & Algorithms  in Java,
     publisher: John Wiley & Sons, Inc., edition: Fourth edition, page number: 273)
  (http://www.wvutech.edu/mclark/Data%20Structures/Assignments%202004/Extra%20Credit%20Assignment.htm)
 */
 private NodeInfoList nodeInfos(Node node, int depth, int num)
 {
  NodeInfoList list = new NodeInfoList();
  NodeInfoList subtree;

  // create NodeInfoList of the left subtree ?		
  if(node.left != null)
  {
   subtree = nodeInfos(node.left, depth + 1, num * 2);
   list.addAll(subtree);
  }

  list.add( new NodeInfo(visited++, depth, node.ch, num) );

  // create NodeInfoList of the right subtree ?
  if(node.right != null)
  {
   subtree = nodeInfos(node.right, depth + 1, (num * 2) + 1);
   list.addAll(subtree);
  }
	
  return list;
 }
 
 /**
  This method is called to draw the rooms as is described by the tree

  @param g the Graphics2D to draw the rooms on
  @param x the integer x coordinate of floor’s top left corner
  @param y the integer y coordinate of floor’s top left corner
  @param width the width of the floor
  @param height the height of the floor
 */
 public void drawRooms(Graphics2D g, int x, int y, int width, int height)
 {
  // first create a new LinkedQueue 
  //  and push on a rectangular representation of the floor (without rooms)
  queue = new NodeQueue<Rectangle>();
  queue.enqueue(new Rectangle(x, y, width, height));

  drawRooms(root, g);
 }
 
 /**
  This recursive draws the individual rooms using inorder traversal

  @param node the node used to determine whether to draw a cut or a room
  @param g the Graphics2D object to draw on
 */
 private void drawRooms(Node node, Graphics2D g)
 {
  switch( node.ch )
  {
   case '|':
    drawVerticalCut();
    break;
   case '-':
    drawHorizontalCut();
    break;
   default:
    drawRoom(node, g);
    break;
   }

  // does node’s left child need to be drawn ?		
  if( node.left != null )
   drawRooms(node.left, g);
  
  // does node’s right child need to be drawn ?
  if( node.right != null )
   drawRooms(node.right, g);
 }
 
 /**
  This method draws a vertical cut on the floor plan
 */
 private void drawVerticalCut()
 {
  // First pop off the last rectangle (to be used in cut)
   Rectangle temp = (Rectangle)queue.dequeue();
  NodeQueue<Rectangle> q = new NodeQueue<Rectangle>();

  // Next determine the new two rooms new width value		
  int newWidth = (int)(temp.getWidth()/2);

  // remove all old rectangle shapes, so that the
  //  the new rectangle shapes can become first and second in queue
  while(!queue.isEmpty())
    q.enqueue(queue.dequeue());
  
  // push the first half of room onto queue
  queue.enqueue(new Rectangle( (int)temp.getX(), (int)temp.getY(), newWidth, (int)temp.getHeight()) );

  // push the second half of room onto queue
  queue.enqueue( new Rectangle( (int)temp.getX() + newWidth, (int)temp.getY(), newWidth, (int)temp.getHeight()) );

  // replace old rectangle shapes into queue
  while(!q.isEmpty())
    queue.enqueue(q.dequeue());
 }

 /**
  This method draws a horizontal cut on the floor plan
 */
	
 private void drawHorizontalCut()
 {
  // First pop off the last rectangle (to be used in cut)
   Rectangle temp = (Rectangle)queue.dequeue();
  NodeQueue<Rectangle> q = new NodeQueue<Rectangle>();

  // Next determine the new two rooms new height value
  int newHeight = (int)(temp.getHeight()/2);

  // remove all old rectangle shapes, so that the
  //  the new rectangle shapes can become first and second in queue
  while( !queue.isEmpty() )
    q.enqueue( queue.dequeue() );
  
  // push the first half of room onto queue
  queue.enqueue( new Rectangle( (int)temp.getX(), (int)temp.getY(), (int)temp.getWidth(), newHeight) );

  // push the second half of room onto queue
  queue.enqueue( new Rectangle( (int)temp.getX(), (int)temp.getY() + newHeight, (int)temp.getWidth(), newHeight) );

  // replace old rectangle shapes into queue
  while(  !q.isEmpty() )
    queue.enqueue( q.dequeue() );
 }

 /**
  This method draws the rectangular outline of the first room in queue
   And then prints the node’s ch value in the center of the room 

  @param node the Node object containing room’s char identifier
  @param g the Graphics2D object to draw on
 */			
 private void drawRoom(Node node, Graphics2D g)
 {
   Rectangle temp = (Rectangle)queue.dequeue();
  
  float x = (float)(temp.getX() + (temp.getWidth()/2));
  float y = (float)(temp.getY() + (temp.getHeight()/2));

  g.setColor( Color.LIGHT_GRAY );
  g.fill( temp );

  g.setColor( Color.BLACK );
  g.draw( temp );
  g.drawString( "" + node.ch, x, y );
 }
 
 /**
  this method tests the creation of tree objects 

  Test #1 
   Given floor plan : "-AB"
   Purpose : Ability to do a basic floor plane with one cut

  Test #2
   Given floor plan : "-ABC"
   Purpose : Ability to identify room identifiers (which cannot hold child nodes).

  Test #3
   Given floor plan : "|-AB-|C-EFD"
   Purpose : Ability to do a complex floor plan with multiple room identifiers and cuts
 */
 public static void main(String[] args)
 {
  String tests[] = new String[3];

  tests[0] = "-AB"; // basic floor plane with one cut   
  tests[1] = "-ABC"; // a room identifier cannot be used as a cut
  tests[2] = "|-AB-|C-EFD"; // multiple room identifiers and cuts

  Tree tree;

  for( int i = 0; i < tests.length; i++)
  {
   tree = new Tree();

   for( int j = 0; j < tests[i].length(); j++)
    tree.add( tests[i].charAt(j) );

   System.out.println("TEST # " + (i + 1) + '\n' + tree);
  }
 }
}

/**
 This class extends from JPanel to handle the drawing of rooms

 @author Todd Gerpacher
*/
class RoomPanel extends JPanel
{
 private Tree tree;
 
 /**
  Constructor creates a new RoomPanel

  @param tree the Tree to use when drawing the rooms
 */
 RoomPanel(Tree tree)
 {
  this.tree = tree;
 }
 
 /**
  Override’s JPanel’s paint method to draw rooms
  
  @param g the Graphics object used in drawing rooms
 */
 public void paint(Graphics g)
 {
  tree.drawRooms((Graphics2D)g, 10, 10, 100, 100);
 }
}

/**
 This class extends from JPanel to handle drawing the nodes of the tree

 @author Todd Gerspacher
*/
class NodePanel extends JPanel
{
 private NodeInfoList list;
 private final int SHAPE_SIZE;
 private final int HALF_SIZE;
 
 /**
  Constructor creates a NodePanel object

  @param list the NodeInfoList containing all the NodeInfo’s to use
  @param size the size of the shape’s to use in drawing
 */
 public NodePanel(NodeInfoList list, int size)
 {
  this.list = list;
  SHAPE_SIZE = size;
  HALF_SIZE = (int)size/2;
 }
 
 /**
  Overrides JPanel’s paint to draw the tree’s nodes

  @param g the Graphics object to draw on
 */
 public void paint(Graphics g)
 {
  ListIterator<NodeInfo> it = list.listIterator(0);
  int num;

  // iterate through list, drawing nodes		
  while( it.hasNext() )
  {
   num = it.next().num();

   // (http://www.wvutech.edu/mclark/Data%20Structures/Assignments%202004/Extra%20Credit%20Assignment.htm)	
   draw(num, num * 2, g);
   draw(num, (num * 2) + 1, g);
  }
 }

 /**
  This method does the actual drawing of the nodes

  @param from the int value of which NodeInfo’s number to draw from
  @param to the int value of which NodeInfo’s number to draw to
  @param g the Graphics object to draw on
 */ 
 private void draw(int from, int to, Graphics g)
 {
  NodeInfo parent = list.get(from);
  NodeInfo child = list.get(to);
		
  if( child == null )
   return;
		
  int x1 = parent.x() * SHAPE_SIZE + SHAPE_SIZE;
  int y1 = parent.y() * SHAPE_SIZE + SHAPE_SIZE;
  int x2 = child.x() * SHAPE_SIZE + SHAPE_SIZE;
  int y2 = child.y() * SHAPE_SIZE + SHAPE_SIZE;
		
  g.setColor(Color.BLACK);
  g.drawLine(x1, y1, x2, y2);
		
  if( Tree.isCut(parent.ch()) )
   drawCircle("" + parent.ch(), x1, y1, g);
  else
   drawSquare("" + parent.ch(), x1, y1, g);
		
  if( Tree.isCut(child.ch()) )
   drawCircle("" + child.ch(), x2, y2, g);
  else
   drawSquare("" + child.ch(), x2, y2, g);
 }
 
 /**
  Method draws a filled in green square around the specified coordinate
  And then places a string in the middle of square

  @param str the String to be placed in middle of square
  @param x the x-coordinate of square’s top left corner
  @param y the y-coordinate of square’s top left corner
  @param g the Graphics object to draw on
 */
 private void drawSquare(String str, int x, int y, Graphics g)
 {
  g.setColor(Color.GREEN);
  g.fillRect(x - HALF_SIZE, y - HALF_SIZE, SHAPE_SIZE, SHAPE_SIZE);
		
  g.setColor(Color.BLACK);
  g.drawRect(x - HALF_SIZE, y - HALF_SIZE, SHAPE_SIZE, SHAPE_SIZE);
		
  g.drawString(str, x, y);
 }
 
 /**
  Method draws a filled in yellow circle around the specified coordinate
  And then places a string in the middle of circle

  @param str the String to be placed in middle of circle
  @param x the x-coordinate of circle’s top left corner
  @param y the y-coordinate of circle’s top left corner
  @param g the Graphics object to draw on
 */
 private void drawCircle(String str, int x, int y, Graphics g)
 {
  g.setColor(Color.YELLOW);
  g.fillOval(x - HALF_SIZE, y - HALF_SIZE, SHAPE_SIZE, SHAPE_SIZE);
		
  g.setColor(Color.BLACK);
  g.drawOval(x - HALF_SIZE, y - HALF_SIZE, SHAPE_SIZE, SHAPE_SIZE);
		
  g.drawString(str, x, y);
 }
 
 // tests the drawing of nodes
 public static void main(String[] args)
 {
  Tree tree = new Tree();
  String str = "|-AB-|C-EFD";
		
  for(int i = 0; i < str.length(); i++)
   tree.add( str.charAt(i) );
		
  JFrame frame = new JFrame("Drawing Nodes test");
  JPanel panel = new NodePanel(tree.getNodeInfoList(), 25);

  frame.setContentPane( panel );
  frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  frame.setSize(200, 200);
  frame.setVisible(true);
 }
}
