package co.zhangbiao.sell.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Create By ZhangBiao
 * 2020/3/6
 */
@Component
@ServerEndpoint("/webSocket")
public class WebSocket {

    private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        logger.info("【webSocket消息】有新的连接,总数：{}", webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        logger.info("【webSocket消息】连接断开,总数：{}", webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        logger.info("【webSocket消息】收到客户端发来的消息：{}", message);
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            logger.info("【webSocket消息】广播消息,message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
