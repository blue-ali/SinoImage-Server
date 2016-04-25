package jcr;

import java.io.InputStream;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.core.RepositoryImpl;
import org.apache.jackrabbit.core.config.ConfigurationException;
import org.apache.jackrabbit.core.config.RepositoryConfig;

public class Test {

	public static void main(String[] args) {
		InputStream is = Test.class.getClassLoader().getResourceAsStream("resources/repository/repository-test.xml");
		
		try {
			Repository repository = RepositoryImpl.create(RepositoryConfig.create(is, "f:\\jcrworkspace"));
			Session session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
//			session.getRootNode().addNode("TEST999");
			System.out.println(session.nodeExists("/TEST999"));
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			Thread.sleep(99999999);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
