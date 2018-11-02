package com.test.message.handler.parse;

import com.test.message.handler.model.RequestRouteDto;
import com.test.message.handler.model.StockLevelDto;

public class StockLevelDtoParser {

	private StockLevelDtoParser() {
	}

	public static RequestRouteDto parseRequestRouteDto(StockLevelDto stockLevelDto) {
		RequestRouteDto requestRouteDto = new RequestRouteDto();
		requestRouteDto.setRequestId(stockLevelDto.getStockControlDto().getRequestId());
		requestRouteDto.setRouteId(stockLevelDto.getStockControlDto().getRouteId());
		return requestRouteDto;
	}
}
