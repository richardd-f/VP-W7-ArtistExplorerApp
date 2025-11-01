package com.felix.labw7_artistexplorerapp.ui.screens.homepage

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.felix.labw7_artistexplorerapp.R
import com.felix.labw7_artistexplorerapp.data.model.AlbumLocal
import com.felix.labw7_artistexplorerapp.data.model.TrackModel
import com.felix.labw7_artistexplorerapp.navigation.Screen
import com.felix.labw7_artistexplorerapp.ui.components.AlbumCard
import com.felix.labw7_artistexplorerapp.ui.viewmodel.MainViewModel

@Composable
fun Homepage(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
){
    HomepageContent(
        navController,
        albumsList = viewModel.albumList.collectAsState().value,
        homepageUiState = viewModel.homepageUiState.collectAsState().value,
        onCardClick = {
            viewModel.setAlbum(it)
            navController.navigate(Screen.AlbumDetails.route)
        }
    )
}

@Composable
fun HomepageContent(
    navController: NavController,
    albumsList: List<AlbumLocal>,
    homepageUiState: HomepageUiState,
    onCardClick: (albumId:Int) -> Unit
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
                text = "John Denver",
                color = Color(0xFFA29C95),
                fontSize = 20.sp
            )
        }

        // John Denver Image
        Spacer(Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .size(340.dp),
            shape = RoundedCornerShape(12.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.john_denver),
                    contentDescription = "John Denver Portrait",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 20.dp, start = 20.dp)
                ) {
                    Text(
                        text = "John Denver",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Indie",
                        color = Color(0xFFE0E0E0),
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        Text(
            text = "Albums",
            color = Color(0xFFA29C95),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp)
        )

        Spacer(Modifier.height(16.dp))

        // --- Lazy Grid for Albums ---
        if(homepageUiState is HomepageUiState.Loading){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Loading...",
                    color = Color(0xFFA29C95)
                )
            }
        }
        else if (homepageUiState is HomepageUiState.Error){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "An error is occured",
                        color = Color(0xFFA29C95),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = homepageUiState.errorMsg,
                        color = Color(0xFFA29C95),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),

            ) {
                items(albumsList) { album ->
                    AlbumCard(
                        album = album,
                        onCardClick = onCardClick
                    )
                }
            }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomepagePreview(){
    HomepageContent(
        navController = rememberNavController(),
        albumsList = listOf(
            AlbumLocal(
                id = 1,
                title = "Sob Rocks",
                year = 2021,
                genre = "Pop Rock",
                description = "John Mayer’s nostalgic 80s-inspired album featuring tracks like 'Last Train Home'.",
                imageUrl = "https://r2.theaudiodb.com/images/media/album/thumb/0qkd2g1639403124.jpg",
                allTracks = listOf(
                    TrackModel(1, "Last Train Home", "3:07"),
                    TrackModel(2, "Shouldn't Matter but It Does", "3:56"),
                    TrackModel(3, "Wild Blue", "4:12")
                ),
                artistName = "John Mayer"
            ),
            AlbumLocal(
                id = 2,
                title = "New Light",
                year = 2018,
                genre = "Indie Pop",
                description = "A groovy single blending pop and funk, showing Mayer’s lighter side.",
                imageUrl = "https://r2.theaudiodb.com/images/media/album/thumb/qyyvtr1626787588.jpg",
                allTracks = listOf(
                    TrackModel(1, "New Light", "3:39")
                ),
                artistName = "John Mayer"
            ),
            AlbumLocal(
                id = 3,
                title = "Paradise Valley",
                year = 2013,
                genre = "Folk",
                description = "A reflective album with blues and folk elements, featuring ‘Paper Doll’.",
                imageUrl = "https://r2.theaudiodb.com/images/media/album/thumb/rryrrq1358986965.jpg",
                allTracks = listOf(
                    TrackModel(1, "Wildfire", "4:11"),
                    TrackModel(2, "Paper Doll", "4:20"),
                    TrackModel(3, "Who You Love", "4:09")
                ),
                artistName = "John Mayer"
            ),
            AlbumLocal(
                id = 4,
                title = "Born and Raised",
                year = 2012,
                genre = "Folk Rock",
                description = "A warm, Americana-inspired album reflecting self-discovery and growth.",
                imageUrl = "https://r2.theaudiodb.com/images/media/album/thumb/rtuyqt1358986939.jpg",
                allTracks = listOf(
                    TrackModel(1, "Queen of California", "4:10"),
                    TrackModel(2, "The Age of Worry", "2:38"),
                    TrackModel(3, "Born and Raised", "4:49")
                ),
                artistName = "John Mayer"
            ),
            AlbumLocal(
                id = 5,
                title = "Continuum",
                year = 2006,
                genre = "Pop Rock",
                description = "A soulful album blending blues and pop, featuring hits like ‘Gravity’.",
                imageUrl = "https://r2.theaudiodb.com/images/media/album/thumb/vtwwrs1347970831.jpg",
                allTracks = listOf(
                    TrackModel(1, "Waiting on the World to Change", "3:50"),
                    TrackModel(2, "Belief", "4:02"),
                    TrackModel(3, "Gravity", "4:05")
                ),
                artistName = "John Mayer"
            ),
            AlbumLocal(
                id = 6,
                title = "Heavier Things",
                year = 2003,
                genre = "Pop Rock",
                description = "A more mature and introspective follow-up to Mayer’s early pop sound.",
                imageUrl = "https://r2.theaudiodb.com/images/media/album/thumb/qyyrrt1347970718.jpg",
                allTracks = listOf(
                    TrackModel(1, "Clarity", "4:28"),
                    TrackModel(2, "Bigger Than My Body", "4:26"),
                    TrackModel(3, "Daughters", "3:58")
                ),
                artistName = "John Mayer"
            )
        ),
        homepageUiState = HomepageUiState.Success,
        onCardClick = {}
    )
}