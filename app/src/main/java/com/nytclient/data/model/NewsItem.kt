package com.nytclient.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Dogan Gulcan on 9/12/17.
 */
@Entity(tableName = "News")
data class NewsItem(

        @SerializedName("byline")
        @ColumnInfo(name = "byline")
        var byline: String = "",

        @SerializedName("created_date")
        @ColumnInfo(name = "created_date")
        var createdDate: String = "",

        @SerializedName("updated_date")
        @ColumnInfo(name = "updated_date")
        var updatedDate: String = "",

        @SerializedName("des_facet")
        @Ignore
        var desFacet: Any = "",

        @SerializedName("item_type")
        @Ignore
        var itemType: String = "",

        @SerializedName("source")
        @ColumnInfo(name = "source")
        var source: String = "",

        @SerializedName("url")
        @PrimaryKey
        @ColumnInfo(name = "url")
        var url: String = "",

        @SerializedName("title")
        @ColumnInfo(name = "title")
        var title: String = "",

        @SerializedName("abstract")
        @ColumnInfo(name = "description")
        var description: String = "",

        @SerializedName("thumbnail_standard")
        @ColumnInfo(name = "thumbnail_standard")
        var thumbnailUrl: String = "",

        @SerializedName("published_date")
        @ColumnInfo(name = "published_date")
        var publishedDate: String = ""
)



