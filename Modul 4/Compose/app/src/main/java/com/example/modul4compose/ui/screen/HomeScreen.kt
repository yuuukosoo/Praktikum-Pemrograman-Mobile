package com.example.modul4compose.ui.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.modul4compose.model.Song
import com.example.modul4compose.viewmodel.SongViewModel
import com.example.modul4compose.R


@Composable
fun HomeScreen(navController: NavController, viewModel: SongViewModel) {
    val songList by viewModel.songs.collectAsState()
    val navigateToUrl by viewModel.navigateToUrl.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(navigateToUrl) {
        navigateToUrl?.let { url ->
            val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(url))
            context.startActivity(intent)
            viewModel.clearUrlNavigation()
        }
    }

    Scaffold(
        topBar = {
            Surface(color = Color.Black, shadowElevation = 4.dp) {
                Row(
                    modifier = Modifier.fillMaxWidth().statusBarsPadding().height(64.dp).padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(stringResource(R.string.app_name), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Default.Settings, null, tint = Color.White)
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            item {
                Text(stringResource(R.string.title_highlight), modifier = Modifier.padding(16.dp), fontWeight = FontWeight.SemiBold)
                LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(songList) { song ->
                        Card(Modifier.size(200.dp, 120.dp), shape = RoundedCornerShape(12.dp)) {
                            Image(painterResource(song.imageRes), null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                        }
                    }
                }
                Spacer(Modifier.height(24.dp))
            }
            items(songList) { song ->
                SongListItem(
                    song = song,
                    onBrowserClick = { viewModel.onBrowserClicked(song.url) },
                    onDetailClick = {
                        viewModel.onDetailClicked(song)
                        navController.navigate("detail/${song.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun SongListItem(song: Song, onBrowserClick: () -> Unit, onDetailClick: () -> Unit) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth().height(175.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A1A))
    ) {
        Row(Modifier.padding(12.dp).fillMaxSize()) {
            Image(
                painterResource(song.imageRes), null,
                modifier = Modifier.size(100.dp, 150.dp).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.padding(start = 12.dp).fillMaxHeight()) {
                Text(song.title, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(song.meanEn, style = MaterialTheme.typography.bodySmall, color = Color.LightGray, maxLines = 2)
                Spacer(Modifier.weight(1f))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    val blue = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                    Button(onClick = onBrowserClick, colors = blue, modifier = Modifier.height(36.dp)) {
                        Text(stringResource(R.string.btn_browser), fontSize = 11.sp)
                    }
                    Spacer(Modifier.width(8.dp))
                    Button(onClick = onDetailClick, colors = blue, modifier = Modifier.height(36.dp)) {
                        Text(stringResource(R.string.btn_detail), fontSize = 11.sp)
                    }
                }
            }
        }
    }
}