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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.felix.labw7_artistexplorerapp.R
import com.felix.labw7_artistexplorerapp.data.model.AlbumLocal
import com.felix.labw7_artistexplorerapp.data.model.TrackModel
import com.felix.labw7_artistexplorerapp.navigation.Screen
import com.felix.labw7_artistexplorerapp.ui.components.AlbumCard
import com.felix.labw7_artistexplorerapp.ui.components.TrackItem
import com.felix.labw7_artistexplorerapp.ui.viewmodel.MainViewModel

@Composable
fun AlbumDetails(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
){
    AlbumDetailsContent(
        navController,
        albumDetailsUiState = viewModel.albumDetailsUiState.collectAsState().value,
        album = viewModel.selectedAlbum.collectAsState().value,
    )
}

@Composable
fun AlbumDetailsContent(
    navController: NavController,
    albumDetailsUiState: AlbumDetailsUiState,
    album: AlbumLocal?,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF282828)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (albumDetailsUiState is AlbumDetailsUiState.Loading) {
            item{
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Loading...",
                        color = Color(0xFFA29C95)
                    )
                }
            }
            // if loading
        } else if (albumDetailsUiState is AlbumDetailsUiState.Error) {
            // if error
            item{
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "An error is occured ${albumDetailsUiState.message}",
                        color = Color(0xFFA29C95)
                    )
                }
            }
        } else if (album != null) {
            // else mean no error and not loading
            item{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(Color(0xFF191E1E)),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 20.dp),
                        text = album.artistName,
                        color = Color(0xFFA29C95),
                        fontSize = 20.sp
                    )
                }

                Spacer(Modifier.height(20.dp))
                // Image Thumbnail
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1B1B1B)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column {
                        // Top Section: Image with Overlays
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp) // Fixed height for the image section
                        ) {
                            // Main Album Image (from URL)
                            AsyncImage(
                                model = album.imageUrl,
                                contentDescription = "${album.title} by ${album.artistName}",
                                modifier = Modifier.fillMaxWidth(),
                                contentScale = ContentScale.Crop,
                            )
                        }

                        Column(
                            modifier = Modifier.padding(16.dp) // Padding for the text content
                        ) {
                            // Album Title
                            Text(
                                text = album.title,
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(4.dp))

                            // Year and Genre
                            Text(
                                text = "${album.year} • ${album.genre}",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                            Spacer(Modifier.height(12.dp))

                            // Description
                            Text(
                                text = album.description,
                                color = Color.LightGray,
                                fontSize = 14.sp,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }

                //
                // "Tracks" Title
                Text(
                    text = "Tracks",
                    color = Color(0xFFD4AF37), // A gold-like color from your image
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(16.dp))
            }

            itemsIndexed(album.allTracks) { index, track ->
                Column (
                    Modifier.padding(horizontal = 20.dp)
                ) {
                    TrackItem(track = track, number = index+1)
                }
            }
            // LazyColumn for the track list
//        Column (
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 20.dp)
//        ) {
//        }
        } else {
            item{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No album details available", color = Color.Gray)
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
        album = AlbumLocal(
            id = 1,
            title = "Sob Rock",
            year = 2021,
            genre = "Pop Rock",
            description = "halo ini adlaha deskripsi, halo ini adlaha deskripsi, halo ini adlaha deskripsi, halo ini adlaha deskripsihalo ini adlaha deskripsi, halo ini adlaha deskripsi",
            imageUrl = "https://r2.theaudiodb.com/images/media/album/thumb/0qkd2g1639403124.jpg",
            allTracks = listOf(
                TrackModel(1, "Last Train Home", "3:07"),
                TrackModel(1, "Last Train Home", "3:07"),
                TrackModel(1, "Last Train Home", "3:07"),
                TrackModel(1, "Last Train Home", "3:07"),
                TrackModel(1, "Last Train Home", "3:07"),
                TrackModel(1, "Last Train Home", "3:07"),
                TrackModel(1, "Last Train Home", "3:07"),

                ),
            artistName = "John Denver"
        ),
        albumDetailsUiState = AlbumDetailsUiState.Success
    )
}
