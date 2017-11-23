package apps.core;

import java.io.*;
import java.util.ArrayList;

import apps.model.Model;

/**
 * 작성자 : UNS
 * 수정일 : 2017.11.16 Thursday
 *
 */
public class Compiler {
	//파일 경로, 파일 이름
	private String filePath="";
	private String fileName="";
	ArrayList<String> lines = new ArrayList<String>();

	public void setFile(String filePath, String fileName){
		this.filePath = filePath;
		this.fileName = fileName;
	}

	public ArrayList<String> compiler(){
		Model model = Model.getInstance();
		String errLine = null;
		File file = new File(filePath, fileName);       //EX)c:\filePath\fileName
		if(!file.exists()){
			lines.add("파일이 없습니다.");
			model.setIsCompiled(false);
			return lines;
		}
		try{
			Process oProcess = new ProcessBuilder("javac", filePath+fileName).start();         //CMD 컴파일 명령

			BufferedReader stdError = new BufferedReader(new InputStreamReader(oProcess.getErrorStream()));
			while ((errLine = stdError.readLine()) != null) lines.add(errLine+"\n");

			if(lines.size() == 0) {
				model.setIsCompiled(true);
				lines.add("compiled successfully.....");
			}
			else {
				model.setIsCompiled(false);
			}

		}catch(IOException e){
			e.printStackTrace();
			model.setIsCompiled(false);
			lines.add("에러! 외부 명령 실행에 실패했습니다.\n" + e.getMessage());
		}
		return lines;
	}
}