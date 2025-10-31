package com.felix.labw7_artistexplorerapp.data.model

data class AlbumLocal(
    val id: Int,
    val title: String,
    val year: Int,
    val genre: String,
    val description: String,
    val painterId: Int,
    val allTracks: List<TrackModel>
)