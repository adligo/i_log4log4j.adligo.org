package org.adligo.i.log.log4j;

import java.util.HashMap;
import java.util.Map;

import org.adligo.i.log.client.I_LogDelegate;
import org.adligo.i.log.client.I_LogFactory;
import org.adligo.i.log.client.I_ProxyLog;
import org.adligo.i.log.client.models.I_LogMessage;
import org.adligo.i.util.client.ClassUtils;
import org.adligo.i.util.client.I_Collection;
import org.adligo.i.util.client.I_Iterator;
import org.adligo.i.util.client.I_Map;

public class Log4jFactory implements I_LogFactory {
	private static final Map<String, I_LogDelegate> logs = new HashMap<String, I_LogDelegate>();
	public static final Log4jFactory INSTANCE = new Log4jFactory();
	
	private Log4jFactory() {}
	
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
	public void sendPreInitMessages(I_Collection iLogMessages) {
		I_Iterator it = iLogMessages.getIterator();
		while (it.hasNext()) {
			I_LogMessage ilog = (I_LogMessage) it.next();
			if (ilog != null) {
				String name = ilog.getName();
				I_LogDelegate logDelegate = this.getLog(name);
				logDelegate.log(ilog.getLevel(), 
						ilog.getMessage(),
						ilog.getThrowable());
			}
		}
	}

	@Override
	public synchronized void setInitalLogLevels(I_Collection iProxyLogs) {
		//do nothing this expects to be staticly init 
		/*
		I_Iterator it = iProxyLogs.getIterator();
		while (it.hasNext()) {
			I_ProxyLog proxyLog = (I_ProxyLog) it.next();
			proxyLog.addDelegate(new Log4jLog(proxyLog.getLogName()));
		}
		*/
	}

	@Override
	public void setInitalLogLevels(I_Map props, I_LogFactory p) {
		//do nothing this is for async log config file responses like adligo_log.properties
	}

	@Override
	public boolean isStaticInit() {
		return true;
	}
}
