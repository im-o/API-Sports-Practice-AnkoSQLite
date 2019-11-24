package com.stimednp.apisportspractice.data

/**
 * Created by rivaldy on 11/23/2019.
 */

class Favorites(val id: Long?, val teamId: String?, val teamName: String?, val teamBadge: String?) {
    companion object{
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
    }
}