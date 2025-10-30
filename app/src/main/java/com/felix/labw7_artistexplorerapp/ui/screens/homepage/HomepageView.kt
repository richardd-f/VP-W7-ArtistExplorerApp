package com.felix.labw7_artistexplorerapp.ui.screens.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.felix.labw7_artistexplorerapp.navigation.Screen

@Composable
fun Homepage(
    navController: NavController
){
    HomepageContent(
        navController
    )
}

@Composable
fun HomepageContent(
    navController: NavController
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Homepage")
        Button(
            onClick = {navController.navigate(Screen.AlbumDetails.route)}
        ) {
            Text("To Album Details")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomepagePreview(){
    HomepageContent(navController = rememberNavController())
}