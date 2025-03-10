package in.basha.filter;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class MyFilter implements GlobalFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		ServerHttpRequest request = exchange.getRequest();
		HttpHeaders headers = request.getHeaders();
		Set<String> keySet = headers.keySet();
		
		if(!keySet.contains("Secret")) {
			throw new RuntimeException("Invalid reqest");
		}
		
		List<String> list = headers.get("Secret");
		if(!list.get(0).equals("Basha@123")) {
			throw new RuntimeException("Invalid reqest");
		}
		System.out.println("filter().....executed");
		return chain.filter(exchange);
	}

}
