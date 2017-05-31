import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class Crawling {
	
	public static void main(String args[]){
		
		try {
			List<Map<String,Object>> stocks = new CrawlingForFootSell().parsing();
			
			DataHandler dh = new DataHandler();
			SqlSession session = dh.getSession();
//			System.out.println(session.getConnection().getSchema());
			
			session.insert("StocksMapper.insertTest",stocks);

//			stocks.stream().forEach(shoes -> {
//					System.out.println(shoes.get("id"));
//					session.insert("StocksMapper.insertTest",  shoes);
//			});
			
//			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
