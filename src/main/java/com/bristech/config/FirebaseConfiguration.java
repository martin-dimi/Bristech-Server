package com.bristech.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@ComponentScan
public class FirebaseConfiguration {

    private static final Logger LOGGER = LogManager.getLogger(FirebaseConfiguration.class);

    @Bean
    public FirebaseAuth firebase() {

        try {
            FileInputStream serviceAccount = new FileInputStream("./src/main/resources/firebase_pk.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://bristech-be538.firebaseio.com")
                    .build();

            return FirebaseAuth.getInstance(FirebaseApp.initializeApp(options));

        } catch (IOException  e) {
            LOGGER.error("Could not configure firebase.");
            e.printStackTrace();
            return null;
        }

    }

}