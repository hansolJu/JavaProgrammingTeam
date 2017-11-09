import java.io.*;

/**
 * FileName : Error.java Comment :
 * 
 * @version : 1.0
 * @author : blesk0111_jh
 * @date : 2017. 9. 30.
 */

public class Error {
	String filePath;
	String fileName;
	
	public void setFile(String[] fullFilePath) {
		filePath = fullFilePath[0];
		fileName = fullFilePath[1] + ".error" + ".txt";
	}

	public boolean errorPrint(boolean isCompiled) {
		//컴파일이 성공했을 경우에 5번을 실행 시켰을 경우
		if(isCompiled == true) {
			System.out.println("에러 파일이 존재하지 않습니다.");
			return false;
		}
		
		//fileName에 받아온 이름이 없을 경우
		if(fileName == null) {
			System.out.println("에러 파일이 존재하지 않습니다.11");
			return false;
		}
		//파일이름을 받아왔는데 에러파일이 존재 하지 않을경우
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("에러 파일이 존재하지 않습니다.");
			return false;
		}

		BufferedReader out = null;
		try {
			String line = null;	
			out = new BufferedReader(new FileReader(file));

			while ((line = out.readLine()) != null) {
				System.out.println(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		return true;
	}
}