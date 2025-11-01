package com.felix.labw7_artistexplorerapp.data.model.dto

import com.google.gson.annotations.SerializedName

data class TrackApiResponse(
    @SerializedName("track") val trackList: List<TrackDto>?
)
data class TrackDto(
    @SerializedName("idTrack") val trackId: String,
    @SerializedName("strTrack") val trackName: String,
    @SerializedName("strAlbum") val albumName: String,
    @SerializedName("intTrackNumber") val trackNumber: String?
)
