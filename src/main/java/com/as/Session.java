package com.as;

import com.as.model.Person;
import com.webobjects.foundation.NSLog;

import er.corebusinesslogic.ERCoreBusinessLogic;
import er.extensions.appserver.ERXSession;
import er.extensions.foundation.ERXThreadStorage;

public class Session extends ERXSession {
	
	private static final long serialVersionUID = 1L;

	private MainNavigationController _navController;
	private Person _user;

	public Session() {
	}
	
	public void awake() {
        super.awake();

        NSLog.out.appendln("***the session user: " + user());
        
        ERXThreadStorage.takeValueForKey(user(), "currentUser");
  
		if (user() != null) {
			ERCoreBusinessLogic.setActor(user());
		}
     }
	
	public MainNavigationController navController() {
		if (_navController == null) {
			_navController = new MainNavigationController(this);
		}
		return _navController;
	}
	
	public Person user() {		
		return _user;
	}
	
	public void setUser(Person user) {
				
		_user = user;
		ERCoreBusinessLogic.setActor(user());
		
		/*
		 * set the default logo here and push it into the ThreadStorage in the awake
		 */
		//NSLog.out.appendln("***the session theDefaultLogo: " + theDefaultLogo);
	}
}
