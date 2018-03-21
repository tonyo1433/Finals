package com.example.tonyo.finals

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.song_list_item.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val url = "https://www.last.fm/api"
    private val Key_Name = "name"
    private val Key_Artist = "artist"
    private val Key_Image = "image"
    private val AlbumList = ArrayList<Albums>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loading_indicator.visibility = View.INVISIBLE

        btnSearch.setOnClickListener {
            AlbumSearch()
            loading_indicator.visibility = View.VISIBLE
            AlbumList.clear()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
    private fun AlbumSearch() {

        doAsync {

            var tempName = searchTxt.text.toString()
            val resultJson = URL(url + tempName).readText()
            val jsonObject = JSONObject(resultJson)
            val getArtist = jsonObject.getInt(Key_Artist)
            val getName = jsonObject.getString(Key_Name)
            val getImage = jsonObject.getJSONObject(Key_Image)
            val getTitle = jsonObject.getString("title")

            uiThread {
                recyclerView.adapter = AlbumAdapter(AlbumList)
                AlbumList.add(Albums(getName,
                        getArtist,
                        getImage,getTitle))
                album_title.text = getName.substring(0, 1).toUpperCase() + getName.substring(1)
                Picasso.with(this@MainActivity).load(getImage).into(album_image)
                loading_indicator.visibility = View.INVISIBLE
            }
        }
    }


}

