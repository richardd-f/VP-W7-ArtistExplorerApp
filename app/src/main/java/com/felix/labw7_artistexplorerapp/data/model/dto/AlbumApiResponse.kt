package com.felix.labw7_artistexplorerapp.data.model.dto

import com.google.gson.annotations.SerializedName

data class AlbumApiResponse(
    @SerializedName("album") val albumList: List<AlbumDto>?
)

data class AlbumDto(
    @SerializedName("idAlbum") val albumId: String,
    @SerializedName("idArtist") val artistId: String,
    @SerializedName("strAlbum") val albumName: String,
    @SerializedName("strArtist") val artistName: String,
    @SerializedName("intYearReleased") val yearReleased: String?,
    @SerializedName("strGenre") val genre: String?,
    @SerializedName("strAlbumThumb") val albumThumbUrl: String?,
    @SerializedName("strDescriptionEN") val descriptionEN: String?
)
