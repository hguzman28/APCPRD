package com.jamar.apc.util;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


import generated.Solicitud;
import generated.SolicitudType;

public class StringToJavaAdapter {

	public static String getInformeTypeFromString(String informes2) throws JAXBException {
//		Informes informes = null;
			File file = new File("C:\\informes.xml");

			JAXBContext jaxbContext = JAXBContext.newInstance(String.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//			informes = (Informes) jaxbUnmarshaller.unmarshal(new StringReader(informes2));
		return null;

	}

	public static String jaxbObjectToXML(Solicitud customer) throws JAXBException {
	    String xmlString = "";

	        JAXBContext context = JAXBContext.newInstance(Solicitud.class);
	        Marshaller m = context.createMarshaller();

	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

	        StringWriter sw = new StringWriter();
	        m.marshal(customer, sw);
	        xmlString = sw.toString();

	   

	    return xmlString;
	}

}
