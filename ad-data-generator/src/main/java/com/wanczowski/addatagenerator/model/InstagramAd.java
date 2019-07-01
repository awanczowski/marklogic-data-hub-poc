package com.wanczowski.addatagenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * {
 * "data": [
 * {
 * "name": "engagement",
 * "period": "lifetime",
 * "values": [
 * {
 * "value": 8
 * }
 * ],
 * "title": "Engagement",
 * "description": "Total number of likes and comments on the media object",
 * "id": "media_id/insights/engagement/lifetime"
 * },
 * {
 * "name": "impressions",
 * "period": "lifetime",
 * "values": [
 * {
 * "value": 13
 * }
 * ],
 * "title": "Impressions",
 * "description": "Total number of times the media object has been seen",
 * "id": "media_id/insights/impressions/lifetime"
 * },
 * {
 * "name": "reach",
 * "period": "lifetime",
 * "values": [
 * {
 * "value": 13
 * }
 * ],
 * "title": "Reach",
 * "description": "Total number of unique accounts that have seen the media object",
 * "id": "media_id/insights/reach/lifetime"
 * }
 * ]
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstagramAd {

    private List<AnalyticData> data;

    private transient String id;

    @Data
    @AllArgsConstructor
    public class AnalyticData {
        private String name;
        private String period;
        private List<Value> values;
        private String title;
        private String description;
        private String id;
    }

    @Data
    @AllArgsConstructor
    public class Value {
        private Integer value;
    }
}
