/**
 * View constructor
 * @param title used for the frame
 * 작성자: 주한솔
 * 수정자: 주한솔
 * 수정내용: 레이아웃변경 (17.11.23)
 * 수정자: 주한솔
 * 수정내용: 레이아웃최적화(17.11.25)
 * 수정자: 주한솔
 * 수정내용: Jpanel,JScrollPane 접근 지정자 추가(17.11.25)
 * 
 * 최종수정일: 17.11.25
 * 
 */
package apps.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class View {
	private JFrame frame;
	private JPanel open;
	private JPanel save;
	private JPanel openAndSave;
	private JPanel edit;
	private JPanel buttonSet;
	private JPanel result;
	private JTextField openFilePath;
	private JTextField saveFilePath;
	private JButton openButton;
	private JButton saveButton;
	private JButton compileButton;
	private JButton runButton;
	private JButton saveErrorButton;
	private JButton deleteButton;
	private JButton clearButton;
	private JTextArea editingWindowArea;
	private JTextArea resultWindowArea;
	private JScrollPane editScrollPane;
	private JScrollPane resultScrollPane;
	

	public View(String title) {
		//Create the principal frame
		frame  = new JFrame(title);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 493, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);//프레임 중앙에 배치.
		frame.setResizable(false);
		// panel
		open = new JPanel();
		open.setSize(483, 40);
		open.setLocation(2, 0);
		save = new JPanel();
		save.setSize(483, 40);
		save.setLocation(2, 40);
		openAndSave = new JPanel();
		openAndSave.setSize(485, 80);
		edit = new JPanel();
		edit.setSize(480, 320);
		edit.setLocation(2, 80);
		buttonSet = new JPanel();
		buttonSet.setSize(480, 40);
		buttonSet.setLocation(2, 400);
		result = new JPanel();
		result.setSize(480, 320);
		result.setLocation(2, 440);
		//Create UI elements
			//TextField
		openFilePath = new JTextField();
		openFilePath.setBounds(2, 5, 380, 30);
		openFilePath.setColumns(10);
		saveFilePath = new JTextField();
		saveFilePath.setBounds(2,5,380, 30);
		saveFilePath.setColumns(10);
			//Button
		openButton = new JButton("Open");
		openButton.setSize(90, 35);
		openButton.setLocation(390, 2);
		saveButton = new JButton("Save");
		saveButton.setSize(90, 35);
		saveButton.setLocation(390, 2);
		compileButton = new JButton("Compile");
		compileButton.setSize(90, 35);
		compileButton.setLocation(2, 2);
		runButton = new JButton("Run");
		runButton.setSize(90, 35);
		runButton.setLocation(96, 2);
		saveErrorButton = new JButton("SaveError");
		saveErrorButton.setSize(100, 35);
		saveErrorButton.setLocation(191, 2);
		deleteButton = new JButton("Delete");
		deleteButton.setSize(90, 35);
		deleteButton.setLocation(295, 2);
		clearButton = new JButton("Clear");
		clearButton.setSize(90, 35);
		clearButton.setLocation(390, 2);
			//ScrollPane&TextArea
		editingWindowArea = new JTextArea();
		editScrollPane = new JScrollPane(editingWindowArea);
		editScrollPane.setBounds(2, 2, 479, 315);
		editingWindowArea.setCaretPosition(editingWindowArea.getDocument().getLength());
		
		resultWindowArea = new JTextArea();
		resultScrollPane = new JScrollPane(resultWindowArea);
		resultScrollPane.setBounds(2, 2, 479, 316);
		resultWindowArea.setCaretPosition(resultWindowArea.getDocument().getLength());
		
		//Add UI element to frame
			//Add UI
		frame.getContentPane().add(openAndSave);
		openAndSave.setLayout(null);
			openAndSave.add(open);
				open.setLayout(null);
				open.add(openFilePath);
				open.add(openButton);
			openAndSave.add(save);
				save.setLayout(null);
				save.add(saveFilePath);
				save.add(saveButton);
		frame.getContentPane().add(edit);
			edit.setLayout(null);
			edit.add(editScrollPane);
		frame.getContentPane().add(buttonSet);
			buttonSet.setLayout(null);
			buttonSet.add(compileButton);
			buttonSet.add(runButton);
			buttonSet.add(saveErrorButton);
			buttonSet.add(deleteButton);
			buttonSet.add(clearButton);
		frame.getContentPane().add(result);
			result.setLayout(null);
			result.add(resultScrollPane);
		frame.setVisible(true);//컴포넌트를 다 추가하고 설정해야지 화면이 잘보임
	}
	/**
	 * generate a getter and setter
	 * @return
	 */

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getOpenFilePath() {
		return openFilePath;
	}

	public void setOpenFilePath(JTextField openFilePath) {
		this.openFilePath = openFilePath;
	}

	public JTextField getSaveFilePath() {
		return saveFilePath;
	}

	public void setSaveFilePath(JTextField saveFilePath) {
		this.saveFilePath = saveFilePath;
	}

	public JButton getOpenButton() {
		return openButton;
	}

	public void setOpenButton(JButton openButton) {
		this.openButton = openButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JButton getCompileButton() {
		return compileButton;
	}

	public void setCompileButton(JButton compileButton) {
		this.compileButton = compileButton;
	}

	public JButton getSaveErrorButton() {
		return saveErrorButton;
	}

	public void setSaveErrorButton(JButton saveErrorButton) {
		this.saveErrorButton = saveErrorButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public JButton getClearButton() {
		return clearButton;
	}

	public void setClearButton(JButton clearButton) {
		this.clearButton = clearButton;
	}

	public JTextArea getEditingWindowArea() {
		return editingWindowArea;
	}

	public void setEditingWindowArea(JTextArea editingWindowArea) {
		this.editingWindowArea = editingWindowArea;
	}

	public JTextArea getResultWindowArea() {
		return resultWindowArea;
	}

	public void setResultWindowArea(JTextArea resultWindowArea) {
		this.resultWindowArea = resultWindowArea;
	}
	public JButton getRunButton() {
		return runButton;
	}
	public void setRunButton(JButton runButton) {
		this.runButton = runButton;
	}
}
