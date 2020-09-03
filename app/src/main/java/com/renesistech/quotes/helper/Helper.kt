package com.renesistech.quotes.helper

import android.app.Activity
import android.content.Intent
import android.os.Handler
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

object Helper {

    fun makeDelay(activity: Activity, aClass: KClass<*>, millSec: Int) {
        val handler = Handler()
        handler.postDelayed({
            activity.finish()
            activity.startActivity(Intent(activity, aClass.java))
        }, millSec.toLong())
    }


    fun calcDays(fromDate: String, toDate: String): Long {
        var diff: Long = 0
        val myFormat = SimpleDateFormat("yyyy-MM-dd")
        val splitStr1 = fromDate.split("T".toRegex()).toTypedArray()
        val splitStr2 = toDate.split("T".toRegex()).toTypedArray()
        return try {
            val date1 = myFormat.parse(splitStr1[0])
            val date2 = myFormat.parse(splitStr2[0])
            diff = date2.time - date1.time
            TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
        } catch (e: ParseException) {
            e.printStackTrace()
            TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
        }
    }


    fun remainingDays(startDate: Date, endDate: Date): String? {
        //milliseconds
        var different = endDate.time - startDate.time
        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24
        val elapsedDays = different / daysInMilli
        different = different % daysInMilli
        val elapsedHours = different / hoursInMilli
        different = different % hoursInMilli
        val elapsedMinutes = different / minutesInMilli
        different = different % minutesInMilli
        val elapsedSeconds = different / secondsInMilli

//        System.out.printf(
//                "%d days, %d hours, %d minutes, %d seconds%n",
//                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
        return elapsedDays.toString() + ""
    }
}
