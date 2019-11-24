package com.stimednp.apisportspractice.api

import android.net.Uri
import com.stimednp.apisportspractice.BuildConfig

/**
 * Created by rivaldy on 11/7/2019.
 */

object TheSportDBApi {
    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("search_all_teams.php")
            .appendQueryParameter("l", league) //exmp: https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League
            .build()
            .toString()
    }
    fun getDetailTeams(teamId: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", teamId)
            .build()
            .toString()
    }
}