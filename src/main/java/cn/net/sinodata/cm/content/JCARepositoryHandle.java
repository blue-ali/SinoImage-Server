package cn.net.sinodata.cm.content;

import javax.jcr.LoginException;
import javax.jcr.NoSuchWorkspaceException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ManagedConnectionFactory;

import org.apache.jackrabbit.jca.JCAConnectionRequestInfo;

public class JCARepositoryHandle {
	
	private ManagedConnectionFactory mcf;
	private ConnectionManager cm;
	
	public JCARepositoryHandle(ManagedConnectionFactory mcf,
			ConnectionManager cm) {
		this.mcf = mcf;
		this.cm = cm;
	}

	private Session login(JCAConnectionRequestInfo cri)  
	        throws LoginException, NoSuchWorkspaceException, RepositoryException {  
	    try {  
	        return (Session) cm.allocateConnection(mcf, cri);  
	    } catch (ResourceException e) {  
	    	e.printStackTrace();
	    }
		return null;  
	} 
}
