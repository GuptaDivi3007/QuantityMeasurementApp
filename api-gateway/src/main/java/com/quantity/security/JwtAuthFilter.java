package com.quantity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // PUBLIC APIs (NO TOKEN REQUIRED)
        if (path.startsWith("/api/v1/users/login") ||
            path.startsWith("/api/v1/users/register") ||
            path.contains("/oauth2")) {
            return chain.filter(exchange);
        }

        // AUTH HEADER CHECK
        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst("Authorization");

        // No token → 401 (NOT 500)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse()
                    .setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {
            // invalid token → 401
            if (!jwtUtil.validateToken(token)) {
                exchange.getResponse()
                        .setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        } catch (Exception e) {
            // any exception → 401 (NOT 500)
            exchange.getResponse()
                    .setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // valid → forward request
        return chain.filter(exchange);
    }
}