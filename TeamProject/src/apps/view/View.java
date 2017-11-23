/**
 * 어플 뷰단 완성
 * 작성자: 주한솔
 * 수정자: 
 * 최종수정일: 17.11.16
 * 
 */
package apps.view;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 500, 782);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);//프레임 중앙에 배치.
		frame.setVisible(true);
		
		//Create UI elements
		openFilePath = new JTextField();
		openFilePath.setColumns(10);
		saveFilePath = new JTextField();
		saveFilePath.setColumns(10);
		
		openButton = new JButton("Open");
		saveButton = new JButton("Save");
		compileButton = new JButton("Compile");
		runButton = new JButton("Run");
		saveErrorButton = new JButton("SaveError");
		deleteButton = new JButton("Delete");
		clearButton = new JButton("Clear");
		
		editingWindowArea = new JTextArea();
		resultWindowArea = new JTextArea();
		
		//Add UI element to frame
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(resultWindowArea, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
						.addComponent(editingWindowArea, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
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
								.addComponent(openFilePath, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
								.addComponent(saveFilePath, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
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
