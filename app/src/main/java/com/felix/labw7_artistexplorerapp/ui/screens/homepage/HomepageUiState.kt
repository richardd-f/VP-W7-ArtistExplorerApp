package com.felix.labw7_artistexplorerapp.ui.screens.homepage

sealed class HomepageUiState {
    object Loading: HomepageUiState()
    object Success : HomepageUiState()
    class Error(val errorMsg: String) : HomepageUiState()
}