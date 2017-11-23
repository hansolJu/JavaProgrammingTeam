/**
 * 어플 뷰단 완성
 * 작성자: 주한솔
 * 수정자: 주한솔
 * 수정내용: 레이아웃변경 (17.11.23)
 * 최종수정일: 17.11.23
 * 
 */
package apps.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollBar;

public class View {
	private JFrame frame;
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
	
	/**
	 * View constructor
	 * @param title title used for the frame
	 */
	public View(String title) {
		//Create the principal frame
		frame  = new JFrame(title);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 493, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);//프레임 중앙에 배치.
		frame.setResizable(false);
		frame.setVisible(true);
		
		//Create UI elements
		
		openFilePath = new JTextField();
		openFilePath.setBounds(2, 2, 370, 35);
		openFilePath.setColumns(10);
		saveFilePath = new JTextField();
		saveFilePath.setBounds(2,2,370, 35);
		saveFilePath.setColumns(10);
		
		openButton = new JButton("Open");
		openButton.setSize(90, 35);
		openButton.setLocation(378, 2);
		saveButton = new JButton("Save");
		saveButton.setSize(90, 35);
		saveButton.setLocation(378, 2);
		compileButton = new JButton("Compile");
		compileButton.setSize(90, 35);
		compileButton.setLocation(0, 2);
		runButton = new JButton("Run");
		runButton.setSize(90, 35);
		runButton.setLocation(95, 2);
		saveErrorButton = new JButton("SaveError");
		saveErrorButton.setSize(100, 35);
		saveErrorButton.setLocation(190, 2);
		deleteButton = new JButton("Delete");
		deleteButton.setSize(90, 35);
		deleteButton.setLocation(295, 2);
		clearButton = new JButton("Clear");
		clearButton.setSize(90, 35);
		clearButton.setLocation(390, 2);
		
		editingWindowArea = new JTextArea();
		JScrollPane editScrollPane = new JScrollPane(editingWindowArea);
		editScrollPane.setBounds(2, 2, 476, 315);
		editingWindowArea.setCaretPosition(editingWindowArea.getDocument().getLength());
		resultWindowArea = new JTextArea();
		JScrollPane resultScrollPane = new JScrollPane(resultWindowArea);
		resultScrollPane.setBounds(2, 2, 476, 316);
		resultWindowArea.setCaretPosition(resultWindowArea.getDocument().getLength());
		
		//Add UI element to frame
		JPanel open = new JPanel();
		open.setSize(480, 40);
		open.setLocation(2, 0);
		JPanel save = new JPanel();
		save.setSize(480, 40);
		save.setLocation(2, 40);
		JPanel openAndSave = new JPanel();
		openAndSave.setSize(480, 80);
		JPanel edit = new JPanel();
		edit.setSize(480, 320);
		edit.setLocation(2, 80);
		JPanel buttonSet = new JPanel();
		buttonSet.setSize(480, 40);
		buttonSet.setLocation(2, 400);
		JPanel result = new JPanel();
		result.setSize(480, 320);
		result.setLocation(2, 440);
		
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
		/*
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(editingWindowArea, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
						.addComponent(resultWindowArea, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(compileButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(runButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(saveErrorButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(clearButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(openFilePath, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
								.addComponent(saveFilePath, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(saveButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(openButton, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
							.addGap(6)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(openFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(openButton)
							.addGap(7)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(saveButton)
								.addComponent(saveFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editingWindowArea, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(compileButton)
						.addComponent(clearButton)
						.addComponent(runButton)
						.addComponent(saveErrorButton)
						.addComponent(deleteButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(resultWindowArea, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		*/
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
