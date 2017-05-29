import java.io.IOException;
import java.text.ParseException;

public class Crawling {
	
	public static void main(String args[]){
		CrawlingForFootSell fs = new CrawlingForFootSell();
		
		try {
			fs.parsing();
			
//			DataHandler dh = new DataHandler();
//			System.out.println(dh.isConnected());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
