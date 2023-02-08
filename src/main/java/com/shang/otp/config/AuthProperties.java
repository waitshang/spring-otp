package com.shang.otp.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shang.otp.model.AuthToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by shangwei2009@hotmail.com on 2023/2/7 16:23
 */
@Configuration
public class AuthProperties {

    private static final String DEMO_CONFIG = "[\n" +
            "  {\n" +
            "    \"name\": \"Example\",\n" +
            "    \"uri\": \"otpauth://totp/Example:alice@gmail.com?secret=TGABZXEP4LKB46AX&issuer=Example\"\n" +
            "  }\n" +
            "]";

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource(name = "jacksonObjectMapper")
    private ObjectMapper objectMapper;

    @Resource
    private Validator validator;

    private List<AuthToken> authTokens = new ArrayList<>();

    @PostConstruct
    public void init() throws IOException {
        final Path path = ResourceUtils.getFile(applicationName + File.separator + "data.json").toPath();
        final Path parent = path.getParent();
        if (Files.notExists(parent)) {
            Files.createDirectories(parent);
        }
        if (Files.notExists(path)) {
            Files.createFile(path);
            Files.write(path, DEMO_CONFIG.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
        }
        final String json = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        try {
            authTokens = objectMapper.readValue(json, new TypeReference<List<AuthToken>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("无有效的配置: " + path.toAbsolutePath());
        }
        if (authTokens == null || authTokens.isEmpty()) {
            throw new RuntimeException("无有效的配置: " + path.toAbsolutePath());
        }
        for (final AuthToken authToken : authTokens) {
            final Set<ConstraintViolation<AuthToken>> violationSet = validator.validate(authToken, Default.class);
            if (!violationSet.isEmpty()) {
                // 由CommonErrorHandler中的handleConstraintViolationException（原本用来处理Query Params参数异常）来处理这个异常
                throw new ConstraintViolationException(violationSet);
            }
        }
    }

    public List<AuthToken> getAuthTokens() {
        return authTokens;
    }
}
