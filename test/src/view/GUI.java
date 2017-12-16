package view;

import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import model.Model;

public class GUI extends JFrame{
	private JMenuBar menuBar;
	private JMenu fileMenu, runMenu; 
	private JMenuItem openMenuItem, closeMenuItem, saveMenuItem, saveAsMenuItem, quitMenuItem,compileMenuItem, runMenuItem;
	private JTabbedPane tabbedPane;
	private HashMap<Model, TabPanel> tabPanelMap;
	private HashMap<TabPanel, Model> panelToModel;
	public GUI(String title) {
		setTitle(title);
		setBounds(100, 100, 493, 800);

		createMenu();
		initialize();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);// 프레임 중앙에 배치.
		setResizable(false); //리사이즈를 막는 메서드.
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		tabPanelMap = new HashMap<Model,TabPanel>();
		panelToModel = new HashMap<TabPanel, Model>();
		tabbedPane = new JTabbedPane();
		TabPanel tabPanel = new TabPanel();
		tabbedPane.add("test", tabPanel); //파일이름과 panel
		add(tabbedPane);
		setVisible(true);
	}
	public void addTap(Model model, TabPanel tabPanel) {
		if(checkModel(model)) {  //새로운 모델이라면
			tabPanelMap.put(model, tabPanel);
			panelToModel.put(tabPanel, model);
			tabbedPane.add(model.getFileName(), tabPanel); //파일이름과 panel
		}
	}
	private boolean checkModel(Model model) {
		Iterator<Model> it = tabPanelMap.keySet().iterator();
		while(it.hasNext()) {
			if(model.getFilePath().equals(it.next().getFilePath()))  //path가 이미 있다면
				return false;
		}
		return true;
	}
	private void createMenu() {
		menuBar = new JMenuBar();

		getContentPane().add(menuBar);

		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);//

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

		runMenu = new JMenu("Run");
		menuBar.add(runMenu);

		compileMenuItem = new JMenuItem("Compile");
		runMenu.add(compileMenuItem);

		runMenuItem = new JMenuItem("Run");
		runMenu.add(runMenuItem);

		setJMenuBar(menuBar);
	}

	public JMenu getFileMenu() {
		return fileMenu;
	}

	public void setFileMenu(JMenu fileMenu) {
		this.fileMenu = fileMenu;
	}

	public JMenu getRunMenu() {
		return runMenu;
	}

	public void setRunMenu(JMenu runMenu) {
		this.runMenu = runMenu;
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

	public HashMap<Model, TabPanel> getTabPanelMap() {
		return tabPanelMap;
	}

	public void setTabPanelMap(HashMap<Model, TabPanel> tabPanelMap) {
		this.tabPanelMap = tabPanelMap;
	}

	public HashMap<TabPanel, Model> getPanelToModel() {
		return panelToModel;
	}

	public void setPanelToModel(HashMap<TabPanel, Model> panelToModel) {
		this.panelToModel = panelToModel;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	
}
