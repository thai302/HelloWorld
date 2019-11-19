package com.ecorau.vbnapi.core.config;

import com.ecorau.vbnapi.core.bean.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditorConfig {
    @Autowired
    UserSession userSession;

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

    public class AuditorAwareImpl implements AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
//            if (userSession != null)
//                return Optional.of(userSession.getUserName());
//            else
//                return Optional.of("anonymous");
            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            return Optional.of(userName);
        }
    }
}
