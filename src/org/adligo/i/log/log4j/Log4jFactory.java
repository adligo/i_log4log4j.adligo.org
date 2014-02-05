package org.adligo.i.log.log4j;

import java.util.HashMap;
import java.util.Map;

import org.adligo.i.log.client.I_LogDelegate;
import org.adligo.i.log.client.I_LogFactory;
import org.adligo.i.log.client.ProxyLog;
import org.adligo.i.util.client.ClassUtils;
import org.adligo.i.util.client.I_Collection;
import org.adligo.i.util.client.I_ImmutableMap;
import org.adligo.i.util.client.I_Iterator;
import org.adligo.i.util.client.I_Map;

public class Log4jFactory implements I_LogFactory {
	/**
	 * convention for logFactory=org.adligo.i.log.log4j.Log4jFactory
	 * setting in adligo_log.properties
	 */
	public static final String LOG_FACTORY_NAME = Log4jFactory.class.getName();
	
	private static final Map<String, I_LogDelegate> logs = new HashMap<String, I_LogDelegate>();
	
	public Log4jFactory() {}
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized I_LogDelegate getLog(Class clazz) {
		return getLogInternal(ClassUtils.getClassName(clazz));
	}

	@Override
	public I_LogDelegate getLog(String name) {
		return getLogInternal(name);
	}

	private synchronized I_LogDelegate getLogInternal(String name) {
		I_LogDelegate del = logs.get(name);
		if (del != null) {
			return del;
		}
		
		I_LogDelegate delegate = new Log4jLog(name);
		
		return delegate;
	}

	@Override
	public void resetLogLevels() {
		throw new RuntimeException("Method not yet implemented. ");
	}

	@Override
	public void setInitalLogLevels(I_ImmutableMap props, I_LogFactory p) {
		//do nothing this is for async log config file responses like adligo_log.properties
	}

	@Override
	public boolean isStaticInit() {
		return true;
	}

	@Override
	public void sendPreInitMessages(I_Collection iLogMessages) {
		// TODO Auto-generated method stub
		
	}

	public void setInitalLogLevels(I_Collection iProxyLogs) {
		I_Iterator it = iProxyLogs.getIterator();
		while (it.hasNext()) {
			ProxyLog proxy =  (ProxyLog) it.next();
			Log4jLog log = new Log4jLog(proxy.getLogName());
			proxy.addDelegate(log);
			proxy.setLevel(log.getLevel());
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Log4jFactory.class.getName();
	}
}
