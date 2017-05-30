import org.apache.ibatis.annotations.Insert;

public interface StockMappere {
	
	@Insert("insert into MARKET_SHOES(MS_ID, MS_URL, MS_NAME, MS_PRICE, MS_USER_NAME, MS_REG_DATE) values ('1','1','1',111,'111','2016-12-12 12:12:12') ")
	void insertTest();

}
