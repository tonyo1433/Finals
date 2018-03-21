package com.example.tonyo.finals

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val url = "http://ws.audioscrobbler.com/2.0/?method=album.search&album=believe" +
            "&api_key=e224027bbd7b17fe9ac0971d12b85717&format=json"

    private val addAlbumList = ArrayList<Albums>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loading_indicator.visibility = View.INVISIBLE
        btnSearch.setOnClickListener {
            AlbumSearch()
            loading_indicator.visibility = View.VISIBLE
            addAlbumList.clear()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
    private fun AlbumSearch() {
        for (i in 1..50) {
            doAsync {

                var tempName = searchTxt.text.toString()
                val resultJson = URL(url + tempName).readText()
                val jsonObject = JSONObject(resultJson)
                val getName = jsonObject.getJSONObject("results").getJSONArray("albummatches")
                        .getJSONObject(i).getString("name")
                val getArtist = jsonObject.getJSONObject("results").getJSONArray("albummatches")
                        .getJSONObject(i).getString("artist")
//                val getImage = jsonObject.getJSONObject("results").getJSONArray("albummatches")
//                        .getJSONObject(0).getString("image")
                uiThread {
                    recyclerView.adapter = AlbumAdapter(this@MainActivity.addAlbumList)
                    addAlbumList.add(Albums(getName, getArtist))
                    loading_indicator.visibility = View.INVISIBLE
                }
            }
        }
    }

}
