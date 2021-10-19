package com.leacar21.technical.seat.function.reservation.config;

import static org.zalando.logbook.Conditions.exclude;
import static org.zalando.logbook.Conditions.requestTo;

import java.math.BigDecimal;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zalando.logbook.BodyFilter;
import org.zalando.logbook.BodyFilters;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.json.JsonBodyFilters;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BigDecimalDeserializer;
import com.leacar21.technical.seat.function.reservation.config.serializers.BigDecimalSerializer;
import com.leacar21.technical.seat.function.reservation.constants.BeanNames;

@Configuration
@EnableRetry(proxyTargetClass = true)
public class MainConfig implements WebMvcConfigurer {

    @Bean
    public Logbook logbook() {

        // Para esconder el valor de algunos atributos
        var customBodyFilter = BodyFilter.merge(BodyFilters.defaultValue(),
                JsonBodyFilters.replaceJsonStringProperty(Set.of("password", "pass", "secret"), "XXX"));

        // Para excluir algunos path
        return Logbook.builder()
                      .bodyFilter(customBodyFilter)
                      .condition(exclude(requestTo("/health"), requestTo("/admin/**")))
                      .build();
    }

    @Bean(name = BeanNames.STRICT_MODEL_MAPPER)
    public ModelMapper getStrictModelMapper() {
        var modelMapper = new ModelMapper();
        var configuration = modelMapper.getConfiguration();
        configuration.setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        var builder = new Jackson2ObjectMapperBuilder();
        builder.serializerByType(BigDecimal.class, new BigDecimalSerializer());
        builder.deserializerByType(BigDecimal.class, new BigDecimalDeserializer());
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return builder;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
