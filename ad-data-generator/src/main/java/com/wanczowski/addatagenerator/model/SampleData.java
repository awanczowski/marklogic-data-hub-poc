package com.wanczowski.addatagenerator.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Container object for passing all generated data.
 */
@Data
@AllArgsConstructor
public class SampleData {
  private List<Asset> assets;
  private List<FacebookAd> facebookAds;
  private List<InstagramAd> instagramAds;
  private List<TwitterAd> twitterAds;
}
