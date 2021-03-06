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
import java.util.Arrays;

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
			if(checkFile(filePath)) {
				inputFile(filePath);
				lines = readFile(filePath);
				for(String line : lines)
					view.getEditingWindowArea().setText(view.getEditingWindowArea().getText() + line + "\n");
			}
			else {
				model.setFilePath("");
				inputFile(filePath);
				view.getResultWindowArea().setText("파일을 찾을 수 없습니다.");
			}
		}
	}
	private class saveJavaFileActionListener implements ActionListener {	//자바 파일 저장 리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			view.getResultWindowArea().setText("");
			try {
				saveJavaFile(view.getOpenFilePath().getText(), view.getSaveFilePath().getText());
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
			if(model.getFileDir() ==  null || model.getFileName() == null) {
				view.getResultWindowArea().setText("열린 파일이 없습니다.\n파일을 열어주세요.");
				return;
			}
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
			if(model.getFileDir() ==  null || model.getFileName() == null) {
				view.getResultWindowArea().setText("열린 파일이 없습니다.\n파일을 열어주세요.");
			}
			else {
				compiler.setFile(model.getFileDir(), model.getFileName());
				lines = compiler.compiler();
				for(String line : lines) {
					view.getResultWindowArea().setText(view.getResultWindowArea().getText()+line);
				}
			}
		}
	}
	//save error 버튼 리스너 구현
	private class saveErrorsActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(model.getIsCompiled() == false) {
					if(model.getFileName() == null || model.getFileDir() == null) {
						view.getResultWindowArea().setText("열린 파일이 없습니다.\n파일을 열어주세요.");
					}
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
					view.getResultWindowArea().setText("Not Error!!");
			}catch(IOException ie) {
				view.getResultWindowArea().setText((ie.getMessage()));
			}
		}
	}
	private class deleteJavaFileActionListener implements ActionListener{	//자바 파일 삭제 리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			if(deleteFile()) 
				view.getResultWindowArea().setText("파일을 삭제 되었습니다.");
			else
				view.getResultWindowArea().setText("파일을 삭제 할수 없습니다.\n파일을 확인해주세요.");
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

	public void inputFile(String filePath) {  //파일 경로를 model클래스에 저장
		model.setFilePath(filePath);
		setFile();
	}
	private void setFile() {  //파일 정보를 model클래스에 저장
		String [] fileInfoArray = FileDirSeperator();
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
		if(model.getFilePath() == null)
			return false;
		File file = new File(model.getFilePath());
		if(file.delete()) {
			model.setFilePath("");
			model.setFileDir("");
			model.setFileName("");
			view.getResultWindowArea().setText("파일을 삭제하였습니다.");
			return true;
		}
		else {
			view.getResultWindowArea().setText("파일이 존재 하지 않습니다.");
			return false;
		}
	}
	/**
	 * @param compiledFileName
	 * 파일명과 디렉터리명을 분리하여 필드 변수에 저장
	 */
	private String[] FileDirSeperator() {
		String [] fileArray = new String[2];
		if(!checkFile(model.getFilePath())) {
			return fileArray;
		}
		String [] splittedArray = model.getFilePath().split("\\\\");
		StringBuilder dirPath = new StringBuilder();
		for(int i=0; i<splittedArray.length; i++) {
			if(i == splittedArray.length -1) {
				fileArray[1] = splittedArray[i];
				fileArray[0] = dirPath.toString();
			}
			else {
				dirPath.append(splittedArray[i]);
				dirPath.append("\\\\");
			}
		}
		return fileArray;
	}
	/**
	 * @param openFilePath : 저장하려는 경로가 지정되지 않았을 경우 오픈 파일에 덮어쓰기
	 * @param saveFilePath : 저장하려는 파일 경로
	 * @call saveFile : 파일 중복을 막고, saveError를 위해 작성된 saveFile메소드를 호출하여 파일에 출력
	 * @return
	 * @throws IOException
	 */
	public void saveJavaFile(String openFilePath, String saveFilePath) throws IOException {
		String [] lines = view.getEditingWindowArea().getText().split("\n");
		ArrayList<String> list= new ArrayList<>(Arrays.asList(lines));  //배열 -> 리스트
		if(saveFilePath.equals("")) {  //저장 경로에 아무 텍스트가 없을 때--> 오픈경로와 동일하게 저장.
			if(openFilePath.equals("")) {
				view.getResultWindowArea().setText("지정한 파일이 없습니다.");
				return;
			}
			view.getSaveFilePath().setText(openFilePath);//savepath에 경로 박아주기
			saveFile(openFilePath, list);
			view.getResultWindowArea().setText(openFilePath + "에 저장했습니다.");
		}
		/*
		else if(openFilePath.equals(saveFilePath)) { //오픈 경로와 세이브 경로가 같을 때
			view.getResultWindowArea().setText("열린 파일과 중복된 파일명입니다. 다른 저장 파일명으로 입력해주세요");
			return;
		}
		*/
		else if(openFilePath.equals("")&(isFile(saveFilePath)==true)){
			view.getResultWindowArea().setText("이미 존재하는 파일명입니다. 다른 저장 파일명으로 입력해주세요");
			return;
		}
		else{	
			saveFile(saveFilePath, list);
			view.getResultWindowArea().setText(view.getSaveFilePath().getText() + "에 저장했습니다. \n");
		}
	}
	public void saveFile(String saveFilePath, ArrayList<String> list) throws IOException {  //saveFilePath에 list를 파일 출력
		File file = new File(saveFilePath);
		BufferedWriter fw = new BufferedWriter(new FileWriter(file, false));
		for(String line : list) 
			fw.write(line + "\n");
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
	public boolean isFile(String FilePath) {// 파일 존재 여부 판단
		File f = new File(FilePath);
		if (f.isFile()) {
			return true;
		} else {
			return false;
		}
	}
}
