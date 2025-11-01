package com.felix.labw7_artistexplorerapp.data.repository

import android.net.http.HttpException
import com.felix.labw7_artistexplorerapp.data.model.dto.AlbumDto
import com.felix.labw7_artistexplorerapp.data.model.dto.TrackDto
import com.felix.labw7_artistexplorerapp.data.network.AudioDbApiService
import com.felix.labw7_artistexplorerapp.data.model.results.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val api: AudioDbApiService
) : AlbumRepository {

    override fun searchAlbumsByArtist(artistName: String): Flow<Result<List<AlbumDto>>> = flow {
        // Emit Loading state first
        emit(Result.Loading())

        try {
            // Make the network call
            val response = api.searchAlbumsByArtist(artistName)

            // Emit Success with the data
            emit(Result.Success(response.albumList ?: emptyList()))

        } catch (e: HttpException) {
            // Emit Error for non-2xx HTTP responses
            emit(Result.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            // Emit Error for network issues (e.g., no internet)
            emit(Result.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    override fun getAlbumTracks(albumId: String): Flow<Result<List<TrackDto>>> = flow {
        emit(Result.Loading())
        try {
            val response = api.getAlbumTracks(albumId)
            emit(Result.Success(response.trackList ?: emptyList()))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An unknown error occurred"))
        }
    }

    override fun getAlbumDetails(albumId: String): Flow<Result<AlbumDto>> = flow {
        emit(Result.Loading())

        try {
            val response = api.getAlbumDetails(albumId)

            val album = response.albumList?.firstOrNull()
            if (album != null) {
                emit(Result.Success(album))
            } else {
                emit(Result.Error("Album not found"))
            }

        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An unknown error occurred"))
        }
    }
}