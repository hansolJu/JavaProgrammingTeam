/**
 * 컨트롤러 작성
 * 작성일 : 17.11.16
 * 수정자 : 정은진
 * 수정일 : 17.11.23
 * 수정 내용 : open버튼 예외처리
 * 수정자 : 주한솔
 * 수정일 : 17.11.25
 * 수정내용 : 파일을 찾을수 없습니다...->파일을 찾을수 없습니다. 와 같은 오류메세지 출력 수정
 * 수정자 : 주한솔
 * 수정일 : 17.11.26
 * 수정내용: 225라인 저장 로직 수정 if-if-else-->if- else if -else
 * @author 정은진
 * 
 */
package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.Compiler;
import core.Runner;
import model.Model;
import view.GUI;
import view.TabPanel;

public class Controller {
	private GUI gui;
	private Compiler compiler;
	private Runner runner;

	public Controller(GUI gui) { // 각 인스턴스 생성
		this.gui = gui;
		compiler = new Compiler();
		runner = new Runner();
	}

	public void initController() { // 각 컴포넌트에 리스너 추가
		gui.getOpenMenuItem().addActionListener(new openJavaFileActionListener());
		gui.getSaveMenuItem().addActionListener(new saveJavaFileActionListener());
		gui.getSaveAsMenuItem().addActionListener(new saveAsJavaFileActionListener());
		gui.getCloseMenuItem().addActionListener(new CloseActionListener());
		gui.getQuitMenuItem().addActionListener(new quitActionListener());
		gui.getCompileMenuItem().addActionListener(new compileActionListener());
		gui.getRunMenuItem().addActionListener(new runActionListener());
	}

	private class saveAsJavaFileActionListener implements ActionListener {  //다른이름으로 저장
		private JFileChooser chooser;
		public saveAsJavaFileActionListener() {
			chooser = new JFileChooser();// 객체 생성
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			Component selected = gui.getTabbedPane().getSelectedComponent();
			TabPanel tabPanel = (TabPanel) gui.getTabbedPane().getSelectedComponent();
			if(selected != null) {
				int ret = chooser.showSaveDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String filePath = chooser.getSelectedFile().getPath();
				File file = new File(filePath);
				if(file.exists()) {  //이미 있는 파일일 경우
					JOptionPane.showMessageDialog(null, "이미 존재하는 파일입니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
				try {
					saveFile(filePath, tabPanel.getEditingTextArea().getText());
					tabPanel.getResultTextArea().setText(filePath + "에 저장 성공.");
				} catch (IOException ie) {
					tabPanel.getResultTextArea().setText(ie.getMessage());
				}
			}
		}
	}
	private class quitActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	private class CloseActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Component selected = gui.getTabbedPane().getSelectedComponent();
			TabPanel tabPanel = (TabPanel) gui.getTabbedPane().getSelectedComponent();

			if (selected != null) {
				gui.getTabPanelMap().remove(gui.getTabPanelMap().getKey(tabPanel));  //해쉬맵에서 제거
				gui.getTabbedPane().remove(tabPanel);  //tabpane에서 제거
			}
		}
	}

	private class openJavaFileActionListener implements ActionListener { // 자바 파일 열기 리스너
		private JFileChooser chooser;
		private Model model;// 모델 하나 생성
		private ArrayList<String> lines; // 해당 모델에서 읽어올 String lines
		private TabPanel tabPanel; // 추가할 tabPane

		openJavaFileActionListener() {
			chooser = new JFileChooser();
			FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("텍스트문서(*.txt)", "txt");
			FileNameExtensionFilter javaFilter = new FileNameExtensionFilter("자바파일(*.java)", "java");
			chooser.setFileFilter(javaFilter);
			chooser.setFileFilter(txtFilter);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			model = new Model();
			tabPanel = new TabPanel();
			int ret = chooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			model.setFilePath(chooser.getSelectedFile().getPath());
			model.setFileName(chooser.getSelectedFile().getName());
			model.setFileDir(chooser.getSelectedFile().getParent()); // 모델에 저장

			lines = readFile(model.getFilePath()); // 해당 모델에서 읽어오기
			for (String line : lines) // editingErea에 넣기
				tabPanel.getEditingTextArea().setText(tabPanel.getEditingTextArea().getText() + line + "\n");

			gui.getTabbedPane().addKeyListener(new panelKeyActionListener());  //탭에 키리스너 추가
			gui.getTabbedPane().setFocusable(true);
			tabPanel.getEditingTextArea().addKeyListener(new panelKeyActionListener());  //editArea에 키리스너 추가
			tabPanel.getEditingTextArea().setFocusable(true);
			tabPanel.getResultTextArea().addKeyListener(new panelKeyActionListener());  //resultArea에 키리스너 추가
			tabPanel.getResultTextArea().setFocusable(true);
			gui.addTap(model, tabPanel);
		}
	}

