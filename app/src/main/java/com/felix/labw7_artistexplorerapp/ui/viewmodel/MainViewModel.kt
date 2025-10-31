package com.felix.labw7_artistexplorerapp.ui.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.felix.labw7_artistexplorerapp.R
import com.felix.labw7_artistexplorerapp.data.model.AlbumLocal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel(){
    private val _albumsList  = MutableStateFlow<List<AlbumLocal>>(listOf(
        AlbumLocal(1, "Sob Rocks", "2021 • Indie", R.drawable.john_denver),
        AlbumLocal(2, "New Light", "2018 • Indie", R.drawable.john_denver),
        AlbumLocal(3, "Paradise Valley", "2013 • Folk", R.drawable.john_denver),
        AlbumLocal(4, "Born and Raised", "2012 • Folk Rock", R.drawable.john_denver),
        AlbumLocal(5, "Continuum", "2006 • Pop Rock", R.drawable.john_denver),
        AlbumLocal(6, "Heavier Things", "2003 • Pop Rock", R.drawable.john_denver)
    ))

    val albumList : StateFlow<List<AlbumLocal>> = _albumsList.asStateFlow()
}