package com.example.modul3xml.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.modul3xml.R
import com.example.modul3xml.data.SongData
import com.example.modul3xml.databinding.FragmentDetailBinding
import com.example.modul3xml.util.LanguageSettings
import com.example.modul3xml.viewmodel.SongViewModel
import com.example.modul3xml.viewmodel.SongViewModelFactory
import timber.log.Timber

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SongViewModel by viewModels {
        SongViewModelFactory("Yuukosoo")
    }

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
        val song = viewModel.songs.value.find { it.id == songId }

        if (song == null) {
            binding.toolbarDetail.title = getString(R.string.title_detail)
            Timber.w("DetailFragment: Lagu dengan ID $songId tidak ditemukan!")
            return
        }

        Timber.d("DetailFragment: Menampilkan detail lagu ${song.title}")

        val isIndonesian = LanguageSettings.isIndonesian(requireContext())

        binding.toolbarDetail.title = song.title
        binding.imageDetail.setImageResource(song.imageRes)
        binding.textDetailTitle.text = song.title
        binding.textDetailYear.text = song.year
        binding.textDetailMean.text = if (isIndonesian) song.meanId else song.meanEn
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}