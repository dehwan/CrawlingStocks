import java.io.IOException;
import java.text.ParseException;

import org.apache.ibatis.session.SqlSession;

public class Crawling {
	
	public static void main(String args[]){
		CrawlingForFootSell fs = new CrawlingForFootSell();
		
		try {
//			fs.parsing();
			
			DataHandler dh = new DataHandler();
			SqlSession session = dh.getSession();
			System.out.println(session.getConnection().getSchema());
			session.insert("com.nodot.sql.mapper.mapper.insertTest");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
