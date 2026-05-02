package com.example.modul3compose.ui.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.core.net.toUri
import com.example.modul3compose.R
import com.example.modul3compose.data.SongData
import com.example.modul3compose.model.Song

@Composable
fun HomeScreen(navController: androidx.navigation.NavController) {
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
    ) { p ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(p)) {
            item {
                Text(stringResource(R.string.title_highlight), modifier = Modifier.padding(16.dp), fontWeight = FontWeight.SemiBold)
                LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(SongData.songLists) { song ->
                        Card(Modifier.size(200.dp, 120.dp), shape = RoundedCornerShape(12.dp)) {
                            Image(painterResource(song.imageRes), null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                        }
                    }
                }
                Spacer(Modifier.height(24.dp))
            }
            items(SongData.songLists) { song ->
                SongListItem(song) { navController.navigate("detail/${song.id}") }
            }
        }
    }
}

@Composable
fun SongListItem(song: Song, onDetail: () -> Unit) {
    val ctx = LocalContext.current
    val config = LocalConfiguration.current

    val lang = config.locales.get(0).language
    val isId = lang == "in" || lang == "id"

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
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(song.title, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                    Text(song.year, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
                Text(
                    text = if (isId) song.meanId else song.MeanEn,
                    style = MaterialTheme.typography.bodySmall, color = Color.LightGray,
                    maxLines = 2, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 4.dp), lineHeight = 16.sp
                )
                Spacer(Modifier.weight(1f))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    val blue = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                    Button(onClick = { ctx.startActivity(Intent(Intent.ACTION_VIEW, song.url.toUri())) }, colors = blue, modifier = Modifier.height(36.dp)) {
                        Text(stringResource(R.string.btn_browser), fontSize = 11.sp)
                    }
                    Spacer(Modifier.width(8.dp))
                    Button(onClick = onDetail, colors = blue, modifier = Modifier.height(36.dp)) {
                        Text(stringResource(R.string.btn_detail), fontSize = 11.sp)
                    }
                }
            }
        }
    }
}