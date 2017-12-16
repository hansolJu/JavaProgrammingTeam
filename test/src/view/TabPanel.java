package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class TabPanel extends JPanel{
	JTextArea editingTextArea;
	JTextArea resultTextArea;
	model.Model model;
	public TabPanel() {
		setLayout(null);
		editingTextArea = new JTextArea();
		
		JScrollPane editingScrollPane = new JScrollPane(editingTextArea);
		editingScrollPane.setBounds(0, 0, 484, 362);
		add(editingScrollPane);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 322, 484, 1);
		add(separator);
		
		resultTextArea = new JTextArea();
		
		JScrollPane resultScrollPane = new JScrollPane(resultTextArea);
		resultScrollPane.setBounds(0, 363, 484, 360);
		add(resultScrollPane);
	}
	public JTextArea getEditingTextArea() {
		return editingTextArea;
	}
	public void setEditingTextArea(JTextArea editingTextArea) {
		this.editingTextArea = editingTextArea;
	}
	public JTextArea getResultTextArea() {
		return resultTextArea;
	}
	public void setResultTextArea(JTextArea resultTextArea) {
		this.resultTextArea = resultTextArea;
	}
	
}
