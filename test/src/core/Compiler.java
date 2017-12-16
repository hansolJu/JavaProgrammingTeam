/**
 * 작성자 : UNS 채지훈
 * 작성일 : 2017.11.16 Thursday
 * 수정자 : 주한솔
 * 수정내용 : 주석위치 변경(제발 룰좀 지킵시다.)
 * 수정일 : 17.11.25
 * 수정일:17.12.16
 * 수정자:주한솔
 * 수정내용: 파일 위치 받는부분 수정
 * 수정일 : 17.12.16
 * 수정자 : 정은진
 * 수정내용 : model을 이용
 */
package core;

import java.io.*;
import java.util.ArrayList;

import model.Model;

public class Compiler {
	private String filePath;
	private String fileName;
	ArrayList<String> lines;

	public ArrayList<String> compile(Model model){
		filePath = model.getFilePath();
		fileName = model.getFileName();

		String errLine = null;
		File file = new File(filePath);       //EX)c:\filePath\fileName
		if(!file.exists()){
			lines = new ArrayList<String>();
			lines.add("파일이 없습니다.");
			model.setIsCompiled(false);
			return lines;
		}
		try{
			lines = new ArrayList<String>();
			Process oProcess = new ProcessBuilder("javac", filePath).start();         //CMD 컴파일 명령

			BufferedReader stdError = new BufferedReader(new InputStreamReader(oProcess.getErrorStream()));
			while ((errLine = stdError.readLine()) != null) lines.add(errLine+"\n");

			if(lines.size() == 0) {
				lines = new ArrayList<String>();
				model.setIsCompiled(true);
				lines.add("compiled successfully.....");
			}
			else {
				model.setIsCompiled(false);
			}
		}catch(IOException e){
			e.printStackTrace();
			model.setIsCompiled(false);
			lines = new ArrayList<String>();
			lines.add("에러! 외부 명령 실행에 실패했습니다.\n" + e.getMessage());
		}
		return lines;
	}
}