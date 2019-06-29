package com.wanczowski.addatagenerator.service;

import com.wanczowski.addatagenerator.model.*;
import com.wanczowski.addatagenerator.model.taxonomy.ColorTaxonomy;
import com.wanczowski.addatagenerator.model.taxonomy.GarmentTaxonomy;
import com.wanczowski.addatagenerator.model.taxonomy.MaterialTaxonomy;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataGenerationService {

    @Value("${generator.runs}")
    private Integer runs;

    private static final String TWITTER_ACCOUNT_ID = "18ce54d4x5t";

    private static final String INSTAGRAM_ENGAEMENT_POSTFIX = "/insights/engagement/lifetime";
    private static final String INSTAGRAM_IMPRESIONS_POSTFIX = "/insights/impressions/lifetime";
    private static final String INSTAGRAM_REACH_POSTFIX = "/insights/reach/lifetime";

    private List<Asset> assets = new ArrayList<>();
    private List<FacebookAd> facebookAds = new ArrayList<>();
    private List<InstagramAd> instagramAds = new ArrayList<>();
    private List<TwitterAd> twitterAds = new ArrayList<>();

    public SampleData generate() {

        for (int run = 0; run <= this.runs; run++) {
            List<String> keywords = getKeywords();
            Asset  asset = new Asset(
                        UUID.randomUUID().toString(),
                        String.join(" ", keywords),
                        null,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        keywords);
            

            TwitterAd twitterAd = this.generateTwitterAd();
            this.twitterAds.add(twitterAd);
            asset.setTwitterCampaigns(twitterAd.getData().stream().map(d -> d.getId()).collect(Collectors.toList()));

            InstagramAd instagramAd = this.generateInstagramAd(asset.getId());
            this.instagramAds.add(instagramAd);

            FacebookAd facebookAd = this.generateFacebookAd();
            this.facebookAds.add(facebookAd);
            asset.setFacebookCampaigns(Arrays.asList(facebookAd.getId()));

            this.assets.add(asset);
        }

        return new SampleData(this.assets, this.facebookAds, this.instagramAds, this.twitterAds);
    }

    private List<String> getKeywords() {
        List<String> keywords = new ArrayList<>();
        Random random = new Random();

        ColorTaxonomy[] colorValues = ColorTaxonomy.values();
        Integer colorPos = random.nextInt(colorValues.length);
        keywords.add(colorValues[colorPos].getLabel());

        MaterialTaxonomy[] materialValues = MaterialTaxonomy.values();
        Integer materialPos = random.nextInt(materialValues.length);
        keywords.add(materialValues[materialPos].getLabel());

        GarmentTaxonomy[] garmentValues = GarmentTaxonomy.values();
        Integer garmentPos = random.nextInt(garmentValues.length);
        keywords.add(garmentValues[garmentPos].getLabel());

        return keywords;
    }

    public TwitterAd generateTwitterAd() {
        TwitterAd twitterAd = new TwitterAd();
        twitterAd.setDataType("reach");

        String campaignId = RandomStringUtils.randomAlphanumeric(5);
        twitterAd.setId(campaignId);
        TwitterAd.Params params = twitterAd.new Params(
                Arrays.asList(campaignId),
                LocalDateTime.now().minusWeeks(1),
                LocalDateTime.now().plusWeeks(1),
                TWITTER_ACCOUNT_ID);

        Double impressions = Math.floor(Math.random() * 1000);

        TwitterAd.AnalyticData analyticData = twitterAd.new AnalyticData(campaignId,
                impressions.intValue(), impressions / 100);

        twitterAd.setData(Arrays.asList(analyticData));
        twitterAd.setRequest(twitterAd.new Request(params));

        return twitterAd;
    }

    public InstagramAd generateInstagramAd(final String mediaId) {
        InstagramAd instagramAd = new InstagramAd();
        instagramAd.setId(mediaId);

        Double engagementCount = Math.floor(Math.random() * 10);
        InstagramAd.AnalyticData engagement = instagramAd.new AnalyticData(
                "engagement",
                "lifetime",
                Arrays.asList(instagramAd.new Value(engagementCount.intValue())),
                "Engagement",
                "Total number of likes and comments on the media object",
                mediaId + INSTAGRAM_ENGAEMENT_POSTFIX
        );

        Double impressionCount = Math.floor(Math.random() * 1000);
        InstagramAd.AnalyticData impressions = instagramAd.new AnalyticData(
                "impressions",
                "lifetime",
                Arrays.asList(instagramAd.new Value(impressionCount.intValue())),
                "Impressions",
                "Total number of times the media object has been seen",
                mediaId + INSTAGRAM_IMPRESIONS_POSTFIX
        );

        Double reachCount = Math.floor(Math.random() * 10);
        InstagramAd.AnalyticData reach = instagramAd.new AnalyticData(
                "reach",
                "lifetime",
                Arrays.asList(instagramAd.new Value(reachCount.intValue())),
                "Reach",
                "Total number of unique accounts that have seen the media object",
                mediaId + INSTAGRAM_REACH_POSTFIX
        );

        instagramAd.setData(Arrays.asList(engagement, impressions, reach));

        return instagramAd;
    }

    public FacebookAd generateFacebookAd() {
        FacebookAd facebookAd = new FacebookAd();
        facebookAd.setId(RandomStringUtils.randomNumeric(13));
        facebookAd.setName("--- Campaign");

        Double impressions = Math.floor(Math.random() * 1000);
        FacebookAd.AnalyticData analyticData = facebookAd.new AnalyticData(
                Integer.toString(impressions.intValue()),
                LocalDate.now().minusWeeks(1),
                LocalDate.now()
        );

        String cursor = RandomStringUtils.randomAlphabetic(6);
        facebookAd.setPaging(facebookAd.new Paging(facebookAd.new Cursors(cursor, cursor)));
        facebookAd.setInsights(facebookAd.new Insights(Arrays.asList(analyticData)));
        return facebookAd;
    }

}
