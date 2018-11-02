package com.test.message.handler.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "REQUEST_ROUTE")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestRouteDto implements Serializable {

	private static final long serialVersionUID = -7194858541768360566L;
	@XmlElement(name = "REQUEST_ID")
	private String requestId;
	@XmlElement(name = "ROUTE_ID")
	private Long routeId;

	public RequestRouteDto() {
	}

	public RequestRouteDto(String requestId, Long routeId) {
		this.requestId = requestId;
		this.routeId = routeId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result + ((routeId == null) ? 0 : routeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestRouteDto other = (RequestRouteDto) obj;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (routeId == null) {
			if (other.routeId != null)
				return false;
		} else if (!routeId.equals(other.routeId))
			return false;
		return true;
	}
}
