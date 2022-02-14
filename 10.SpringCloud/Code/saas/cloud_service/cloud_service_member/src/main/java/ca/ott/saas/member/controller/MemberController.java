package ca.ott.saas.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HeZhu
 */
@RestController
@RefreshScope
@Slf4j
public class MemberController {

    @Value("${myconfig}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfig() {
        return configInfo;
    }
}
