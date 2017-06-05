import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class Crawling {
	
	public static void main(String args[]){
		
		SqlSession session = null;
		
		try {
			session = new DataHandler().getSession();
			List<Map<String,Object>> stocks = new CrawlingForFootSell().parsing();
			session.insert("StocksMapper.setMarketData",stocks);
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
}
