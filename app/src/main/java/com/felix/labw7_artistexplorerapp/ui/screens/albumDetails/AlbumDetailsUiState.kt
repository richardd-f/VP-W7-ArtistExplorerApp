package com.felix.labw7_artistexplorerapp.ui.screens.albumDetails

import com.felix.labw7_artistexplorerapp.data.model.AlbumLocal

sealed class AlbumDetailsUiState {
    object Loading : AlbumDetailsUiState()
    object Success : AlbumDetailsUiState()
    data class Error(val message: String) : AlbumDetailsUiState()
}