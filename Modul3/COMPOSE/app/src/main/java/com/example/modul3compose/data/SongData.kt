package com.example.modul3compose.data

import com.example.modul3compose.R
import com.example.modul3compose.model.Song

object SongData {
    val songLists = listOf(
        Song(
            id = 1,
            title = "Scott Street",
            year = "2017",
            meanId = "Lagu emosional tentang nostalgia dan perasaan terasing dari Phoebe Bridgers.",
            MeanEn = "An emotional song about nostalgia and feeling alienated by Phoebe Bridgers.",
            imageRes = R.drawable.scootstreet,
            url = "https://open.spotify.com/track/21uFPefbgeR3QLVJWATlrr?si=ab2c7ad661ad4168"
        ),
        Song(
            id = 2,
            title = "The Night We Met",
            year = "2015",
            meanId = "Balada melankolis dari Lord Huron tentang kerinduan untuk kembali ke masa lalu.",
            MeanEn = "A melancholy ballad by Lord Huron about longing to return to the past.",
            imageRes = R.drawable.thenightwemet,
            url = "https://open.spotify.com/track/3hRV0jL3vUpRrcy398teAU?si=bfd097b1bc824f91"
        ),
        Song(
            id = 3,
            title = "Hello",
            year = "2015",
            meanId = "Karya musik folk minimalis dari Ichiko Aoba dengan petikan gitar yang lembut.",
            MeanEn = "A folk rock song by Ichiko Aoba with a soft guitar.",
            imageRes = R.drawable.hello,
            url = "https://open.spotify.com/track/795bHfsDk7EC34IsLqaXfp?si=0a8eba42ee67448d"
        ),
        Song(
            id = 4,
            title = "Light Song",
            year = "2016",
            meanId = "Karya musik yang menenangkan dengan harmoni vokal yang indah.",
            MeanEn = "A soothing musical piece featuring beautiful vocal harmonies.",
            imageRes = R.drawable.lightsong,
            url = "https://open.spotify.com/track/4KXZ4MJKT0RYGhijfQ08Jw?si=9e08213f078c4a8f"
        ),
        Song(
            id = 5,
            title = "Childhood",
            year = "1995",
            meanId = "Lagu dari daniel.mp3 yang sangat personal tentang masa lalu saat kecil.",
            MeanEn = "A personal song about the past when I was a child.",
            imageRes = R.drawable.childhood,
            url = "https://open.spotify.com/track/06dDLXf4gPbE02gvH8E6G9?si=1d289d479e104445"
        )
    )
}