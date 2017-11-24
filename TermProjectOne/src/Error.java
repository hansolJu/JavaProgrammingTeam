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

	public Error(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName + ".error" + ".txt";
	}

	public void errorPrint() {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("에러 파일이 존재하지 않습니다.");
			return;
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
					//
				}
		}
	}
}