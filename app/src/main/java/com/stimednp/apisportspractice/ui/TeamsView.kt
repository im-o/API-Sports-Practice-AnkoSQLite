package com.stimednp.apisportspractice.ui

import com.stimednp.apisportspractice.data.Teams

/**
 * Created by rivaldy on 11/7/2019.
 */

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Teams>)
}