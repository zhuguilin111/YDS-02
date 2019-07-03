package com.yds.websocket;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

/**websocket*/
@Component
@ServerEndpoint("/websocket")
public class WebSocket {
	
	private Session  session;
	//定义websocket容器，存储session
	private static CopyOnWriteArraySet<WebSocket> websocket = new CopyOnWriteArraySet<>();
	
	//建立连接
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		websocket.add(this);
	}
	
	//关闭连接
	@OnClose
	public void onClose() {
		websocket.remove(this);
	}
	
	//接收到消息
	@OnMessage
	public void onMessage(String msg) {
		
	}
	
	public void sendMessage(String message) {
		for (WebSocket socket : websocket) {
			try {
				socket.session.getBasicRemote().sendText(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
			

}
