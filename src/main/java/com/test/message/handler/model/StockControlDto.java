package com.test.message.handler.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CTRL_SEG")
@XmlAccessorType(XmlAccessType.FIELD)
public class StockControlDto implements Serializable {

	private static final long serialVersionUID = 2945047692134789342L;
	@XmlElement(name = "TRNNAM")
	private String trnam;
	@XmlElement(name = "TRNVER")
	private Long trnver;
	@XmlElement(name = "UUID")
	private String uuid;
	@XmlElement(name = "WH_ID")
	private String whId;
	@XmlElement(name = "CLIENT_ID")
	private String clientId;
	@XmlElement(name = "ISO_2_CTRY_NAME")
	private String ctryName;
	@XmlElement(name = "REQUEST_ID")
	private String requestId;
	@XmlElement(name = "ROUTE_ID")
	private Long routeId;

	public String getTrnam() {
		return trnam;
	}

	public void setTrnam(String trnam) {
		this.trnam = trnam;
	}

	public Long getTrnver() {
		return trnver;
	}

	public void setTrnver(Long trnver) {
		this.trnver = trnver;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWhId() {
		return whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getCtryName() {
		return ctryName;
	}

	public void setCtryName(String ctryName) {
		this.ctryName = ctryName;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

}
