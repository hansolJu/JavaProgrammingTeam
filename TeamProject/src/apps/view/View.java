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

public class View {
	private JFrame frame;
	private JTextField openFilePath;
	private JTextField saveFilePath;
	private JButton openButton;
	private JButton saveButton;
	private JButton compileButton;
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 100);
		frame.setLocationRelativeTo(null);//프레임 중앙에 배치.
		frame.setVisible(true);
		
		//Create UI elements
		openFilePath = new JTextField(20);
		saveFilePath = new JTextField(20);
		openButton = new JButton("Open");
		saveButton = new JButton("Save");
		compileButton = new JButton("Compile");
		saveErrorButton = new JButton("SaveError");
		deleteButton = new JButton("Delete");
		clearButton = new JButton("Clear");
		editingWindowArea = new JTextArea();
		resultWindowArea = new JTextArea();
		
		//Add UI element to frame
		GroupLayout layout = new GroupLayout(frame.getContentPane());
		layout.setAutoCreateGaps(true);//컨테이너와 컨테이너의 경계에 접하는 컴퍼넌트 간의 간격이 자동으로 생성할지 여부를 설정합니다
		layout.setAutoCreateContainerGaps(true);//컨테이너와 컨테이너의 경계에 접하는 컴퍼넌트 간의 간격이 자동으로 생성할지 여부를 설정합니다.
		layout.setHorizontalGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
								.addComponent(openFilePath)
								.addComponent(openButton)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
								.addComponent(saveFilePath)
								.addComponent(saveButton)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
								.addComponent(editingWindowArea)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
								.addComponent(compileButton)
								.addComponent(saveErrorButton)
								.addComponent(deleteButton)
								.addComponent(clearButton)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
								.addComponent(resultWindowArea)
		);
		
		layout.linkSize(SwingConstants.HORIZONTAL,openButton,saveButton,compileButton,saveErrorButton,deleteButton,clearButton);
		layout.linkSize(SwingConstants.HORIZONTAL,openFilePath,saveFilePath);
		layout.linkSize(SwingConstants.HORIZONTAL,editingWindowArea,resultWindowArea);
		
		frame.getContentPane().setLayout(layout);
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
	
	
}
