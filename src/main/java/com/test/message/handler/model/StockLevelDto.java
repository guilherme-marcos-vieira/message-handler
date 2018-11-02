package com.test.message.handler.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UC_STOCK_LEVEL_IFD")
@XmlAccessorType(XmlAccessType.FIELD)
public class StockLevelDto implements Serializable {

	private static final long serialVersionUID = -2832362393315637408L;
	@XmlElement(name = "CTRL_SEG")
	private StockControlDto stockControlDto;

	public StockLevelDto() {
	}

	public StockLevelDto(StockControlDto stockControlDto) {
		this.stockControlDto = stockControlDto;
	}

	public StockControlDto getStockControlDto() {
		return stockControlDto;
	}

	public void setStockControlDto(StockControlDto stockControlDto) {
		this.stockControlDto = stockControlDto;
	}
}
