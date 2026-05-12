package com.example.modul3xml.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul3xml.R
import com.example.modul3xml.databinding.FragmentHomeBinding
import com.example.modul3xml.viewmodel.SongViewModel
import com.example.modul3xml.viewmodel.SongViewModelFactory
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SongViewModel by viewModels {
        SongViewModelFactory("Yuukosoo")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMenu()
        setupRecyclerViews()
        observeViewModel()
    }

    private fun setupMenu() {
        binding.toolbarHome.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_settings -> {
                    findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
                    true
                }
                else -> false
            }
        }
        binding.toolbarHome.title = getString(R.string.app_name)
    }

    private fun setupRecyclerViews() {
        binding.rvHighlight.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSong.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.songs.collect { songList ->
                        binding.rvHighlight.adapter = HighlightAdapter(songList)

                        binding.rvSong.adapter = SongListAdapter(
                            songs = songList,
                            onBrowserClick = { song ->
                                viewModel.onBrowserClicked(song.url)
                            },
                            onDetailClick = { song ->
                                viewModel.onSongDetailClicked(song)
                            }
                        )
                    }
                }

                launch {
                    viewModel.selectedSongId.collect { id ->
                        if (id != null) {
                            val song = viewModel.songs.value.find { it.id == id }
                            Timber.d("Navigasi ke Detail. Lagu dipilih: ${song?.title}")

                            val bundle = Bundle().apply {
                                putInt("songId", id)
                            }
                            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)

                            viewModel.clearSelection()
                        }
                    }
                }

                launch {
                    viewModel.navigateToUrl.collect { url ->
                        if (url != null) {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                            viewModel.clearUrlNavigation()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        _binding?.let { binding ->
            binding.toolbarHome.title = getString(R.string.app_name)
        }
    }
}