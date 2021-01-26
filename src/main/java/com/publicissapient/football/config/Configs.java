package com.publicissapient.football.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;

/**
 * @author mukutbhattacharjee
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class Configs {
    private static final String API_KEY = "APIkey";

    @Autowired
    AppProperties properties;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.interceptors(Collections.singleton(interceptor())).build();
    }

    public ClientHttpRequestInterceptor interceptor() {
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                                ClientHttpRequestExecution execution) throws IOException {
                URI uri = UriComponentsBuilder.fromHttpRequest(request)
                        .queryParam(API_KEY, properties.getApiKey())
                        .build().toUri();

                HttpRequest httpRequest = new HttpRequestWrapper(request) {
                    @Override
                    public URI getURI() {
                        return uri;
                    }
                };
                log.info("Call made to {}",request.getURI());
                return execution.execute(httpRequest, body);
            }
        };
    }

}
