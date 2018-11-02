package com.test.message.handler.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.test.message.handler.model.RequestRouteDto;

public class JaxbUtilsTests {

	private static final RequestRouteDto EXPECTED_REQUEST_ROUTE_DTO = new RequestRouteDto(
			"bc2a55e8-5a07-4af6-85fd-8290d3ccfb51", 186L);

	@Test
	public void shouldMarshalAndUnmarshalSameObject() throws Exception {
		String xml = JaxbUtils.marshal(EXPECTED_REQUEST_ROUTE_DTO);
		RequestRouteDto requestRouteDto = JaxbUtils.unmarshal(xml, RequestRouteDto.class);
		assertEquals(EXPECTED_REQUEST_ROUTE_DTO, requestRouteDto);
	}

}
