package com.tobysamples.demo.wink;

import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.wink.common.internal.runtime.RuntimeDelegateImpl;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin {

	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Starting Wink Service...");
		// Ensure that the service loader uses the right class
		// See: http://www.mail-archive.com/discuss@restlet.tigris.org/msg07539.html
		ClassLoader oldcl = Thread.currentThread().getContextClassLoader();
		ClassLoader newcl = RuntimeDelegate.class.getClassLoader();
		
		Thread.currentThread().setContextClassLoader(newcl);
		try {
			RuntimeDelegate.setInstance(new RuntimeDelegateImpl());
		} finally {
			Thread.currentThread().setContextClassLoader(oldcl);
		}
		super.start(context);
	}
	
}
