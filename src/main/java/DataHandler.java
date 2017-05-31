import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class DataHandler {

	private SqlSession session;
	
	public DataHandler() throws IOException {
		session = new SessionFactory().getSession();
	}
	
	public String isConnected() throws SQLException{
		return session.getConnection().getSchema();
	}
	
	public void insertMarketData(List<Map<String, Object>> stocks){
		
	}
	
	public SqlSession getSession(){
		return session;
	}

}
