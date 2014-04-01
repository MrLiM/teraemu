package com.angelis.tera.common.network.processors;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.angelis.tera.common.network.AbstractServer;
import com.angelis.tera.common.network.connection.AbstractTeraConnection;
import com.angelis.tera.common.network.factory.AbstractConnectionFactory;

public class AcceptProcessor extends Processor {

	private static Logger log = Logger.getLogger(AcceptProcessor.class.getName());
	
	public AcceptProcessor(AbstractServer abstractServer, AbstractConnectionFactory<? extends AbstractTeraConnection> connectionFactory) {
		super(abstractServer);
		try {
			this.abstractServer.getServerSocketChannel().register(this.selector, SelectionKey.OP_ACCEPT, connectionFactory);
			this.selector.wakeup();
		} catch (ClosedChannelException e) {
			throw new RuntimeException("Can't initialize AcceptProcessor");
		}
	}

	public void dispatch() {
		try {
		    int selection = this.selector.select();
			if (selection == 0) {
				return;
			}
			
			Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
			while(iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				iterator.remove();
				
				if (!selectionKey.isValid()) {
					log.info("Key isn't valid for accept");
					continue;
				}
				
				switch (selectionKey.readyOps()) {
					case SelectionKey.OP_ACCEPT:
					    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
					    SocketChannel socketChannel = serverSocketChannel.accept();
				        socketChannel.configureBlocking(false);
				        
						@SuppressWarnings("unchecked")
						AbstractConnectionFactory<AbstractTeraConnection> factory = (AbstractConnectionFactory<AbstractTeraConnection>) selectionKey.attachment();

						AbstractTeraConnection connection = factory.create(socketChannel, selectionKey);
						connection.attachReadWriteProcessor(this.abstractServer.getReadWriteProcessor());

						connection.onConnect();
					break;
				}
			}
		} catch (IOException e) {
		    log.error(e.getMessage(), e);
		}
	}
}
