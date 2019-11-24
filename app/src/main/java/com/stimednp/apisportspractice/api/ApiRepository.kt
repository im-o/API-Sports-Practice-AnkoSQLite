package com.stimednp.apisportspractice.api

import java.net.URL

/**
 * Created by rivaldy on 11/7/2019.
 */

class ApiRepository {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}