	private class saveJavaFileActionListener implements ActionListener { // 자바 파일 저장 리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			Component selected = gui.getTabbedPane().getSelectedComponent();
			TabPanel tabPanel = (TabPanel) gui.getTabbedPane().getSelectedComponent();
			Model model = gui.getTabPanelMap().getKey(tabPanel);
			if (selected != null) {
				if (model.getFilePath() != null)
					model.setIsCompiled(false);  //다시 컴파일하도록
				try {
					int result = JOptionPane.showConfirmDialog(null, "저장하시겠습니까?",
							"저장", JOptionPane.YES_NO_OPTION);
					if(result != JOptionPane.YES_OPTION) 
						return;
					saveFile(model.getFilePath(), tabPanel.getEditingTextArea().getText());
				} catch (IOException ie) {
					tabPanel.getResultTextArea().setText(ie.getMessage());
				}
			}
		}
	}

	private class runActionListener implements ActionListener { // 자바 파일 실행 리스너
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Component selected = gui.getTabbedPane().getSelectedComponent();
			TabPanel tabPanel = (TabPanel) gui.getTabbedPane().getSelectedComponent();
			if(selected != null) {
				Model model = gui.getTabPanelMap().getKey(tabPanel);
				run(model, tabPanel);
			}
		}
	}

	// compile 버튼 리스터 구현
	private class compileActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Component selected = gui.getTabbedPane().getSelectedComponent();
			TabPanel tabPanel = (TabPanel) gui.getTabbedPane().getSelectedComponent();
			if(selected != null) {
				Model model = gui.getTabPanelMap().getKey(tabPanel);
				compile(model, tabPanel);
			}
		}
	}
	private class panelKeyActionListener extends KeyAdapter {
		private final Set<Integer> pressedSet = new HashSet<>();  //현재 눌러진 키들을 저장하는 set
		@Override
		public synchronized void keyPressed(KeyEvent e) {
			pressedSet.add(e.getKeyCode());  //눌러진 키 추가
			if (pressedSet.size() == 2) {  //눌러진 키가 2개일 경우
				if(pressedSet.contains(KeyEvent.VK_CONTROL) && pressedSet.contains(KeyEvent.VK_R)) {  //컨트롤키와 r키를 갖고있을 경우
					Component selected = gui.getTabbedPane().getSelectedComponent();  //컴파일
					TabPanel tabPanel = (TabPanel) gui.getTabbedPane().getSelectedComponent();
					if(selected != null) {
						Model model = gui.getTabPanelMap().getKey(tabPanel);
						compile(model, tabPanel);
					}
				}
				else if(pressedSet.contains(KeyEvent.VK_CONTROL) && pressedSet.contains(KeyEvent.VK_F11)) {
					Component selected = gui.getTabbedPane().getSelectedComponent();  //컴파일
					TabPanel tabPanel = (TabPanel) gui.getTabbedPane().getSelectedComponent();
					if(selected != null) {
						Model model = gui.getTabPanelMap().getKey(tabPanel);
						run(model, tabPanel);
					}
				}
			}
		}
		@Override
		public synchronized void keyReleased(KeyEvent e) {
			pressedSet.remove(e.getKeyCode());
		}
	}
	private void compile(Model model, TabPanel tabPanel) {
		tabPanel.getResultTextArea().setText("");
		ArrayList<String> lines;
		if (model.getFileDir() == null || model.getFileName() == null) {
			tabPanel.getResultTextArea().setText("열린 파일이 없습니다.\n파일을 열어주세요.");
		} else {
			lines = compiler.compile(model);
			for (String line : lines) {
				tabPanel.getResultTextArea().setText(tabPanel.getResultTextArea().getText() + line);
			}
		}
	}
	private void run(Model model, TabPanel tabPanel) {
		tabPanel.getResultTextArea().setText("");
		ArrayList<String> list;
		if (model.getFileDir() == null || model.getFileName() == null) {
			tabPanel.getResultTextArea().setText("열린 파일이 없습니다.\n파일을 열어주세요.");
			return;
		}
		list = runner.run(model);
		for (String line : list)
			tabPanel.getResultTextArea().setText(tabPanel.getResultTextArea().getText() + line + "\n");
	}
	public ArrayList<String> readFile(String filePath) { // 해당 파일을 불러와서 list로 반환
		String line = null;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	public void saveFile(String saveFilePath, String texts) throws IOException {  //saveFilePath에 list를 파일 출력
		String [] lines = texts.split("\n");
		ArrayList<String> list= new ArrayList<>(Arrays.asList(lines));  //배열 -> 리스트

		File file = new File(saveFilePath);
		BufferedWriter fw = new BufferedWriter(new FileWriter(file, false));
		for(String line : list) 
			fw.write(line + "\n");
		fw.close();
	}
}
