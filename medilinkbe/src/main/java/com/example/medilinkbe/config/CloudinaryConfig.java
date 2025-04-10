package com.example.medilinkbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
      @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dgl2c1sat",
            "api_key", "949563314114383",
            "api_secret", "pdDEBOxn830hCyVz4wsHJmEJgmM"
        ));
    }
}
