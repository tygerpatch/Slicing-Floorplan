package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

/*
 * @author Todd Gerspacher
 */
public class BrowseActionListener implements ActionListener {

  private JTextField textField;

  public BrowseActionListener(JTextField textField) {
    this.textField = textField;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    JFileChooser fileChooser = new JFileChooser("./other");

    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      textField.setText(fileChooser.getSelectedFile().toString());
    }
  }
}
