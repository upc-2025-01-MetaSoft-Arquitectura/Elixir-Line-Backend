package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.infrastructure.hashing.bcrypt;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * This interface is a marker interface for the BCrypt hashing service.
 * It extends the {@link HashingService} and {@link PasswordEncoder} interfaces.
 * This interface is used to inject the BCrypt hashing service in the {@link com.elixirline.service.elixirline_backend.userandrolemanagement.iam.infrastructure.hashing.bcrypt.services.HashingServiceImpl} class.
 */
public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
