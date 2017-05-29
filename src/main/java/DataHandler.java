import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import settings.SessionFactory;

public class DataHandler {

	private SqlSession session;
	
	public DataHandler() throws IOException {
		session = new SessionFactory().getSession();
	}
	
	public String isConnected() throws SQLException{
		return session.getConnection().getSchema();
	}
	
	public void insertMarketData(Map<String, Object> shoes){
		
	}

}
