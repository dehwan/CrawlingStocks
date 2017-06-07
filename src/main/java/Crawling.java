import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class Crawling {
	
	public static void main(String args[]){
		
		SqlSession session = new DataHandler().getSession();
		try {
			List<Map<String,Object>> stocks = new CrawlingForFootSell().parsing();
			
			stocks.stream().forEach( shoes ->{
				session.insert("StocksMapper.setMarketData", shoes);
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
