package apps.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import apps.core.*;
import apps.core.Compiler;
import apps.model.Model;

public class Controller {
	private Model model;
	private View view;
	private FileSys fileSys;
	private Compiler compiler;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		this.fileSys = new FileSys();
		this.compiler = new Compiler();
	}
	private void initView() {
		view
	}
	private void initController() {
		view.getOpenButton.addActionListener(new openJavaFileActionListener());
		view.getSaveButton.addActionListener(new saveJavaFileActionListener());
		view.getCompileButton.addActionListener(new compileActionListener());
		view.getSaveErrorsButton.addActionListener(new saveErrorsActionListener());
		view.getDeleteButton.addActionListener(new deleteJavaFileActionListener());
		view.getClearButton.addActionListener(new clearActionListener());
	}
	private class openJavaFileActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			view.getResultArea.setText("");
			view.getEditArea.setText("");
			ArrayList<String> lines;
			String filePath = view.getOpenTextField().getText();
			if (fileSys.inputFile(filePath)) {
				lines = readFile(filePath);
				for(String line : lines)
					view.getEditArea().setText(view.getEditArea().getText() + line + "\n");
			}
			else
				view.getResultArea.setText("파일을 찾을 수 없습니다...");
		}
	}
	private class saveJavaFileActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}

	}
	private class compileActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			view.getResultArea.setText("");
			ArrayList<String> lines;
			//지훈이가 해줭 compiler.setFile(fullFilePath);
			
		}
	}
	private class saveErrorsActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//지훈이가
		}

	}
	private class deleteJavaFileActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(deleteFile()) 
				view.getResultArea.setText("Delete Success!!");
			else
				view.getResultArea.setText("Delete Fail!!");
		}

	}
	private class clearActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			view.getFileOpenField().setText("");
			view.getFileSaveField().setText("");
			view.getEditArea().setText("");
			view.getResultArea().setText("");
		}
	}
	
	//fileSys
	public boolean inputFile(String filePath) {
		//if file exist
		if(checkFile(filePath)) {
			model.setFilePath(filePath);
			setFile();
			return true;
		}
		else
			return false;
	}
	private void setFile() {
		String [] fileInfoArray = FileDirSeperator(model.getFilePath());
		System.out.println(fileInfoArray[0] +  fileInfoArray[1]); //test
		model.setFileDir(fileInfoArray[0]);
		model.setFileName(fileInfoArray[1]);
	}
	public boolean checkFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) 
			return true;
		else 
			return false;
	}
	
	public boolean deleteFile() {
		File file = new File(model.getFilePath());
		if(file.delete()) {
			model.setFilePath("");
			model.setFileDir("");
			model.setFileName("");
			System.out.println("파일을 삭제 하였습니다.");
			return true;
		}
		else {
			System.out.println("파일이 존재하지 않습니다..");
			return false;
		}
	}
	/**
	 * 
	 * @param compiledFileName
	 * 파일명과 디렉터리명을 분리하여 필드 변수에 저장
	 */
	private String[] FileDirSeperator(String absolutePath) {
		String [] fileDirArray;
		if(!checkFile(absolutePath)) {
			fileDirArray = new String[2];
			return fileDirArray;
		}
		fileDirArray = model.getFilePath().split("////");
		StringBuilder filePath = new StringBuilder();
		String [] fullFilePath = new String[2];
		for(int i=0; i<fileDirArray.length; i++) {
			if(i == fileDirArray.length -1) {  //필드 변수에 값들을 저장하고 return
				fullFilePath[1] = fileDirArray[i];
				fullFilePath[0] = filePath.toString();
			}
			else {
			filePath.append(fileDirArray[i]);
			filePath.append("\\\\");
			}
		}
		return fullFilePath;
	}
	public boolean saveJavaFile(String openFileName, String saveFileName) throws IOException {
		if(saveFileName.equals("")) {
			saveFile(openFileName, readFile(openFileName));
			return true;
		}
		if(openFileName == saveFileName && !saveFileName.equals(""))
			return false;
		else {	
			saveFile(saveFileName, readFile(openFileName));
			return true;
		}
	}
	public void saveFile(String saveFileName, ArrayList<String> list) throws IOException {
		File file = new File(saveFileName);
		BufferedWriter fw = new BufferedWriter(new FileWriter(file, false));
		for(String line : list)
			fw.write(line);
		fw.close();
	}
	public ArrayList<String> readFile(String filePath) {
		String line = null;
		ArrayList<String> lines = new ArrayList<String>();
		File file = new File(filePath);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while((line = reader.readLine())!=null) {
				lines.add(line);
			}
			reader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}

}
