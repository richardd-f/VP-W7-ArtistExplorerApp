package com.felix.labw7_artistexplorerapp.data.model

data class AlbumLocal(
    val id: Int,
    val title: String,
    val year: Int,
    val genre: String,
    val artistName: String,
    val description: String,
    val imageUrl: String,
    val allTracks: List<TrackModel>
)