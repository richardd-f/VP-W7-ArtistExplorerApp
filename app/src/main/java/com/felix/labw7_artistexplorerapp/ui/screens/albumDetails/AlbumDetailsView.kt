package com.felix.labw7_artistexplorerapp.ui.screens.albumDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.felix.labw7_artistexplorerapp.R
import com.felix.labw7_artistexplorerapp.navigation.Screen
import com.felix.labw7_artistexplorerapp.ui.components.AlbumCard
import com.felix.labw7_artistexplorerapp.ui.viewmodel.MainViewModel

@Composable
fun AlbumDetails(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
){
    AlbumDetailsContent(
        navController
    )
}

@Composable
fun AlbumDetailsContent(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF282828)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color(0xFF191E1E)),
            contentAlignment = Alignment.BottomCenter
        ){
            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "John Mayer",
                color = Color(0xFFA29C95),
                fontSize = 20.sp
            )
        }

        // John Denver Image
        Spacer(Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth() // Makes the card fill the width
                .padding(16.dp), // Padding around the whole card
            shape = RoundedCornerShape(12.dp), // More rounded corners
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1B1B1B) // Darker background for the card
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // Subtle shadow
        ) {
            Column {
                // Top Section: Image with Overlays
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp) // Fixed height for the image section
                ) {

                    // !! IMPORTANT: Replace with your data
                    val artistName = "John Mayer"
                    val albumName = "Sob Rock"
                    // This is the URL for the image
                    val imageUrl =
                        "https://r2.theaudiodb.com/images/media/album/thumb/0qkd2g1639403124.jpg"

                    // Main Album Image (from URL)
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "$albumName by $artistName",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop, // Crop to fill the bounds
                        // Optional: You can add a placeholder
                        // placeholder = painterResource(id = R.drawable.your_placeholder)
                    )

                    // Artist Name Overlay
                    Text(
                        text = artistName.uppercase(),
                        color = Color.White.copy(alpha = 0.8f), // Slightly transparent white
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .align(Alignment.TopStart) // Align to top-left
                            .padding(start = 12.dp, top = 12.dp)
                    )

                    // Album Name Overlay
                    Text(
                        text = albumName.uppercase(),
                        color = Color.White.copy(alpha = 0.8f), // Slightly transparent white
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .align(Alignment.TopEnd) // Align to top-right
                            .padding(end = 12.dp, top = 12.dp)
                    )
                }

                // Bottom Section: Album Details
                // !! Replace these with your data
                val yearReleased = "2021"
                val genre = "Indie"
                val description =
                    "Sob Rock is the eighth studio album by American singer-songwriter John Mayer, " +
                            "released on July 16, 2021, by Columbia Records. The single \"New Light\", " +
                            "released in May 2018, is included on the album..."

                Column(
                    modifier = Modifier.padding(16.dp) // Padding for the text content
                ) {
                    // Album Title
                    Text(
                        text = albumName,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(4.dp))

                    // Year and Genre
                    Text(
                        text = "$yearReleased • $genre",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Spacer(Modifier.height(12.dp))

                    // Description
                    Text(
                        text = description,
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        lineHeight = 20.sp // Improve readability for paragraphs
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlbumDetailsPreview(){
    AlbumDetailsContent(
        navController = rememberNavController(),

    )
}