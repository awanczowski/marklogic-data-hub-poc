package com.wanczowski.addatagenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanczowski.addatagenerator.model.SampleData;
import com.wanczowski.addatagenerator.service.DataGenerationService;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot application that will initialize and automatically generate sample
 * Ad and Asset metadata.
 */
@Log4j2
@SpringBootApplication
public class AdDataGeneratorApplication implements ApplicationRunner {

  public static void main(String[] args) {
    SpringApplication.run(AdDataGeneratorApplication.class, args);
  }

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private DataGenerationService dataGenerationService;

  @Value("${generator.save_location}")
  private String saveLocation;

  /**
   * Runner that will take the generated data and write it to disk.
   *
   * @param args - application arguments.
   * @throws Exception - general exception thrown if any point of the process fails.
   */
  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("Starting to generate...");
    SampleData sampleData = this.dataGenerationService.generate();

    sampleData.getAssets().forEach(asset -> {
          try {
            FileUtils.writeStringToFile(
                new File(this.saveLocation + "/asset/" + asset.getId() + ".json"),
                this.objectMapper.writeValueAsString(asset),
                StandardCharsets.UTF_8.name());
          } catch (IOException e) {
            log.error("Can not write asset " + asset.getId(), e);
          }
        }
    );

    sampleData.getFacebookAds().forEach(ad -> {
          try {
            FileUtils.writeStringToFile(
                new File(this.saveLocation + "/facebook/" + ad.getId() + ".json"),
                this.objectMapper.writeValueAsString(ad),
                StandardCharsets.UTF_8.name());
          } catch (IOException e) {
            log.error("Can not write Facebook Ad " + ad.getId(), e);
          }
        }
    );

    sampleData.getInstagramAds().forEach(ad -> {
          try {
            FileUtils.writeStringToFile(
                new File(this.saveLocation + "/instagram/" + ad.getId() + ".json"),
                this.objectMapper.writeValueAsString(ad),
                StandardCharsets.UTF_8.name());
          } catch (IOException e) {
            log.error("Can not write Instagram Ad " + ad.getId(), e);
          }
        }
    );

    sampleData.getTwitterAds().forEach(ad -> {
          try {
            FileUtils.writeStringToFile(
                new File(this.saveLocation + "/twitter/" + ad.getId() + ".json"),
                this.objectMapper.writeValueAsString(ad),
                StandardCharsets.UTF_8.name());
          } catch (IOException e) {
            log.error("Can not write Twitter Ad " + ad.getId(), e);
          }
        }
    );

    log.info("Generation complete!");
  }
}

