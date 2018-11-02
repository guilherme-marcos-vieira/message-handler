package com.test.message.handler.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class JaxbUtils {

	private JaxbUtils() {
	}

	public static <T> T unmarshal(String xml, Class<T> type) throws Exception {
		JAXBContext context = JAXBContext.newInstance(type);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return type.cast(unmarshaller.unmarshal(new StringReader(xml)));
	}
	
	public static String marshal(Object object) throws Exception {
		StringWriter writer = new StringWriter();
		JAXB.marshal(object, writer);
		return writer.toString();	
	}
	
}
