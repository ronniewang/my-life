package wang.ronnie.global;

import org.apache.commons.logging.LogFactory;
import wang.ronnie.utils.DateUtil;


public class Log {
	private static org.apache.commons.logging.Log log
			= LogFactory.getLog(Log.class);
	
	private static String genOutputStr(String msg) {
		StackTraceElement[] ele = Thread.currentThread().getStackTrace();
		
		StringBuilder sb = new StringBuilder();
		
        //level
        sb.append("[").append(ele[2].getMethodName()).append("]");

		//时间
        sb.append("[").append(DateUtil.format(System.currentTimeMillis()
                , "yyyy-MM-dd HH:mm:ss S")).append("]");
        
		//内容
		sb.append("[").append(msg).append("]");
		
		sb.append("[").append(ele[3].getClassName()).append(".")
			.append(ele[3].getMethodName()).append(":")
			.append(ele[3].getLineNumber()).append("]");
		

		//request id
//        sb.append("[requestId:").append(SpringMVCUtil.getRequestId()).append("]");
        
        return sb.toString();
	}
	
	public static void statistic(String msg) {
		log.info(genOutputStr(msg));
	}
	
	public static void fatal(String msg) {
		log.fatal(genOutputStr(msg));
	}
	
	public static void warn(String msg) {
		log.warn(genOutputStr(msg));
	}
	
	public static void info(String msg) {
		log.info(genOutputStr(msg));
	}
	
	public static void ex (Throwable t) {
		log.warn(genOutputStr(t.getMessage()));
		StackTraceElement[] ste = t.getStackTrace();
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement ele : ste) {
		    sb.append(ele.toString());
		}
		log.warn(sb.toString());
	}
	
	public static void main(String[] args) {
		Log.fatal("test");
	}
}
