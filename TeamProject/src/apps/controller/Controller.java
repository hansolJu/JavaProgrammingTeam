/**
 * 컨트롤러 작성
 * 작성일 : 17.11.16
 * 수정일 : 17.11.23
 * 수정 내용 : run버튼에 이벤트 추가
 * @author 정은진
 * 
 */
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

import apps.core.Compiler;
import apps.core.Runner;
import apps.model.Model;
import apps.view.View;

public class Controller {
	private Model model;
	private View view;
	private Compiler compiler;
	private Runner runner;

	public Controller(Model model, View view) {  //각 인스턴스 생성
		this.model = model;
		this.view = view;
		this.compiler = new Compiler();
		this.runner = new Runner();
	}
	private void initView() {
	}

	public void initController() {  //각 컴포넌트에 리스너 추가
		view.getOpenButton().addActionListener(new openJavaFileActionListener());
		view.getSaveButton().addActionListener(new saveJavaFileActionListener());
		view.getCompileButton().addActionListener(new compileActionListener());
		view.getRunButton().addActionListener(new runActionListener());
		view.getSaveErrorButton().addActionListener(new saveErrorsActionListener());
		view.getDeleteButton().addActionListener(new deleteJavaFileActionListener());
		view.getClearButton().addActionListener(new clearActionListener());
	}
	private class openJavaFileActionListener implements ActionListener{	//자바 파일 열기 리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			view.getResultWindowArea().setText("");
			view.getEditingWindowArea().setText("");
			ArrayList<String> lines;
			String filePath = view.getOpenFilePath().getText();
			if (inputFile(filePath)) {
				lines = readFile(filePath);
				for(String line : lines)
					view.getEditingWindowArea().setText(view.getEditingWindowArea().getText() + line + "\n");
			}
			else
				view.getResultWindowArea().setText("파일을 찾을 수 없습니다...");
		}
	}
	private class saveJavaFileActionListener implements ActionListener {	//자바 파일 저장 리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			view.getResultWindowArea().setText("");
			try {
				if(saveJavaFile(view.getOpenFilePath().getText(), view.getSaveFilePath().getText())) {
					if(view.getSaveFilePath().getText().equals(""))
						view.getResultWindowArea().setText(view.getOpenFilePath().getText() + "에 저장했습니다.\n");
					else
						view.getResultWindowArea().setText(view.getSaveFilePath().getText() + "에 저장했습니다. \n");
				}
				else
					view.getResultWindowArea().setText("저장할 파일명을 입력해주세요");
			}catch(IOException ie) {
				view.getResultWindowArea().setText(ie.getMessage());
			}
		}
	}
	private class  runActionListener implements ActionListener {	//자바 파일 실행 리스너
		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.getResultWindowArea().setText("");
			ArrayList<String> list;
			list = runner.run(model.getIsCompiled());
			for(String line : list)
				view.getResultWindowArea().setText(view.getResultWindowArea().getText() + line + "\n");
		}
	}
	//compile 버튼 리스터 구현
	private class compileActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			view.getResultWindowArea().setText("");
			ArrayList<String> lines;
			compiler.setFile(model.getFileDir(), model.getFileName());
			lines = compiler.compiler();
			for(String line : lines) {
				view.getResultWindowArea().setText(view.getResultWindowArea().getText()+line);
			}
		}
	}
	//save error 버튼 리스너 구현
	private class saveErrorsActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(model.getIsCompiled() == false) {
					if(view.getResultWindowArea().getText() == null)
						return;
					else {
						ArrayList<String> lines = new ArrayList<String>(); 
						String[] result = view.getResultWindowArea().getText().split("\n");
						for(String line : result)
							lines.add(line);
						saveFile(model.getFileDir() + model.getFileName()+ ".error", lines);
						view.getResultWindowArea().setText("오류 저장을 완료했습니다.");
					}
				}
				else
					view.getEditingWindowArea().setText("compiled successfully.....");
			}catch(IOException ie) {
				view.getResultWindowArea().setText((ie.getMessage()));
			}
		}
	}
	private class deleteJavaFileActionListener implements ActionListener{	//자바 파일 삭제 리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			if(deleteFile()) 
				view.getResultWindowArea().setText("Delete Success!!");
			else
				view.getResultWindowArea().setText("Delete Fail!!");
		}

	}
	private class clearActionListener implements ActionListener{	//파일 변수 초기화 리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			view.getOpenFilePath().setText("");
			view.getSaveFilePath().setText("");
			view.getEditingWindowArea().setText("");
			view.getResultWindowArea().setText("");
		}
	}

	public boolean inputFile(String filePath) {  //파일 경로를 model클래스에 저장
		//if file exist
		if(checkFile(filePath)) {
			model.setFilePath(filePath);
			setFile();
			return true;
		}
		else
			return false;
	}
	private void setFile() {  //파일 정보를 model클래스에 저장
		String [] fileInfoArray = FileDirSeperator(model.getFilePath());
		System.out.println(fileInfoArray[0] +  fileInfoArray[1]); //test
		model.setFileDir(fileInfoArray[0]);
		model.setFileName(fileInfoArray[1]);
	}
	public boolean checkFile(String filePath) {  //파일이 존재하는지 
		File file = new File(filePath);
		if (file.exists()) 
			return true;
		else 
			return false;
	}

	public boolean deleteFile() {  //파일 삭제 후 model필드 변수 리셋
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
	/**
	 * 
	 * @param openFilePath : 저장하려는 경로가 지정되지 않았을 경우 오픈 파일에 덮어쓰기
	 * @param saveFilePath : 저장하려는 파일 경로
	 * @call saveFile : 파일 중복을 막고, saveError를 위해 작성된 saveFile메소드를 호출하여 파일에 출력
	 * @return
	 * @throws IOException
	 */
	public boolean saveJavaFile(String openFilePath, String saveFilePath) throws IOException {
		if(saveFilePath.equals("")) {
			saveFile(openFilePath, readFile(openFilePath));
			return true;
		}
		if(openFilePath == saveFilePath)
			return false;
		else {	
			saveFile(saveFilePath, readFile(openFilePath));
			return true;
		}
	}
	public void saveFile(String saveFilePath, ArrayList<String> list) throws IOException {  //saveFilePath에 list를 파일 출력
		File file = new File(saveFilePath);
		BufferedWriter fw = new BufferedWriter(new FileWriter(file, false));
		for(String line : list)
			fw.write(line);
		fw.close();
	}
	public ArrayList<String> readFile(String filePath) {  //해당 파일을 불러와서 list로 반환
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
