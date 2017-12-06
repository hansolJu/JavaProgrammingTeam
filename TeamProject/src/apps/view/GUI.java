package apps.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSeparator;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 384, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		
		JMenuItem closeMenuItem = new JMenuItem("Close");
		fileMenu.add(closeMenuItem);
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		
		JMenuItem saveAsMenuItem = new JMenuItem("Save As");
		fileMenu.add(saveAsMenuItem);
		
		JMenuItem quitMenuItem = new JMenuItem("Quit");
		fileMenu.add(quitMenuItem);
		
		JMenu runMenu = new JMenu("Run");
		menuBar.add(runMenu);
		
		JMenuItem compileMenuItem = new JMenuItem("Compile");
		runMenu.add(compileMenuItem);
		
		JMenuItem runMenuItem = new JMenuItem("Run");
		
		runMenu.add(runMenuItem);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 21, 384, 540);
		frame.getContentPane().add(tabbedPane);
		
		JPanel mainPanel = new JPanel();
		tabbedPane.addTab("New File", null, mainPanel, null);
		mainPanel.setLayout(null);
		
		JTextArea editingTextArea = new JTextArea();
		
		JScrollPane editingScrollPane = new JScrollPane(editingTextArea);
		editingScrollPane.setBounds(0, 0, 379, 322);
		mainPanel.add(editingScrollPane);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 322, 379, 1);
		mainPanel.add(separator);
		
		JTextArea resultTextArea = new JTextArea();
		
		JScrollPane resultScrollPane = new JScrollPane(resultTextArea);
		resultScrollPane.setBounds(0, 323, 380, 187);
		mainPanel.add(resultScrollPane);
		
	}
}
