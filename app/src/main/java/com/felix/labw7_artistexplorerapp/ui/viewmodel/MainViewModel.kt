package com.felix.labw7_artistexplorerapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.labw7_artistexplorerapp.data.model.AlbumLocal
import com.felix.labw7_artistexplorerapp.data.model.TrackModel
import com.felix.labw7_artistexplorerapp.data.model.results.Result
import com.felix.labw7_artistexplorerapp.data.repository.AlbumRepository
import com.felix.labw7_artistexplorerapp.ui.screens.albumDetails.AlbumDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AlbumRepository
) : ViewModel(){
    // Private MutableStateFlow
    // Dummy data
    private val _albumsList = MutableStateFlow<List<AlbumLocal>>(
        listOf(
            AlbumLocal(
                id = 1,
                title = "...",
                year = 0,
                genre = "...",
                description = "",
                imageUrl = "",
                artistName = "",
                allTracks = emptyList()
            ),
            AlbumLocal(
                id = 1,
                title = "...",
                year = 0,
                genre = "...",
                description = "",
                imageUrl = "",
                artistName = "",
                allTracks = emptyList()
            ),
        )
    )
    val albumList : StateFlow<List<AlbumLocal>> = _albumsList.asStateFlow()

    private val _selectedAlbum = MutableStateFlow<AlbumLocal?>(null)
    val selectedAlbum = _selectedAlbum.asStateFlow()

    private val _albumDetailsUiState = MutableStateFlow(AlbumDetailsUiState())
    val albumDetailsUiState: StateFlow<AlbumDetailsUiState> = _albumDetailsUiState.asStateFlow()

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            repository.searchAlbumsByArtist("John Denver")
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            val dtoList = result.data ?: emptyList()

                            // Convert List<AlbumDto> to List<AlbumLocal>
                            val localList = dtoList.map { albumDto ->
                                AlbumLocal(
                                    id = albumDto.albumId?.toIntOrNull() ?: 0,
                                    title = albumDto.albumName ?: "Unknown Album",
                                    year = albumDto.yearReleased?.toIntOrNull() ?: 0,
                                    genre = albumDto.genre ?: "Unknown",
                                    artistName = albumDto.artistName ?: "Unknown Artist",
                                    description = albumDto.descriptionEN ?: "No description available",
                                    imageUrl = albumDto.albumThumbUrl ?: "",
                                    allTracks = emptyList() // dont need this for now, it will be used on the album details
                                )
                            }

                            _albumsList.value = localList
                        }

                        is Result.Error -> {
                            println("Error fetching albums: ${result.message}")
                            _albumsList.value = emptyList()
                        }

                        is Result.Loading -> {
                            println("Loading albums...")
                        }
                    }
                }
        }
    }


    fun setAlbum(albumId: Int) {
        val albumIdStr = albumId.toString()

        val albumDetailsFlow = repository.getAlbumDetails(albumIdStr)
        val albumTracksFlow = repository.getAlbumTracks(albumIdStr)

        viewModelScope.launch {
            combine(albumDetailsFlow, albumTracksFlow) { albumResult, trackResult ->

                when {
                    albumResult is Result.Loading || trackResult is Result.Loading -> {
                        _albumDetailsUiState.value = AlbumDetailsUiState(isLoading = true)
                        null
                    }

                    albumResult is Result.Error -> {
                        _albumDetailsUiState.value = AlbumDetailsUiState(errorMessage = albumResult.message)
                        null
                    }

                    trackResult is Result.Error -> {
                        _albumDetailsUiState.value = AlbumDetailsUiState(errorMessage = trackResult.message)
                        null
                    }

                    albumResult is Result.Success && trackResult is Result.Success -> {
                        val albumDto = albumResult.data
                        val trackDtoList = trackResult.data

                        val trackModels = (trackDtoList ?: emptyList()).mapIndexed { index, trackDto ->
                            TrackModel(
                                id = index + 1,
                                name = trackDto.trackName,
                                duration = trackDto.trackNumber ?: "-"
                            )
                        }

                        val albumLocal = AlbumLocal(
                            id = albumDto?.albumId?.toIntOrNull() ?: 0,
                            title = albumDto?.albumName ?: "Unknown Album",
                            year = albumDto?.yearReleased?.toIntOrNull() ?: 0,
                            genre = albumDto?.genre ?: "Unknown",
                            artistName = albumDto?.artistName ?: "Unknown Artist",
                            description = albumDto?.descriptionEN ?: "No description available",
                            imageUrl = albumDto?.albumThumbUrl ?: "",
                            allTracks = trackModels
                        )

                        _albumDetailsUiState.value = AlbumDetailsUiState(album = albumLocal)
                        albumLocal
                    }

                    else -> null
                }
            }.collect { albumLocal ->
                if (albumLocal != null) _selectedAlbum.value = albumLocal
            }
        }
    }


}