package com.sample.bonial_examination

import com.google.gson.Gson
import com.sample.bonial_examination.data.model.Embedded
import com.sample.bonial_examination.data.model.Item
import com.sample.bonial_examination.presentation.helper.ItemFilterHelper
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HelperUnitTest {

    var testJsonStr = "{\n" +
        "  \"_embedded\": {\n" +
        "    \"contents\": [\n" +
        "      {\n" +
        "        \"placement\": \"ad_placement__shelf_sort_managed\",\n" +
        "        \"adFormat\": null,\n" +
        "        \"contentFormatSource\": \"destinationsApi\",\n" +
        "        \"contentType\": \"superBannerCarousel\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"placement\": \"ad_placement__shelf_sort_managed\",\n" +
        "        \"adFormat\": null,\n" +
        "        \"contentFormatSource\": \"destinationsApi\",\n" +
        "        \"contentType\": \"storyCarousel\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"placement\": \"ad_placement__shelf_sort_managed\",\n" +
        "        \"adFormat\": \"ad_format__brochure_card_cover\",\n" +
        "        \"contentFormatSource\": \"destinationsApi\",\n" +
        "        \"contentType\": \"brochure\",\n" +
        "        \"content\": {\n" +
        "          \"id\": 1622007859,\n" +
        "          \"title\": \"Top Angebote\",\n" +
        "          \"validFrom\": \"2022-01-30T23:00:00.000+0000\",\n" +
        "          \"validUntil\": \"2022-02-05T19:00:00.000+0000\",\n" +
        "          \"publishedFrom\": \"2022-01-29T19:00:00.000+0000\",\n" +
        "          \"publishedUntil\": \"2022-02-05T19:00:00.000+0000\",\n" +
        "          \"type\": \"BROCHURE\",\n" +
        "          \"pageCount\": 63,\n" +
        "          \"publisher\": {\n" +
        "            \"id\": 1013,\n" +
        "            \"name\": \"Lidl\",\n" +
        "            \"type\": \"RETAILER\"\n" +
        "          },\n" +
        "          \"retailer\": {\n" +
        "            \"id\": 1013,\n" +
        "            \"name\": \"Lidl\"\n" +
        "          },\n" +
        "          \"brochureImage\": \"https://content-media.bonial.biz/b2cc4f98-c83a-4880-894e-683dd4b3ab3c/preview.jpg\",\n" +
        "          \"badges\": [],\n" +
        "          \"isEcommerce\": false,\n" +
        "          \"distance\": 1.384040569776516,\n" +
        "          \"hideValidityDate\": false\n" +
        "        }\n" +
        "      },\n" +
        "      {\n" +
        "        \"placement\": \"ad_placement__shelf_sort_managed\",\n" +
        "        \"adFormat\": \"ad_format__brochure_card_cover\",\n" +
        "        \"contentFormatSource\": \"destinationsApi\",\n" +
        "        \"contentType\": \"brochure\",\n" +
        "        \"content\": {\n" +
        "          \"id\": 1620127151,\n" +
        "          \"title\": \"AB 30€ EINKAUFSWERT\",\n" +
        "          \"validFrom\": \"2022-01-19T23:00:00.000+0000\",\n" +
        "          \"validUntil\": \"2022-02-03T22:00:00.000+0000\",\n" +
        "          \"publishedFrom\": \"2022-01-19T23:00:00.000+0000\",\n" +
        "          \"publishedUntil\": \"2022-02-03T22:00:00.000+0000\",\n" +
        "          \"type\": \"BROCHURE\",\n" +
        "          \"pageCount\": 6,\n" +
        "          \"publisher\": {\n" +
        "            \"id\": 1080,\n" +
        "            \"name\": \"Woolworth\",\n" +
        "            \"type\": \"RETAILER\"\n" +
        "          },\n" +
        "          \"retailer\": {\n" +
        "            \"id\": 1080,\n" +
        "            \"name\": \"Woolworth\"\n" +
        "          },\n" +
        "          \"brochureImage\": \"https://content-media.bonial.biz/70a9d8f2-f76a-44d4-9fa2-315a409303d6/preview.jpg\",\n" +
        "          \"badges\": [],\n" +
        "          \"isEcommerce\": false,\n" +
        "          \"distance\": 3.247533413758663,\n" +
        "          \"hideValidityDate\": false\n" +
        "        }\n" +
        "      },\n" +
        "      {\n" +
        "        \"placement\": \"ad_placement__shelf_sort_managed\",\n" +
        "        \"adFormat\": \"ad_format__brochure_card_cover\",\n" +
        "        \"contentFormatSource\": \"destinationsApi\",\n" +
        "        \"contentType\": \"brochure\",\n" +
        "        \"content\": {\n" +
        "          \"id\": 1620127151,\n" +
        "          \"title\": \"AB 30€ EINKAUFSWERT\",\n" +
        "          \"validFrom\": \"2022-01-19T23:00:00.000+0000\",\n" +
        "          \"validUntil\": \"2022-02-03T22:00:00.000+0000\",\n" +
        "          \"publishedFrom\": \"2022-01-19T23:00:00.000+0000\",\n" +
        "          \"publishedUntil\": \"2022-02-03T22:00:00.000+0000\",\n" +
        "          \"type\": \"BROCHURE\",\n" +
        "          \"pageCount\": 6,\n" +
        "          \"publisher\": {\n" +
        "            \"id\": 1080,\n" +
        "            \"name\": \"Woolworth\",\n" +
        "            \"type\": \"RETAILER\"\n" +
        "          },\n" +
        "          \"retailer\": {\n" +
        "            \"id\": 1080,\n" +
        "            \"name\": \"Woolworth\"\n" +
        "          },\n" +
        "          \"brochureImage\": \"https://content-media.bonial.biz/70a9d8f2-f76a-44d4-9fa2-315a409303d6/preview.jpg\",\n" +
        "          \"badges\": [],\n" +
        "          \"isEcommerce\": false,\n" +
        "          \"distance\": 5.247533413758663,\n" +
        "          \"hideValidityDate\": false\n" +
        "        }\n" +
        "      }\n" +
        "    ]\n" +
        "  }\n" +
        "}"

    @Test
    fun getItemList() {
        var itemList: ArrayList<Item>
        var embedded = Gson().fromJson(testJsonStr, Embedded::class.java)
        embedded.embedded?.contents?.let {
            itemList = it
            assertEquals(5, itemList.size)
        }
    }

    @Test
    fun filterItemListBrochures() {
        var embedded = Gson().fromJson(testJsonStr, Embedded::class.java)
        embedded.embedded?.contents?.let {
            assertEquals(3, ItemFilterHelper().returnContentTypeFilterBrochure(it).size)
        }
    }

    @Test
    fun filterItemListLessThanFiveKm() {
        var itemList: ArrayList<Item>
        var embedded = Gson().fromJson(testJsonStr, Embedded::class.java)
        embedded.embedded?.contents?.let { it ->
            itemList = ItemFilterHelper().returnContentTypeFilterBrochure(it)
            assertEquals(2, ItemFilterHelper().returnLessEqualFiveKmDistance(itemList).size)
        }

    }
}