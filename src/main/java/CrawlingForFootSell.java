import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlingForFootSell {
	
	private final String FS_URL = "https://footsell.com/g2/bbs/board.php?bo_table=m51";
	
	private List<Map<String, Object>> stocks;
	
	private SimpleDateFormat dateParse;
	
	public CrawlingForFootSell() {
		stocks = new ArrayList<Map<String, Object>>();
		dateParse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.KOREA);
	}
	
	public List<Map<String, Object>> parsing() throws IOException, ParseException	{
		Document doc = Jsoup.connect(FS_URL).get();
		Elements els = doc.getElementById("list_table").getElementsByClass("list_table_row relative  ");
		
		//div id를 게시물의 키값으로 한다.
		for(Element el : els){
			el.select("span.list_comment_num_a").remove();
			Map<String, Object> shoes = new HashMap<String, Object>();
			
			String id = el.id().split("_")[2];
			String name = el.select("a.list_subject_a > span").text();
			
			Elements priceSize = el.select("div.list_market_etc > div.left.list_market_price");
			priceSize.select("span.list_market_size_mm").remove();
			int price = Integer.parseInt(priceSize.get(0).text().replaceAll(",", ""));
			String size = priceSize.get(1).text().replaceAll("\\p{Z}", "");
			
//			int price = 100000;
//			String size = "265";
			
			String user = el.select("span.member").text();
			String[] timehhmm = el.select("span.list_table_dates").text().split(":");
			int hh = Integer.parseInt(timehhmm[0]);
			int mm = Integer.parseInt(timehhmm[1]);
			String url = el.select("a.list_subject_a").attr("href");
			String statusText = el.select("div.left.list_market_size").text().replaceAll("\\p{Z}", "");
			int status = 1;
			
			if(statusText.equals("새제품")) status = 1;
			else status = 0;
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, hh);
			cal.set(Calendar.MINUTE, mm);
			cal.set(Calendar.SECOND, 00);
			String regDate = dateParse.format(cal.getTime());
					
			shoes.put("id", id);
			shoes.put("url", url);
			shoes.put("name", name);
			shoes.put("price", price);
			shoes.put("size", size);
			shoes.put("status", status);
			shoes.put("user", user);
			shoes.put("regDate", regDate);
			
			stocks.add(shoes);
				
		}
		return stocks;

		
	}
}
