package com.stimednp.apisportspractice.api

import com.google.gson.Gson
import com.stimednp.apisportspractice.data.TeamsResponse
import com.stimednp.apisportspractice.ui.ITeamDetailView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by rivaldy on 11/23/2019.
 */

class TeamDetailPresenter(
    private val view: ITeamDetailView,
    private val apiRepository: ApiRepository,
    val gson: Gson
) {
    fun getDetailTeam(teamId: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getDetailTeams(teamId)),
                TeamsResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showTeamDetail(data.teams)
            }
        }
    }
}