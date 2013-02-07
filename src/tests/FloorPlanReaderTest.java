package tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import src.FloorPlanReader;

public class FloorPlanReaderTest {

  @Test
  public void testBuildTree() {
    String inorder = FloorPlanReader.buildTree("|-AB-|C-EFD").inorder();
    assertThat(inorder, is("A-B|C|E-F-D"));

    inorder = FloorPlanReader.buildTree("|-ABC").inorder();
    assertThat(inorder, is("A-B|C"));

    inorder = FloorPlanReader.buildTree("|C-AB").inorder();
    assertThat(inorder, is("C|A-B"));
  }
}
