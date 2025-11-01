package com.felix.labw7_artistexplorerapp.di

import com.felix.labw7_artistexplorerapp.data.repository.AlbumRepository
import com.felix.labw7_artistexplorerapp.data.repository.AlbumRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAlbumRepository(
        impl: AlbumRepositoryImpl
    ): AlbumRepository
}