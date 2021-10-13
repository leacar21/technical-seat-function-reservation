package com.leacar21.technical.seat.function.reservation.clients.rest;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ShowCatalogClientConfig {

    public static final String BEAN_NAME = "showCatalogRestClient";

    @Value("${service.shows.catalog.connect.timeout}")
    private Integer connectTimeout;

    @Value("${service.shows.catalog.connection.request.timeout}")
    private Integer connectionRequestTimeout;

    @Value("${service.shows.catalog.socket.timeout}")
    private Integer socketTimeout;

    @Bean(name = BEAN_NAME)
    public RestTemplate getClient() {
        return new RestTemplate(getClientHttpRequestFactory());
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        RequestConfig config = RequestConfig.custom() //
                .setConnectTimeout(3000) //
                .setConnectionRequestTimeout(3000) //
                .setSocketTimeout(5000) //
                .build();
        CloseableHttpClient client = HttpClientBuilder //
                .create() //
                .setDefaultRequestConfig(config) //
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }
}
