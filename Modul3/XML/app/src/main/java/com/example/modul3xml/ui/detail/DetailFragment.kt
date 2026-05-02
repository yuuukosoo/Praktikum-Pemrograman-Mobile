package com.example.modul3xml.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.modul3xml.R
import com.example.modul3xml.data.SongData
import com.example.modul3xml.databinding.FragmentDetailBinding
import com.example.modul3xml.util.LanguageSettings

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        bindSongData()
    }

    private fun setupToolbar() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun bindSongData() {
        val songId = arguments?.getInt("songId") ?: -1
        val song = SongData.findSongById(songId)

        if (song == null) {
            binding.toolbarDetail.title = getString(R.string.title_detail)
            return
        }

        val isIndonesian = LanguageSettings.isIndonesian(requireContext())

        binding.toolbarDetail.title = song.title
        binding.imageDetail.setImageResource(song.imageRes)
        binding.textDetailTitle.text = song.title
        binding.textDetailYear.text = song.year
        binding.textDetailMean.text = if (isIndonesian) song.meanId else song.MeanEn
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}