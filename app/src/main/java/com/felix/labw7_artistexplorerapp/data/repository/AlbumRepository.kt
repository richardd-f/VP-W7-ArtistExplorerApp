package com.felix.labw7_artistexplorerapp.data.repository

import com.felix.labw7_artistexplorerapp.data.model.dto.AlbumDto
import com.felix.labw7_artistexplorerapp.data.model.dto.TrackDto
import com.felix.labw7_artistexplorerapp.data.model.results.Result
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {

    fun searchAlbumsByArtist(artistName: String): Flow<Result<List<AlbumDto>>>

    fun getAlbumTracks(albumId: String): Flow<Result<List<TrackDto>>>

    fun getAlbumDetails(albumId: String): Flow<Result<AlbumDto>>
}
