package com.yds.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**websocket 的配置类*/
@Component
public class WebSocketConfig {

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
	        return new ServerEndpointExporter();
    }
}
