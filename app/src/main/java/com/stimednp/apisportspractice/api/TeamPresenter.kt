package com.stimednp.apisportspractice.api

import com.google.gson.Gson
import com.stimednp.apisportspractice.ui.TeamsView
import com.stimednp.apisportspractice.data.TeamsResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by rivaldy on 11/7/2019.
 */

class TeamPresenter(private val view: TeamsView, private val apiRepository: ApiRepository, private val gson: Gson) {
    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(
                TheSportDBApi.getTeams(
                    league
                )
            ), TeamsResponse::class.java)
            uiThread{
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
}