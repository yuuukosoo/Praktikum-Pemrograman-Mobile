package com.example.modul4compose.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.modul4compose.viewmodel.SongViewModel
import timber.log.Timber

@Composable
fun DetailScreen(navController: NavController, songId: Int?, viewModel: SongViewModel) {
    val songList by viewModel.songs.collectAsState()
    val song = songList.find { it.id == songId }
    val lang = LocalConfiguration.current.locales.get(0).language
    val isId = lang == "in" || lang == "id"

    LaunchedEffect(song) {
        song?.let {
            Timber.d("Menampilkan Detail untuk lagu: ${it.title}")
        }
    }

    Scaffold(
        topBar = {
            Surface(color = Color.Black, shadowElevation = 4.dp) {
                Row(Modifier.fillMaxWidth().statusBarsPadding().height(64.dp).padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White)
                    }
                    Text(song?.title ?: "Detail", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    ) { p ->
        song?.let {
            Column(Modifier.fillMaxSize().padding(p).verticalScroll(rememberScrollState()).padding(16.dp)) {
                Image(painterResource(it.imageRes), null, modifier = Modifier.fillMaxWidth().height(260.dp).clip(RoundedCornerShape(16.dp)), contentScale = ContentScale.Crop)
                Row(Modifier.fillMaxWidth().padding(vertical = 20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(it.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text(it.year, style = MaterialTheme.typography.titleLarge, color = Color.Gray)
                }
                Text(stringResource(com.example.modul4compose.R.string.titlemean), fontWeight = FontWeight.Bold)
                Text(if (isId) it.meanId else it.meanEn, modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}