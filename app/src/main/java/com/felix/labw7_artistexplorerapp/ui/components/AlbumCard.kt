package com.felix.labw7_artistexplorerapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlbumCard(
    modifier: Modifier = Modifier,
    painterId: Int,
    title: String,
    subtitle: String
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
            Image(
                painter = painterResource(id = painterId),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f), // Makes the image a perfect square
                contentScale = ContentScale.Crop // Ensures the image fills the space
            )

            // Text Content
            Column(
                modifier = Modifier.padding(12.dp) // Padding for the text area
            ) {
                Text(
                    text = title,
                    color = Color(0xFFA29C95),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    maxLines = 1 // Ensures title doesn't wrap to 2 lines
                )
                Spacer(Modifier.height(4.dp)) // Small space between texts
                Text(
                    text = subtitle,
                    color = Color(0xFFA29C95),
                    fontSize = 14.sp,
                    maxLines = 1
                )
            }
        }
    }
}