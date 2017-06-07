import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CrawlingForFootSell {
	
	private final String FS_URL = "https://footsell.com/g2/bbs/board.php?bo_table=m51&done=1";
	
	private List<Map<String, Object>> stocks;
	
	private SimpleDateFormat dateParse;
	
	public CrawlingForFootSell() {
		stocks = new ArrayList<Map<String, Object>>();
		dateParse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.KOREA);
	}
	
	public List<Map<String, Object>> parsing() throws IOException {
		Document doc = Jsoup.connect(FS_URL).get();
		Elements els = doc.getElementById("list_table").getElementsByClass("list_table_row relative  ");
		
		els.stream().forEach(el->{
			Map<String, Object> shoes = new HashMap<String, Object>();

			el.select("span.list_comment_num_a").remove();
			
			String id = el.id().split("_")[2];
			String name = el.select("a.list_subject_a > span").text();
			
			Elements priceSize = el.select("div.list_market_etc > div.left.list_market_price");
			priceSize.select("span.list_market_size_mm").remove();
			int price = Integer.parseInt(priceSize.get(0).text().replaceAll(",", ""));
			String size = priceSize.get(1).text().replaceAll("\\p{Z}", "");
			
			System.out.println(size);
			
			Pattern pattern = Pattern.compile("^(?:(\\d+)(?:[,. ]|))*$");
			Matcher matcher = pattern.matcher(size);
			
			while(matcher.find()){
				System.out.println(matcher.groupCount());
			}
			
			
			
			String user = el.select("span.member").text();
			String url = el.select("a.list_subject_a").attr("href");
			String statusText = el.select("div.left.list_market_size").text().replaceAll("\\p{Z}", "");
			
			int status;
			if(statusText.equals("새제품")) status = 1;
			else status = 0;
			
			String regDate = null;
			try {
				regDate = this.convertRegDate(el.select("span.list_table_dates").text());
			} catch (ParseException e) {
				e.printStackTrace();
			}
					
			shoes.put("id", id);
			shoes.put("url", url);
			shoes.put("name", name);
			shoes.put("price", price);
			shoes.put("size", size);
			shoes.put("status", status);
			shoes.put("user", user);
			shoes.put("regDate", regDate);
			
			stocks.add(shoes);
		});
		return stocks;
	}
	
	private String convertRegDate(String regDate) throws ParseException{
		if(	regDate.matches("^\\d+:\\d+$")	){
			String[] hhmm = regDate.split(":");
			
			int hh = Integer.parseInt(hhmm[0]);
			int mm = Integer.parseInt(hhmm[1]);
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, hh);
			cal.set(Calendar.MINUTE, mm);
			cal.set(Calendar.SECOND, 00);
			return dateParse.format(cal.getTime());
		} else {
			SimpleDateFormat tempFormat = new SimpleDateFormat("yy-MM-dd");
			return dateParse.format(tempFormat.parse(regDate));
		}
	}
}
