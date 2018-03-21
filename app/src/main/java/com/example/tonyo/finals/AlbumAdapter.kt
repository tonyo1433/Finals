package com.example.tonyo.finals

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.tonyo.finals.R.id.album_image
import kotlinx.android.synthetic.main.song_list_item.view.*

/**
 * Created by Tonyo on 3/21/2018.
 */
class AlbumAdapter(val context: ArrayList<Albums>): RecyclerView.Adapter<AlbumAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.song_list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return context.size;    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        holder?.view?.album_title?.text = context[position].name
        holder?.view?.album_artist?.text = context[position].artist
    }

    class CustomViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

}

