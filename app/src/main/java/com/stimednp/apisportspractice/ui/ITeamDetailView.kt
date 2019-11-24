package com.stimednp.apisportspractice.ui

import com.stimednp.apisportspractice.data.Teams

/**
 * Created by rivaldy on 11/23/2019.
 */

interface ITeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Teams>)
}