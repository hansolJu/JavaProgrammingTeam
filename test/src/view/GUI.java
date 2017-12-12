package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class GUI {

	private JFrame frame;

	private JMenuItem openMenuItem;
	private JMenuItem closeMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem quitMenuItem;
	private JMenuItem compileMenuItem;
	private JMenuItem runMenuItem;

	private JTabbedPane tabbedPane;
	private JPanel mainPanel;

	private JTextArea editingTextArea;
	private JTextArea resultTextArea;

	/**
	 * Create the application.
	 */
	public GUI(String title) {
		initialize(title);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String title) {
		frame = new JFrame(title);
		frame.setBounds(100, 100, 400, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 384, 21);
		frame.getContentPane().add(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);

		closeMenuItem = new JMenuItem("Close");
		fileMenu.add(closeMenuItem);

		saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);

		saveAsMenuItem = new JMenuItem("Save As");
		fileMenu.add(saveAsMenuItem);

		quitMenuItem = new JMenuItem("Quit");
		fileMenu.add(quitMenuItem);

		JMenu runMenu = new JMenu("Run");
		menuBar.add(runMenu);

		compileMenuItem = new JMenuItem("Compile");
		runMenu.add(compileMenuItem);

		runMenuItem = new JMenuItem("Run");
		runMenu.add(runMenuItem);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 21, 384, 540);
		frame.getContentPane().add(tabbedPane);

		mainPanel = new JPanel();
		tabbedPane.addTab("New File", mainPanel);
		tabbedPane.addTab("New File2",new MyPanel());
		mainPanel.setLayout(null);

		editingTextArea = new JTextArea();
		JScrollPane editingScrollPane = new JScrollPane(editingTextArea);
		editingScrollPane.setBounds(0, 0, 379, 322);
		mainPanel.add(editingScrollPane);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 322, 379, 1);
		mainPanel.add(separator);

		resultTextArea = new JTextArea();
		JScrollPane resultScrollPane = new JScrollPane(resultTextArea);
		resultScrollPane.setBounds(0, 323, 380, 187);
		mainPanel.add(resultScrollPane);
		frame.setVisible(true);

	}

	class MyPanel extends JPanel {
		public MyPanel() {
			JPanel mainPanel = new JPanel();
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

	// 컨트롤러에서 컨트롤하기위한 getter/setter
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JMenuItem getOpenMenuItem() {
		return openMenuItem;
	}

	public void setOpenMenuItem(JMenuItem openMenuItem) {
		this.openMenuItem = openMenuItem;
	}

	public JMenuItem getCloseMenuItem() {
		return closeMenuItem;
	}

	public void setCloseMenuItem(JMenuItem closeMenuItem) {
		this.closeMenuItem = closeMenuItem;
	}

	public JMenuItem getSaveMenuItem() {
		return saveMenuItem;
	}

	public void setSaveMenuItem(JMenuItem saveMenuItem) {
		this.saveMenuItem = saveMenuItem;
	}

	public JMenuItem getSaveAsMenuItem() {
		return saveAsMenuItem;
	}

	public void setSaveAsMenuItem(JMenuItem saveAsMenuItem) {
		this.saveAsMenuItem = saveAsMenuItem;
	}

	public JMenuItem getQuitMenuItem() {
		return quitMenuItem;
	}

	public void setQuitMenuItem(JMenuItem quitMenuItem) {
		this.quitMenuItem = quitMenuItem;
	}

	public JMenuItem getCompileMenuItem() {
		return compileMenuItem;
	}

	public void setCompileMenuItem(JMenuItem compileMenuItem) {
		this.compileMenuItem = compileMenuItem;
	}

	public JMenuItem getRunMenuItem() {
		return runMenuItem;
	}

	public void setRunMenuItem(JMenuItem runMenuItem) {
		this.runMenuItem = runMenuItem;
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

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

}
