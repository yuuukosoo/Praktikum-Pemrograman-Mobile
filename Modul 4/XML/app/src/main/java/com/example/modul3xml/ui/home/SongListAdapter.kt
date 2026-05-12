package com.example.modul3xml.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.modul3xml.data.Song
import com.example.modul3xml.databinding.ItemSongBinding
import com.example.modul3xml.util.LanguageSettings

class SongListAdapter(
    private val songs: List<Song>,
    private val onBrowserClick: (Song) -> Unit,
    private val onDetailClick: (Song) -> Unit
) : RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(songs[position], onBrowserClick, onDetailClick)
    }

    override fun getItemCount(): Int = songs.size

    class SongViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            song: Song,
            onBrowserClick: (Song) -> Unit,
            onDetailClick: (Song) -> Unit
        ) {
            val isIndonesian = LanguageSettings.isIndonesian(binding.root.context)

            binding.apply {
                imageSong.setImageResource(song.imageRes)
                textTitle.text = song.title
                textYear.text = song.year
                textMean.text = if (isIndonesian) song.meanId else song.meanEn

                buttonBrowser.setOnClickListener { onBrowserClick(song) }
                buttonDetail.setOnClickListener { onDetailClick(song) }
            }
        }
    }
}