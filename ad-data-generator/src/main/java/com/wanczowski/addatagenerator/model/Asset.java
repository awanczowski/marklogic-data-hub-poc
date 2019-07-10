package com.wanczowski.addatagenerator.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Asset metadata sample that represents an output of a DAM API.
 */
@Data
@NoArgsConstructor
public class Asset {

  private String id;
  private String title;
  private LocalDateTime createdDate;
  private LocalDateTime modifiedDate;
  private List<String> keywords;

  private List<String> facebookCampaigns;
  private List<String> instagramCampaigns;
  private List<String> twitterCampaigns;

  /**
   *  Asset metadata sample that represents an output of a DAM API.
   * @param id - unique identifier for the asset
   * @param title - human readable title for the asset
   * @param createdDate - the date the asset was created
   * @param modifiedDate - the date the asset was last modified
   * @param keywords = a list of string representing keywords
   */
  public Asset(String id, String title, LocalDateTime createdDate,
               LocalDateTime modifiedDate, List<String> keywords) {
    this.id = id;
    this.title = title;
    this.createdDate = createdDate;
    this.modifiedDate = modifiedDate;
    this.keywords = keywords;
  }
}
