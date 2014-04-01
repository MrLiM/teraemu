package com.angelis.tera.game.network.factory;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import com.angelis.tera.common.network.factory.AbstractConnectionFactory;
import com.angelis.tera.game.network.connection.TeraGameConnection;

public class TeraGameAcceptFactory extends AbstractConnectionFactory<TeraGameConnection> {

	@Override
	public TeraGameConnection create(SocketChannel socketChannel, SelectionKey selectionKey) {
		TeraGameConnection connection = new TeraGameConnection(socketChannel, selectionKey);
		return connection;
	}
}
