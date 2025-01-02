package org.asupranovich.chat.gateway;

import jakarta.ws.rs.HttpMethod;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("client_messages_route",
                route -> route.path("/client/messages/**")
                    .and()
                    .method(HttpMethod.POST)
                    .filters(filter -> filter.stripPrefix(1))
                    .uri("lb://client/messages")).build();
    }

}
