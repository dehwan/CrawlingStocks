import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class DataHandler {

	private SqlSession session;
	
	public String isConnected() throws SQLException{
		return session.getConnection().getSchema();
	}
	
	public SqlSession getSession(){
		try {
			session = new SessionFactory().getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}

}
