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
 * 수정일:17.12.16
 * 수정자:주한솔
 * 수정내용:
 * 1.요구사항에 맞게 리스너 수정
 * 2.파일이 안열였을때 클릭시 경고 추가
 * @author 정은진
 * 
 */
package controller;

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

	private class saveAsJavaFileActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Component selected = gui.getTabbedPane().getSelectedComponent();
			TabPanel tabPanel = (TabPanel) gui.getTabbedPane().getSelectedComponent();
			if(selected != null) {
				FileDialog saveDialog = new FileDialog(gui, "저장", FileDialog.SAVE);
				saveDialog.setVisible(true);

				if(saveDialog.getFile() == null)
					return;
				String filePath = saveDialog.getDirectory() + saveDialog.getFile();

				if (selected != null) {
					try {
						saveFile(filePath, tabPanel.getEditingTextArea().getText());
						tabPanel.getResultTextArea().setText(filePath + "에 저장 성공.");
					} catch (IOException ie) {
						tabPanel.getResultTextArea().setText(ie.getMessage());
					}
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
				gui.getTabPanelMap().remove(gui.getPanelToModel().get(tabPanel));  //해쉬맵에서 제거
				gui.getPanelToModel().remove(tabPanel);  //해쉬맵에서 제거
				gui.getTabbedPane().remove(tabPanel);  //tabpane에서 제거
			}else
				JOptionPane.showMessageDialog(null, "파일을 먼저 열어주세요.", "경고", JOptionPane.WARNING_MESSAGE);
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
			chooser.setFileFilter(txtFilter);
			chooser.setFileFilter(javaFilter);
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

			gui.addTap(model, tabPanel);
		}
	}

	private class saveJavaFileActionListener implements ActionListener { // 자바 파일 저장 리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			Component selected = gui.getTabbedPane().getSelectedComponent();
			TabPanel tabPanel = (TabPanel) gui.getTabbedPane().getSelectedComponent();
			Model model = gui.getPanelToModel().get(tabPanel);
			if (selected != null) {
				if (model.getFilePath() != null) //?
					try {
						saveFile(model.getFilePath(), tabPanel.getEditingTextArea().getText());
						tabPanel.getResultTextArea().setText(model.getFilePath() + "에 저장 성공.");
					} catch (IOException ie) {
						tabPanel.getResultTextArea().setText(ie.getMessage());
					}
			}else
				JOptionPane.showMessageDialog(null, "파일을 먼저 열어주세요.", "경고", JOptionPane.WARNING_MESSAGE);
		}
	}

	private class runActionListener implements ActionListener { // 자바 파일 실행 리스너
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Component selected = gui.getTabbedPane().getSelectedComponent();
			TabPanel tabPanel = (TabPanel) gui.getTabbedPane().getSelectedComponent();
			Model model = gui.getPanelToModel().get(tabPanel);
			if (selected != null) {
				tabPanel.getResultTextArea().setText("");
				ArrayList<String> list;
				if (model.getFileDir() == null || model.getFileName() == null) {
					tabPanel.getResultTextArea().setText("열린 파일이 없습니다.\n파일을 열어주세요.");
					return;
				}
				runner.setFile(model.getFileDir(), model.getFileName());
				list = runner.run(model.getIsCompiled());
				for (String line : list)
					tabPanel.getResultTextArea().setText(tabPanel.getResultTextArea().getText() + line + "\n");
			}else
				JOptionPane.showMessageDialog(null, "파일을 먼저 열어주세요.", "경고", JOptionPane.WARNING_MESSAGE);
		}
	}

	// compile 버튼 리스터 구현
	private class compileActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Component selected = gui.getTabbedPane().getSelectedComponent();
			TabPanel tabPanel = (TabPanel) gui.getTabbedPane().getSelectedComponent();
			Model model = gui.getPanelToModel().get(tabPanel);
			if (selected != null) {
				tabPanel.getResultTextArea().setText("");
				ArrayList<String> lines;
				if (model.getFileDir() == null || model.getFileName() == null) {
					tabPanel.getResultTextArea().setText("열린 파일이 없습니다.\n파일을 열어주세요.");
				} else {
					compiler.setFile(model.getFileDir(), model.getFileName());
					lines = compiler.compiler();
					for (String line : lines)
						tabPanel.getResultTextArea().setText(tabPanel.getResultTextArea().getText() + line);
				}
			}else
				JOptionPane.showMessageDialog(null, "파일을 먼저 열어주세요.", "경고", JOptionPane.WARNING_MESSAGE);
		}
	}

	public ArrayList<String> readFile(String filePath) { // 해당 파일을 불러와서 list로 반환
		String line = null;
		ArrayList<String> lines = new ArrayList<String>();
		File file = new File(filePath);
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
