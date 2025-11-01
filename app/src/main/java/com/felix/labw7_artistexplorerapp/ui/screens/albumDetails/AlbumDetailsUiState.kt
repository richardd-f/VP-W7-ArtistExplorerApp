package com.felix.labw7_artistexplorerapp.ui.screens.albumDetails

import com.felix.labw7_artistexplorerapp.data.model.AlbumLocal

data class AlbumDetailsUiState(
    val isLoading: Boolean = false,
    val album: AlbumLocal? = null,
    val errorMessage: String? = null
)