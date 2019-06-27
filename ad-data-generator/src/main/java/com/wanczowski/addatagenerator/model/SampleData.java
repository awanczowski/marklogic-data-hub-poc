package com.wanczowski.addatagenerator.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
public class SampleData {
    private List<Asset> assets;
    private List<FacebookAd> facebookAds;
    private List<InstagramAd> instagramAds;
    private List<TwitterAd> twitterAds;
}
