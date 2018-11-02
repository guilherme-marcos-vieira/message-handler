package com.test.message.handler.parse;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.test.message.handler.model.RequestRouteDto;
import com.test.message.handler.model.StockControlDto;
import com.test.message.handler.model.StockLevelDto;

public class StockLevelDtoParserTests {

	private static final long ROUTE_ID = 186L;
	private static final String REQUEST_ID = "bc2a55e8-5a07-4af6-85fd-8290d3ccfb51";
	private static final RequestRouteDto EXPECTED_REQUEST_ROUTE_DTO = new RequestRouteDto(REQUEST_ID, ROUTE_ID);
	private static final StockLevelDto STOCK_LEVEL_DTO = new StockLevelDto(new StockControlDto(REQUEST_ID, ROUTE_ID));

	@Test
	public void test() {
		RequestRouteDto requestRouteDto = StockLevelDtoParser.parseRequestRouteDto(STOCK_LEVEL_DTO);
		assertEquals(EXPECTED_REQUEST_ROUTE_DTO, requestRouteDto);
	}

}
