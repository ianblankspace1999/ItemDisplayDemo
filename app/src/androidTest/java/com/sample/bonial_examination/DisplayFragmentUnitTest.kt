package com.sample.bonial_examination

import android.service.autofill.OnClickAction
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.exam.ph.examination.adapter.ItemListAdapter
import com.google.android.flexbox.*
import com.google.gson.Gson
import com.sample.bonial_examination.data.model.Embedded
import com.sample.bonial_examination.data.model.Item
import com.sample.bonial_examination.presentation.activity.MainActivity
import com.sample.bonial_examination.presentation.helper.ItemFilterHelper
import com.sample.bonial_examination.presentation.helper.ViewHelper
import kotlinx.android.synthetic.main.fragment_display.*

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DisplayFragmentUnitTest: Fragment() {
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
    private var items: MutableList<Item> = ArrayList()
    private lateinit var recyclerView: RecyclerView

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `displayListOnDashboard`() {

//        var embedded = Gson().fromJson(testJsonStr, Embedded::class.java)
//        embedded.embedded?.contents?.let {
//            items = ItemFilterHelper().returnContentTypeFilterBrochure(it)
//        }
//        activity?.let {
//            recyclerView = it.findViewById(R.id.rvDisplay)
//            recyclerView.adapter = ItemListAdapter(this,
//                items as ArrayList<Item>,
//                ViewHelper().getScreenWidthResolution(it),
//                true)
//
//            val layoutManager = FlexboxLayoutManager(it)
//            layoutManager.flexDirection = FlexDirection.ROW
//            layoutManager.flexWrap = FlexWrap.WRAP
//            layoutManager.justifyContent = JustifyContent.FLEX_START
//            layoutManager.alignItems = AlignItems.FLEX_START
//            recyclerView.layoutManager = layoutManager
//        }
        onView(withId(R.id.btnFilter)).check(matches(withText("Closer to 5km distance")))

    }
}