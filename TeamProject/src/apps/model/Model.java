/**
 * 파일의 정보가 들어간 model 클래스
 * 싱글턴 패턴으로 작성
 * 
 * 작성일 : 17.11.16
 * 수정일 : 17.11.16
 * 작성자 : 정은진
 * 
 */
package apps.model;

public class Model {
	private static Model instance = new Model();
	private String filePath;
	private String fileName;
	private String fileDir;
	private boolean isCompiled =false;
	
	private Model() {
	}
	public static Model getInstance() {
		return instance;
	}
	public String getFilePath() {
		return filePath;
	}
	public boolean getIsCompiled() {
		return isCompiled;
	}
	public void setIsCompiled(boolean isCompiled) {
		this.isCompiled = isCompiled;
	}
	public String getFileDir() {
		return fileDir;
	}
	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
