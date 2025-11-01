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
                title = "Sob Rock",
                year = 2021,
                genre = "Pop Rock",
                description = "John Mayer’s nostalgic 80s-inspired album featuring tracks like 'Last Train Home'.",
                imageUrl = "https://r2.theaudiodb.com/images/media/album/thumb/0qkd2g1639403124.jpg",
                allTracks = listOf(
                    TrackModel(1, "Last Train Home", "3:07"),
                    TrackModel(2, "Shouldn't Matter but It Does", "3:56"),
                    TrackModel(3, "Wild Blue", "4:12")
                ),
                artistName = "John Denver"
            ),
            AlbumLocal(
                id = 2,
                title = "New Light",
                year = 2018,
                genre = "Indie Pop",
                description = "A bright and soulful single that marked a new sound direction for Mayer.",
                imageUrl = "https://upload.wikimedia.org/wikipedia/en/5/59/John_Mayer_-_New_Light.png",
                allTracks = listOf(
                    TrackModel(1, "New Light", "3:39")
                ),
                artistName = "John Denver"
            ),
            AlbumLocal(
                id = 3,
                title = "Paradise Valley",
                year = 2013,
                genre = "Folk",
                description = "A country-inspired album that reflects Mayer’s Montana years.",
                imageUrl = "https://upload.wikimedia.org/wikipedia/en/b/b3/John_Mayer_-_Paradise_Valley.png",
                allTracks = listOf(
                    TrackModel(1, "Who You Love (feat. Katy Perry)", "4:12"),
                    TrackModel(2, "Paper Doll", "4:19"),
                    TrackModel(3, "Wildfire", "4:10")
                ),
                artistName = "John Denver"
            ),
            AlbumLocal(
                id = 4,
                title = "Born and Raised",
                year = 2012,
                genre = "Folk Rock",
                description = "A reflective and rootsy album exploring Americana and self-discovery.",
                imageUrl = "https://upload.wikimedia.org/wikipedia/en/d/d2/John_Mayer_-_Born_and_Raised.png",
                allTracks = listOf(
                    TrackModel(1, "Queen of California", "4:09"),
                    TrackModel(2, "Shadow Days", "3:51"),
                    TrackModel(3, "Born and Raised", "4:49")
                ),
                artistName = "John Denver"
            ),
            AlbumLocal(
                id = 5,
                title = "Continuum",
                year = 2006,
                genre = "Pop Rock",
                description = "A critically acclaimed album blending pop and blues, featuring 'Gravity' and 'Slow Dancing in a Burning Room'.",
                imageUrl = "https://upload.wikimedia.org/wikipedia/en/a/af/John_Mayer_-_Continuum.png",
                allTracks = listOf(
                    TrackModel(1, "Gravity", "4:05"),
                    TrackModel(2, "Vultures", "4:12"),
                    TrackModel(3, "Slow Dancing in a Burning Room", "4:02")
                ),
                artistName = "John Denver"
            ),
            AlbumLocal(
                id = 6,
                title = "Heavier Things",
                year = 2003,
                genre = "Pop Rock",
                description = "A mix of heartfelt songwriting and guitar-driven melodies.",
                imageUrl = "https://upload.wikimedia.org/wikipedia/en/4/4b/John_Mayer_-_Heavier_Things.png",
                allTracks = listOf(
                    TrackModel(1, "Clarity", "4:31"),
                    TrackModel(2, "Bigger Than My Body", "4:26"),
                    TrackModel(3, "Daughters", "3:58")
                ),
                artistName = "John Denver"
            )
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