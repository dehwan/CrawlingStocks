
import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionFactory {
	
	private final String MYBATIS_RESOURCE = "com/nodot/sql/xml/mybatis-config.xml";
	
	private SqlSession session;
	private SqlSessionFactory sqlSessionFactory;
	
	public SessionFactory() throws IOException {
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(MYBATIS_RESOURCE));
	}
	
	public SqlSession getSession(){
		session = sqlSessionFactory.openSession();
		return session;
	}
	

}
