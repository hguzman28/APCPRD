package com.jamar.apc.request.handler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.MessageContext.Scope;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class AuditoryHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		
		if (!(Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
			Map<String, List<String>> map = (Map<String, List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS);
			List<String> usuarios = getHTTPHeader(map, "usuario");
			List<String> origenes = getHTTPHeader(map, "origen");
			if (usuarios != null) {
				StringBuffer strBuf = new StringBuffer();
				for (String type : usuarios) {
					strBuf.append(type);
				}
				context.put("usuario", strBuf.toString());
				context.setScope("usuario", Scope.APPLICATION);
			}
			if (origenes != null) {
				StringBuffer strBuf = new StringBuffer();
				for (String type : origenes) {
					strBuf.append(type);
				}
				context.put("origen", strBuf.toString());
				context.setScope("origen", Scope.APPLICATION);
			}
		}else{
//			System.out.println(context.getMessage().toString());
		}

		return true;
	}

	private List<String> getHTTPHeader(Map<String, List<String>> headers, String header) {
		for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
			String name = entry.getKey();
			if (name.equalsIgnoreCase(header))
				return entry.getValue();
		}
		return null;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	@Override
	public void close(MessageContext context) {
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

}
