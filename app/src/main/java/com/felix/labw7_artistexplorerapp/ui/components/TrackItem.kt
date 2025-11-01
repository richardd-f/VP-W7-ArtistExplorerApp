package com.felix.labw7_artistexplorerapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felix.labw7_artistexplorerapp.data.model.TrackModel

@Composable
fun TrackItem(track: TrackModel, number: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp), // Space between each track row
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 1. Track Number Box
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(
                    color = Color(0xFF8F8A6C),
                    shape = RoundedCornerShape(6.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number.toString(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }

        // 2. Track Name
        Text(
            text = track.name,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .weight(1f) // This pushes the duration to the end
                .padding(start = 16.dp, end = 8.dp)
        )

        // 3. Track Duration
        Text(
            text = track.duration,
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}