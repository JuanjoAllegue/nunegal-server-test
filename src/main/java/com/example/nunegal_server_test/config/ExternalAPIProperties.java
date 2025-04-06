package com.example.nunegal_server_test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "external-api", ignoreUnknownFields = false)
public class ExternalAPIProperties {
    private String url;

    public ExternalAPIProperties() {}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
