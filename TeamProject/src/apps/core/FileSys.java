package apps.core;

import java.io.File;
import java.util.Scanner;

public class FileSys {
	private String fileName = null;
	private String absolutePath = null;

	public void inputFile() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Type Java Filename:");
		try {
			this.fileName = scanner.nextLine();
		} finally {
			//scanner.close();
		}
	}

	public void checkFile() {
		File file = new File(this.fileName);
		if (file.exists()) {
			absolutePath = file.getAbsolutePath();
		} else {
			System.out.println("파일이 존재하지 않습니다..");
		}
	}

	public String getFile() {
		return fileName;
	}

	public String getPath() {
		return absolutePath;
	}

	public boolean deleteFile() {
		if (this.fileName != null) {
			this.fileName = "";
			this.absolutePath = "";
			System.out.println("파일을 삭제 하였습니다.");
		} else {
			System.out.println("파일이 존재하지 않습니다..");
		}
		return false;
	}
	/**
	 * 
	 * @param compiledFileName
	 * 파일명과 디렉터리명을 분리하여 필드 변수에 저장
	 */
	public String[] FileDirSeperator(String absolutePath) {
		String [] fileDirArray = this.absolutePath.split("\\\\");
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
}
