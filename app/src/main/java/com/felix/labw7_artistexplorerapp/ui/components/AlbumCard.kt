package com.felix.labw7_artistexplorerapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.felix.labw7_artistexplorerapp.data.model.AlbumLocal

@Composable
fun AlbumCard(
    modifier: Modifier = Modifier,
    album: AlbumLocal
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0x4FA29C95)),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color(0xFF191E1E)
        )
    ) {
        Column{
            // Album Image
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(album.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = album.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )

            // Text Content
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = album.title,
                    color = Color(0xFFA29C95),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    maxLines = 1
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "${album.year} • ${album.genre}",
                    color = Color(0xFFA29C95),
                    fontSize = 14.sp,
                    maxLines = 1
                )
            }
        }
    }
}