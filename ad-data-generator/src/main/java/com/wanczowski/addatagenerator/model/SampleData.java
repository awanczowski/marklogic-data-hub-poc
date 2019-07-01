package com.wanczowski.addatagenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SampleData {
    private List<Asset> assets;
    private List<FacebookAd> facebookAds;
    private List<InstagramAd> instagramAds;
    private List<TwitterAd> twitterAds;
}
