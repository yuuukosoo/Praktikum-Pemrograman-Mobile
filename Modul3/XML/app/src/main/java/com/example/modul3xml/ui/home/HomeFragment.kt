package com.example.modul3xml.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul3xml.R
import com.example.modul3xml.data.SongData
import com.example.modul3xml.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        setupHighlightList()
        setupSongList()
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

    private fun setupHighlightList() {
        binding.rvHighlight.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHighlight.adapter = HighlightAdapter(SongData.SongList)
    }

    private fun setupSongList() {
        binding.rvSong.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSong.adapter = SongListAdapter(
            songs = SongData.SongList,
            onBrowserClick = { song ->
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(song.url)))
            },
            onDetailClick = { song ->
                val bundle = Bundle().apply {
                    putInt("songId", song.id)
                }
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            }
        )
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