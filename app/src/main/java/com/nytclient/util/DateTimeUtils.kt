package com.nytclient.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

/**
 * Created by Dogan Gulcan on 9/16/17.
 */
@Singleton
class DateTimeUtils {

    companion object {
        private val nytSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ", Locale.US)
        @Volatile private var INSTANCE: DateTimeUtils? = null

        fun getInstance(): DateTimeUtils =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: DateTimeUtils().also { it ->
                        INSTANCE = it
                    }

                }
    }

    init {
        nytSimpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    }

    fun getTimeStampFromDate(date: String, simpleDateFormat: SimpleDateFormat = nytSimpleDateFormat): Long? {
        var mDate: Date? = null

        try {
            mDate = simpleDateFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()

        } finally {
            return mDate?.time
        }
    }
}