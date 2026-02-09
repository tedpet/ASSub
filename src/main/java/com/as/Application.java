package com.as;

import com.as.components.Main;
import com.webobjects.appserver.WOSession;

import er.extensions.appserver.ERXApplication;
import er.extensions.appserver.navigation.ERXNavigationManager;
import er.extensions.foundation.ERXPatcher;

public class Application extends ERXApplication {
	public static void main(String[] argv) {
		ERXApplication.main(argv, Application.class);
	}

	public Application() {
		ERXApplication.log.info("Welcome to " + name() + " !");
		setDefaultRequestHandler(requestHandlerForKey(directActionRequestHandlerKey()));
		setAllowsConcurrentRequestHandling(true);		
	}
	
    @Override
    public void finishInitialization() {
    	super.finishInitialization();
    	
    	// Setup main navigation
    	ERXNavigationManager.manager().configureNavigation();
    	
    }
    
	/**
	 * Determines the {@link WOSession} class to instantiate.
	 *
	 * @see com.webobjects.appserver.WOApplication#_sessionClass()
	 */

	@Override

	protected Class<? extends WOSession> _sessionClass() {
		return Session.class;

	}


	/**
	 * Installs patches, including ensuring that {@code Main} is correctly resolved at runtime.
	 *
	 * @see er.extensions.appserver.ERXApplication#installPatches()
	 */

	@Override

	public void installPatches() {
		super.installPatches();
		ERXPatcher.setClassForName(Main.class, "Main");

	}
}
