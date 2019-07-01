package com.wanczowski.addatagenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class Asset {

    private String id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<String> keywords;

    private List<Relation> relations;

    private List<String> facebookCampaigns;
    private List<String> instagramCampaigns;
    private List<String> twitterCampaigns;

    public Asset(String id, String title, LocalDateTime createdDate, LocalDateTime modifiedDate, List<String> keywords) {
        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.keywords = keywords;
    }

    @Data
    @AllArgsConstructor
    public class Relation {
        String id;
        RelationType type;
    }

    @Getter
    @AllArgsConstructor
    public enum RelationType {
        IS_PART_OF("isPartOf"),
        HAS_PART("hasPart");
        private String label;
    }
}
