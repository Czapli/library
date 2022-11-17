package org.czaplinski.library.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ServiceAccountConfig {
    @Value("${serviceAccount.mail}")
    private String ServiceMail;
}
