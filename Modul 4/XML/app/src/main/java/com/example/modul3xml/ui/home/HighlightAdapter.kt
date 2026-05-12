package com.example.modul3xml.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.modul3xml.data.Song
import com.example.modul3xml.databinding.ItemHighlightSongBinding

class HighlightAdapter(
    private val song: List<Song>
) : RecyclerView.Adapter<HighlightAdapter.HighlightViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        val binding = ItemHighlightSongBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HighlightViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {
        holder.bind(song[position])
    }

    override fun getItemCount(): Int = song.size

    class HighlightViewHolder(private val binding: ItemHighlightSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(song: Song) {
            binding.imageHighlight.setImageResource(song.imageRes)
        }
    }
}