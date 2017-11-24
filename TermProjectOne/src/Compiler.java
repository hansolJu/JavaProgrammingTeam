import java.io.*;

public class Compiler {
	//파일 경로, 파일 이름
	private String filePath="";
	private String fileName="";

	//컴파일 실행 및 오류 발생 시 오류메세지 파일 생
	public synchronized boolean compiler(){
		boolean iscompiled = true;
		String errLine = null;
		String check = null;

		File file = new File(filePath, fileName);       /*EX)c:\filePath\fileName.java*/
		if(!file.exists()){
			System.out.println("업로드된 파일이 없습니다.");
			return false;
		}
		try{
			Process oProcess = new ProcessBuilder("javac", filePath+fileName).start();			//CMD 컴파일 명령

			BufferedReader stdError = new BufferedReader(new InputStreamReader(oProcess.getErrorStream()));

			//컴파일 오류 메세지 TXT파일에 저장 , 오류메세지가 있는지 한줄확인
			if((check = stdError.readLine())!=null){
				iscompiled = false;
				BufferedWriter out = new BufferedWriter(new FileWriter(fileName+".java_compile.error"+".txt"));
				System.out.println("2 compile error occured -"+fileName+".java.error");
				out.write(check);
				out.newLine();
				//다음줄 오류메세지 저장
				while((errLine = stdError.readLine()) != null){
					try{
						out.write(errLine);
						out.newLine();
					}catch(IOException e){
						System.out.println(e);
					}
				}
				out.close();
			}
			//컴파일 성공
			else
				System.out.println("compiled successfully……");

		}catch(IOException e){
			e.printStackTrace();
			System.out.println("에러! 외부 명령 실행에 실패했습니다.\n" + e.getMessage());
		}
		return iscompiled;
	}
	
	public void setFile(String[] fullFilePath){
		filePath = fullFilePath[0];
		fileName = fullFilePath[1];
	}
}