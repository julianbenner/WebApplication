package main;

import java.io.Serializable;

public class Status implements Serializable {
	private String status;
	private StatusType statusType;

	public Status() {
	}

	public StatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
