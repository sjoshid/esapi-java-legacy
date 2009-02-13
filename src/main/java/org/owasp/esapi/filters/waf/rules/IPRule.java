package org.owasp.esapi.filters.waf.rules;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.filters.waf.internal.InterceptingHTTPServletRequest;

public class IPRule extends Rule {

	private Pattern ip;
	private Pattern pathPattern;

	public IPRule(Pattern ip, Pattern pathPattern) {
		this.ip = ip;
		this.pathPattern = pathPattern;
	}

	public boolean check(InterceptingHTTPServletRequest request,
			HttpServletResponse response) {

		if ( pathPattern.matcher(request.getPathInfo()).matches() ) {
			if ( ip.matcher(request.getRemoteAddr()).matches() ) {
				return true;
			}
		}

		return false;
	}

}