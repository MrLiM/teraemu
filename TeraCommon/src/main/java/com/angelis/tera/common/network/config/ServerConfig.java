package com.angelis.tera.common.network.config;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import com.angelis.tera.common.network.connection.AbstractTeraConnection;
import com.angelis.tera.common.network.factory.AbstractConnectionFactory;


public final class ServerConfig {

	private final String address;
	private final int port;
	private AbstractConnectionFactory<? extends AbstractTeraConnection> connectionFactory;
	private final int readWriteProcessorCount;
	
	public ServerConfig(String address, int port, AbstractConnectionFactory<? extends AbstractTeraConnection> connectionFactory, int readWriteProcessorCount) {
		this.address = address;
		this.port = port;
		this.connectionFactory = connectionFactory;
		this.readWriteProcessorCount = readWriteProcessorCount;
	}

	public final int getPort() {
		return port;
	}

	public final String getAddress() {
		return this.address;
	}
	
	public final AbstractConnectionFactory<? extends AbstractTeraConnection> getConnectionFactory() {
		return this.connectionFactory;
	}
	
	public int getReadWriteProcessorCount() {
	    return this.readWriteProcessorCount;
	}

	public final SocketAddress getInetSocketAddress() {
		SocketAddress inetSocketAddress = null;
		if ("*".equals(this.address)) {
			inetSocketAddress = new InetSocketAddress(this.port);
		} else {
			inetSocketAddress = new InetSocketAddress(this.address, this.port);
		}
		return inetSocketAddress ;
	}
}
