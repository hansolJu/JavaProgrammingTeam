import java.util.Scanner;

public class Main {
	Scanner scanner = new Scanner(System.in);
	private boolean isCompiled = false;
	FileSys fileSys = new FileSys(); 
	Runner runner = new Runner();
	Compiler compiler = new Compiler();
	Error error = new Error();
	
	//선택지를 알려주는 함수
	private void view() {
		liner();
		System.out.println("1. Java File Upload");
		System.out.println("2. Complie");
		System.out.println("3. Run");
		System.out.println("4. Reset");
		System.out.println("5. Complie Error File");
		System.out.print("Choice: ");
	}
	//입력이 끝나고 뷰를 꾸며주는 함수
	private void liner(){
		System.out.println("############################");
	}
	
	private void select() {
		while (true) {
			switch (scanner.nextInt()) {
			case 1:
				oneCase();
				break;
			case 2:
				twoCase();
				break;
			case 3:
				threeCase();
				break;
			case 4:
				fourCase();
				break;
			case 5:
				fiveCase();
				break;
			default:
				break;
			}
		}
	}

	private void oneCase() {
		liner();
		fileSys.inputFile(); 
		fileSys.checkFile();
		view();
	}
	private void twoCase() {
		liner();
		compiler.setFile(fileSys.FileDirSeperator(fileSys.getPath()));
		isCompiled = compiler.compiler();
		view();
	}
	private void threeCase() {
		liner();
		runner.setFile(fileSys.FileDirSeperator(fileSys.getPath()));
		runner.run(isCompiled);
		view();
	}
	private void fourCase() {
		liner();
		isCompiled = fileSys.deleteFile();
		view();
	}
	private void fiveCase() {
		liner();
		error.setFile(fileSys.FileDirSeperator(fileSys.getPath()));
		error.errorPrint(isCompiled);
		view();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.view();
		main.select();
	}
}
