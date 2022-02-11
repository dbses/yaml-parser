package com.github.dbses.yaml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;

import java.util.Properties;

/**
 * @author yanglulu
 * @date 2022/1/20
 */
public class MultiProfilesYamlPropertiesFactoryBean extends YamlPropertiesFactoryBean {

    private String activeProfile;

    public MultiProfilesYamlPropertiesFactoryBean(String activeProfile) {
        this.activeProfile = activeProfile;
    }

    @Override
    protected Properties createProperties() {
        final Properties result = createStringAdaptingProperties();
        process((properties, map) -> {
            if (result.isEmpty()) {
                result.putAll(properties);
            } else {
                if (activeProfile.equals(properties.get("spring.profiles"))) {
                    result.putAll(properties);
                }
            }
        });
        return result;
    }

    public static Properties createStringAdaptingProperties() {
        return new Properties() {
            @Override
            public String getProperty(String key) {
                Object value = get(key);
                return (value != null ? value.toString() : null);
            }
        };
    }

}
