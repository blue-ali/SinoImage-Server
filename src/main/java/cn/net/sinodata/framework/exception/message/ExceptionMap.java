package cn.net.sinodata.framework.exception.message;

import java.util.HashMap;
import java.util.Map;

public class ExceptionMap {
	
	private static Map<Integer, ExceptionInfo> map = null;

	private static final ExceptionMap exceptionMap = new ExceptionMap();

	private ExceptionMap() {
		map = new HashMap<Integer, ExceptionInfo>();
		Object[][] arrayIds = ExceptionIDs.exceptionIds;
		int errorCode = 0;
		for (int i = 0; i < arrayIds.length; i++) {
			errorCode = (Integer) arrayIds[i][0];
			map.put(errorCode, new ExceptionInfo(errorCode, (String)arrayIds[i][1], (String)arrayIds[i][2]));
		}
	}

	public static ExceptionMap getInstance() {
		return exceptionMap;
	}
	
	public Map<Integer, ExceptionInfo> getMap(){
		return map;
	}

}
