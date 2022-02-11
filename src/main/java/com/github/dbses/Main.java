package com.github.dbses;

import com.github.dbses.yaml.MultiProfilesYamlPropertiesFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ByteArrayResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {

        String filePath = "/Users/yanglulu/work/idea-workspace/icec-cloud-configurations/infra/accessor-service.yml";

        BufferedReader in = new BufferedReader(new FileReader(filePath));
        String content = "";
        String buffer;
        while ((buffer = in.readLine()) != null) {
            content += buffer;
        }

        YamlPropertiesFactoryBean yamlFactory = new MultiProfilesYamlPropertiesFactoryBean("alpha");
        yamlFactory.setResources(new ByteArrayResource(content.getBytes()));
        Properties commonsProperties = yamlFactory.getObject();

        System.out.println(commonsProperties);
    }

}
