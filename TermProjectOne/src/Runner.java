import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 
 * fileName : 파일의 이름
 * filePath : 파일의 절대 경로
 * @author isk03
 * 파일의 이름을 받아 실행하는 클래스
 */
public class Runner {
	private String fileName;
	private String filePath;
	/**
	 * 
	 * 해당 파일을 실행(run)
	 */
	public synchronized void run(boolean isCompiled) {
		if(!checkCompiled(isCompiled))  //잘 컴파일 됐는지 검사
			return;
		try {
			String outLine, errLine;
			
			Process oProcess = new ProcessBuilder("java", "-cp", filePath, fileName.split(".java")[0]).start();  //cmd 명령어 ex)java -cp C:\temp\ test
			
			BufferedReader resultOut = new BufferedReader(new InputStreamReader(oProcess.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(oProcess.getErrorStream()));
			
			while ((errLine = stdError.readLine()) != null) System.err.println(errLine);
			while((outLine = resultOut.readLine()) != null)	System.out.println(outLine);
		}
		catch(IOException e) {
			System.err.println("자바 파일을 실행하지 못했습니다.\n" + e.getMessage());
		}
	}
	/**
	 * 
	 * @param FileDirArray index0:경로, index1:이름(~~~.java)
	 * 파일이름과 경로를 설정
	 */
	public void setFile(String[] FileDirArray) {
		filePath = FileDirArray[0];
		fileName = FileDirArray[1];
	}
	/**
	 * 
	 * @param isCompiled:컴파일 오류가 발생한 경우 오류처리를 위한 변수
	 * @return 파일이 잘 컴파일됐었다면 true
	 * 파일이 잘 컴파일됐는지 확인 
	 */
	private boolean checkCompiled(boolean isCompiled) {
		if(!isCompiled) {  //컴파일 오류가 발생한 상태에서 run 메뉴 발생 시, boolean값 반환
			System.out.println("");
			System.err.println("컴파일 오류");
			return false;
		}
		File file = new File(filePath, fileName);
		if(!file.exists()) {
			System.out.print(filePath+fileName);
			System.err.println("\t파일이 존재하지 않습니다..");
			return false;
		}
		return true;
	}
}