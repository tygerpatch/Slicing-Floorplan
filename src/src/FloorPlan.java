package src;

import gui.MainPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 Program request text file containing floor plan to use.
 Floor plan is read in and stored as a binary tree.
 User can then selected to graphically display tree
  or display the rooms that the floor plan represents.

 @author Todd Gerspacher
*/
public class FloorPlan implements ActionListener {
 private Tree tree;
 private JTextField txtField;
 private JButton browse;
 private JButton rooms;
 private JButton nodes;
  
 public FloorPlan(JTextField txtField, JButton browse, JButton rooms, JButton nodes) {
   this.txtField = txtField;
   this.browse = browse;
   this.rooms = rooms;
   this.nodes = nodes;

   this.browse.addActionListener(this);
   this.rooms.addActionListener(this);
   this.nodes.addActionListener(this);
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
   MainPanel mainPanel = new MainPanel();
   FloorPlan floorPlan = new FloorPlan(mainPanel.getTxtField(), mainPanel.getBrowse(), mainPanel.getRooms(), mainPanel.getNodes());

  JFrame frame = new JFrame("Floor Plan");
  frame.setContentPane(mainPanel);
  frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  frame.pack();
  frame.setVisible(true);
  
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
