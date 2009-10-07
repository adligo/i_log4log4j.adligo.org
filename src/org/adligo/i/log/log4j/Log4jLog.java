package org.adligo.i.log.log4j;

import org.adligo.i.log.client.I_LogDelegate;
import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.SimpleLog;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jLog implements Log, I_LogDelegate {
	private boolean enabled = true;
	private Logger logger;
	
	public Log4jLog(String logName) {
		logger = Logger.getLogger(logName);
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public boolean isErrorEnabled() {
		if (enabled) {
			return logger.isEnabledFor(Level.ERROR);
		}
		return false;
	}

	@Override
	public boolean isFatalEnabled() {
		if (enabled) {
			return logger.isEnabledFor(Level.FATAL);
		}
		return false;
	}

	@Override
	public boolean isWarnEnabled() {
		if (enabled) {
			return logger.isEnabledFor(Level.WARN);
		}
		return false;
	}

    /**
     * @Override
     */
    public boolean isDebugEnabled() {
    	if (enabled) {
    		return logger.isEnabledFor(Level.DEBUG);
    	}
    	return false;
    }


    /**
     * @Override
     */
    public boolean isInfoEnabled() {
    	if (enabled) {
    		return logger.isEnabledFor(Level.INFO);
    	}
    	return false;
    }


    /**
     * @Override
     */
    public boolean isTraceEnabled() {
    	if (enabled) {
    		return logger.isEnabledFor(Level.TRACE);
    	}
    	return false;
    }
    
	@Override
	public void setEnabled(boolean p) {
		enabled = p;
	}

	@Override
	public void log(short type, Object message, Throwable t) {
		if (enabled) {
			switch (type) {
				case SimpleLog.LOG_LEVEL_TRACE:
						if (message != null) {
							if (t != null) {
								trace(message, t);
							} else {
								trace(message);
							}
						}
					break;
				case SimpleLog.LOG_LEVEL_DEBUG:
					if (message != null) {
						if (t != null) {
							debug(message, t);
						} else {
							debug(message);
						}
					}
					break;
				case SimpleLog.LOG_LEVEL_INFO:
					if (message != null) {
						if (t != null) {
							info(message, t);
						} else {
							info(message);
						}
					}
					break;
				case SimpleLog.LOG_LEVEL_WARN:
					if (message != null) {
						if (t != null) {
							warn(message, t);
						} else {
							warn(message);
						}
					}
					break;
				case SimpleLog.LOG_LEVEL_ERROR:
					if (message != null) {
						if (t != null) {
							error(message, t);
						} else {
							error(message);
						}
					}
					break;
				case SimpleLog.LOG_LEVEL_FATAL:
					if (message != null) {
						if (t != null) {
							fatal(message, t);
						} else {
							fatal(message);
						}
					}
					break;
			}
		}
	}

	@Override
	public void debug(Object message) {
		logger.debug(message);
	}

	@Override
	public void debug(Object message, Throwable t) {
		logger.debug(message, t);
	}

	@Override
	public void error(Object message) {
		logger.error(message);
	}

	@Override
	public void error(Object message, Throwable t) {
		logger.error(message, t);
	}

	@Override
	public void fatal(Object message) {
		logger.fatal(message);
	}

	@Override
	public void fatal(Object message, Throwable t) {
		logger.fatal(message, t);
	}

	@Override
	public void info(Object message) {
		logger.info(message);
	}

	@Override
	public void info(Object message, Throwable t) {
		logger.info(message, t);
	}

	@Override
	public void trace(Object message) {
		logger.trace(message);
	}

	@Override
	public void trace(Object message, Throwable t) {
		logger.trace(message, t);
	}

	@Override
	public void warn(Object message) {
		logger.warn(message);
	}

	@Override
	public void warn(Object message, Throwable t) {
		logger.warn(message, t);
	}
}
