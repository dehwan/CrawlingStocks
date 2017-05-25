import java.io.IOException;
import java.text.ParseException;

public class Crawling {
	
	public static void main(String args[]){
		CrawlingForFootSell fs = new CrawlingForFootSell();
		
		try {
			fs.parsing();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
