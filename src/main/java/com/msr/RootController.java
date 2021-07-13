package com.msr;

import com.google.common.collect.ImmutableMap;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Respond to root requests
 */
@Log4j2
@RestController
@RequestMapping("/")
public class RootController {

    private final BuildProperties buildProperties;

    @Autowired
    public RootController(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @GetMapping("/")
    public Map<String, Object> getRoot() {
        return ImmutableMap.of(
                "status", "up",
                "version", buildProperties.getVersion(),
                "name", buildProperties.getName(),
                "time", buildProperties.getTime()

        );
    }
}