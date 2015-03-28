package at.wtfp.cbttl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CBTTLDispatcher {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CBTTLDispatcher(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public void route() {
		
	}

}
