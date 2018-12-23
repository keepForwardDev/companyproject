package com.doctortech.framework.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义参数
 */
@Component
@ConfigurationProperties(prefix = CustomProperties.Custom_Properties_Prefix)
public class CustomProperties {
    public static final String Custom_Properties_Prefix="company.properties";
    private String superPassword;

    private Boolean openkaptcha;

    private Integer sessionValidationInterval;

    private Integer sessionInvalidateTime;

    private String anonUrl;

    private Boolean openSwagger;

    private String termsOfServiceUrl;

    public String getSuperPassword() {
        return superPassword;
    }

    public void setSuperPassword(String superPassword) {
        this.superPassword = superPassword;
    }

    public Boolean getOpenkaptcha() {
        return openkaptcha;
    }

    public void setOpenkaptcha(Boolean openkaptcha) {
        this.openkaptcha = openkaptcha;
    }

    public Integer getSessionValidationInterval() {
        return sessionValidationInterval;
    }

    public void setSessionValidationInterval(Integer sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }

    public Integer getSessionInvalidateTime() {
        return sessionInvalidateTime;
    }

    public void setSessionInvalidateTime(Integer sessionInvalidateTime) {
        this.sessionInvalidateTime = sessionInvalidateTime;
    }

    public String getAnonUrl() {
        return anonUrl;
    }

    public void setAnonUrl(String anonUrl) {
        this.anonUrl = anonUrl;
    }

    public Boolean getOpenSwagger() {
        return openSwagger;
    }

    public void setOpenSwagger(Boolean openSwagger) {
        this.openSwagger = openSwagger;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }
}
