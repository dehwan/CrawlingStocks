import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class Crawling {
	
	public static void main(String args[]){
		
		try {
			
			List<Map<String,Object>> stocks = new CrawlingForFootSell().parsing();
			
			SqlSession session = new DataHandler().getSession();
			
			session.insert("StocksMapper.setMarketData",stocks);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
