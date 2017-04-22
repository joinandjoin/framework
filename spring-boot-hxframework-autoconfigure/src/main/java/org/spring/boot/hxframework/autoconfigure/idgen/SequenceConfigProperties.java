package org.spring.boot.hxframework.autoconfigure.idgen;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application.idgen")
public class SequenceConfigProperties {
	
	private int datacenter;
	
	private int worker;

	public int getDatacenter() {
		return datacenter;
	}

	public void setDatacenter(int datacenter) {
		this.datacenter = datacenter;
	}

	public int getWorker() {
		return worker;
	}

	public void setWorker(int worker) {
		this.worker = worker;
	}
	
}
