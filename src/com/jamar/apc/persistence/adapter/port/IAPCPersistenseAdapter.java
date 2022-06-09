package com.jamar.apc.persistence.adapter.port;

import com.jamar.apc.persistence.entities.Cenclientesconsultar;

public interface IAPCPersistenseAdapter {
	
	public Cenclientesconsultar getConsultaFromServiceResponse(String serviceResponse);
	
}
