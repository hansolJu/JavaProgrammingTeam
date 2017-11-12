package apps.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 
 * fileName : �뙆�씪�쓽 �씠由�
 * filePath : �뙆�씪�쓽 �젅�� 寃쎈줈
 * @author isk03
 * �뙆�씪�쓽 �씠由꾩쓣 諛쏆븘 �떎�뻾�븯�뒗 �겢�옒�뒪
 */
public class Runner {
	private String fileName;
	private String filePath;
	/**
	 * 
	 * �빐�떦 �뙆�씪�쓣 �떎�뻾(run)
	 */
	public synchronized void run(boolean isCompiled) {
		if(!checkCompiled(isCompiled))  //�옒 而댄뙆�씪 �릱�뒗吏� 寃��궗
			return;
		try {
			String outLine, errLine;
			
			Process oProcess = new ProcessBuilder("java", "-cp", filePath, fileName.split(".java")[0]).start();  //cmd 紐낅졊�뼱 ex)java -cp C:\temp\ test
			
			BufferedReader resultOut = new BufferedReader(new InputStreamReader(oProcess.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(oProcess.getErrorStream()));
			
			while ((errLine = stdError.readLine()) != null) System.err.println(errLine);
			while((outLine = resultOut.readLine()) != null)	System.out.println(outLine);
		}
		catch(IOException e) {
			System.err.println("�옄諛� �뙆�씪�쓣 �떎�뻾�븯吏� 紐삵뻽�뒿�땲�떎.\n" + e.getMessage());
		}
	}
	/**
	 * 
	 * @param FileDirArray index0:寃쎈줈, index1:�씠由�(~~~.java)
	 * �뙆�씪�씠由꾧낵 寃쎈줈瑜� �꽕�젙
	 */
	public void setFile(String[] FileDirArray) {
		filePath = FileDirArray[0];
		fileName = FileDirArray[1];
	}
	/**
	 * 
	 * @param isCompiled:而댄뙆�씪 �삤瑜섍� 諛쒖깮�븳 寃쎌슦 �삤瑜섏쿂由щ�� �쐞�븳 蹂��닔
	 * @return �뙆�씪�씠 �옒 而댄뙆�씪�릱�뿀�떎硫� true
	 * �뙆�씪�씠 �옒 而댄뙆�씪�릱�뒗吏� �솗�씤 
	 */
	private boolean checkCompiled(boolean isCompiled) {
		if(!isCompiled) {  //而댄뙆�씪 �삤瑜섍� 諛쒖깮�븳 �긽�깭�뿉�꽌 run 硫붾돱 諛쒖깮 �떆, boolean媛� 諛섑솚
			System.out.println("");
			System.err.println("而댄뙆�씪 �삤瑜�");
			return false;
		}
		File file = new File(filePath, fileName);
		if(!file.exists()) {
			System.err.println("\t�뙆�씪�씠 議댁옱�븯吏� �븡�뒿�땲�떎..");
			return false;
		}
		return true;
	}
}