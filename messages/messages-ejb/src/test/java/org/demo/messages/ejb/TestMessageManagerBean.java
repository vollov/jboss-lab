package org.demo.messages.ejb;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test driver with jndi
 * 
 * @author dtz
 */
public class TestMessageManagerBean {
	
	private final static Hashtable jndiProperties = new Hashtable();
	
	@Before
	public void setup(){
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	}
//	BufferedReader brConsoleReader = null;
//	Properties props;
//	InitialContext ctx;
//
//	{
//		props = new Properties();
//		try {
//			props.load(getInputStream("jndi.properties"));
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//		try {
//			ctx = new InitialContext(props);
//		} catch (NamingException ex) {
//			ex.printStackTrace();
//		}
//		brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
//	}

	
//	private InputStream getInputStream(String fileName) throws IOException{
//		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//		InputStream inputStream = classloader.getResourceAsStream(fileName);
//		return inputStream;
//	}
//	
	@Test
	public void listMessages() {
		try {
			//So what will be the JNDI name used to invoke a stateless session bean? Here is it:
			//ejb:<app-name>/<module-name>/<distinct-name>/<bean-name>!<fullyqualified-
			//classname-of-the-remote-interface>
			//ejb:/message-ejb-0.0.1-SNAPSHOT//MessageManagerBean!org.demo.messages.ejb.MessageManager?stateful
			final Context context = new InitialContext(jndiProperties);
			//java:app/messages-ejb-0.0.1-SNAPSHOT/MessageManagerBean
			final String appName = "";
			final String moduleName = "messages-ejb-0.0.1-SNAPSHOT";
			final String distinctName = "";
			final String beanName = MessageManagerBean.class.getSimpleName();
			final String viewClassName = MessageManager.class.getName();
			
			MessageManager mm = (MessageManager)context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful");
			//MessageManager mm = (MessageManager)context.lookup("ejb:/messages-ejb-0.0.1-SNAPSHOT//MessageManagerBean!org.demo.messages.ejb.MessageManager?stateful");
			mm.findMessages();
			List<Message> ml = mm.getMessageList();
			
			for(Message m : ml){
				System.out.println("title = " + m.getTitle());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
//			try {
//				if (brConsoleReader != null) {
//					brConsoleReader.close();
//				}
//			} catch (IOException ex) {
//				System.out.println(ex.getMessage());
//			}
		}
	}
}
