package com.felix.labw7_artistexplorerapp.data.network

import com.felix.labw7_artistexplorerapp.data.model.dto.AlbumApiResponse
import com.felix.labw7_artistexplorerapp.data.model.dto.TrackApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AudioDbApiService {

    /**
     * Searches for all albums by a singer's name.
     */
    @GET("searchalbum.php")
    suspend fun searchAlbumsByArtist(
        @Query("s") artistName: String
    ): AlbumApiResponse

    /**
     * Gets the details for a specific album by its ID.
     * Note: This API seems to return the same 'album' list structure,
     */
    @GET("album.php")
    suspend fun getAlbumDetails(
        @Query("m") albumId: String
    ): AlbumApiResponse

    /**
     * Gets all tracks for a specific album by its ID.
     */
    @GET("track.php")
    suspend fun getAlbumTracks(
        @Query("m") albumId: String
    ): TrackApiResponse
}
