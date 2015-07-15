package com.tobysamples.demo.wink;


import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Map;
import lotus.domino.Session;
import com.ibm.domino.napi.c.NotesUtil;
import com.ibm.domino.napi.c.xsp.XSPNative;

public class SudoUtils {
	public interface SudoCallback {
		public Object run(Session session);

		public Object onException(Exception e);
	}


	public static Object runOnBehalfOf(String userName, SudoCallback callback) {
		Object result = null;
		try {
			Session s = getSessionAs(userName);
			result = callback.run(s);
		} catch (Exception e) {
			result = callback.onException(e);
		} finally {
		}
		return result;
	}

public static Session getSessionAs(final String userName) {
	Session result = null;
	try {
		result = AccessController.doPrivileged(new PrivilegedExceptionAction<lotus.domino.Session>() {
			public lotus.domino.Session run() throws Exception {
				long hList = NotesUtil.createUserNameList(userName);
				return XSPNative.createXPageSession(userName, hList, true, false);
			}
		});
	} catch (Exception e) {

	}
	return result;
}
}