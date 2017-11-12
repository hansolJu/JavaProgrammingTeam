package apps.core;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JFrame{
	JTextField fileOpenText;
	JTextField fileSaveText;
	JTextArea fileEditArea, resultArea;
	JButton openButton, saveButton;
	JButton compileButton, runButton, saveErrorsButton, deleteButton, clearButton;
	Container contentPane;
	Main(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("JAVA IDE"	);
		contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(5,1));
		
		OpenPanel openPanel = new OpenPanel();
		SavePanel savePanel = new SavePanel();
		contentPane.add(openPanel);
		contentPane.add(savePanel);
		fileSaveText = new JTextField(50);
		fileEditArea = new JTextArea(50, 50);
		
		setSize(300, 500);
		setVisible(true);
		
	}
	/*
	Scanner scanner = new Scanner(System.in);
	private boolean isCompiled = false;
	FileSys fileSys = new FileSys(); 
	Runner runner = new Runner();
	Compiler compiler = new Compiler();
	Error error = new Error();
	*/
	private class OpenPanel extends JPanel{
		OpenPanel() {
			setLayout(new FlowLayout());
			fileOpenText = new JTextField(50);
			openButton = new JButton("Open");
			add(fileOpenText);
			add(openButton);
			
		}
	}
	private class SavePanel extends JPanel{
		SavePanel() {
			setLayout(new FlowLayout());
			fileSaveText = new JTextField(50);
			saveButton = new JButton("Save");
			add(fileSaveText);
			add(saveButton);
		}
	}
	private class EditPanel extends JPanel {
		
	}
	private class ButtonsPanel extends JPanel {
		
	}
	private class resultPanel extends JPanel {
		
	}
	public static void main(String[] args) {
		new Main();
	}
}
