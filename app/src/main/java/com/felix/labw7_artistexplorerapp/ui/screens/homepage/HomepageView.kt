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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.felix.labw7_artistexplorerapp.R
import com.felix.labw7_artistexplorerapp.data.model.AlbumLocal
import com.felix.labw7_artistexplorerapp.navigation.Screen
import com.felix.labw7_artistexplorerapp.ui.components.AlbumCard
import com.felix.labw7_artistexplorerapp.ui.viewmodel.MainViewModel

@Composable
fun Homepage(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
){
    HomepageContent(
        navController,
        albumsList = viewModel.albumList.collectAsState().value
    )
}

@Composable
fun HomepageContent(
    navController: NavController,
    albumsList: List<AlbumLocal>
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
                    painterId = album.painterId,
                    title = album.title,
                    subtitle = album.subtitle
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
            AlbumLocal(1, "Sob Rock", "2021 • Indie", R.drawable.john_denver),
            AlbumLocal(2, "New Light", "2018 • Indie", R.drawable.john_denver),
            AlbumLocal(3, "Paradise Valley", "2013 • Folk", R.drawable.john_denver),
            AlbumLocal(4, "Born and Raised", "2012 • Folk Rock", R.drawable.john_denver),
            AlbumLocal(5, "Continuum", "2006 • Pop Rock", R.drawable.john_denver),
            AlbumLocal(6, "Heavier Things", "2003 • Pop Rock", R.drawable.john_denver)
        )
    )
}