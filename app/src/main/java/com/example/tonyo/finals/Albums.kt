package com.example.tonyo.finals

import org.json.JSONObject

/**
 * Created by Tonyo on 3/21/2018.
 */
class Albums {
        var name: String? = null
        var artist: String? = null
        var url: String? = null
        var image: String? = null
        constructor(name: String, artist: Int, url: JSONObject, image: String) {
            this.name = name
            this.artist = artist.toString()
            this.url = url.toString()
            this.image = image
        }
    }