package com.felix.labw7_artistexplorerapp.di

import com.felix.labw7_artistexplorerapp.data.network.AudioDbApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // This is your new single Base URL
    private const val BASE_URL = "https://www.theaudiodb.com/api/v1/json/123/"

    /**
     * Provides a HttpLoggingInterceptor for debugging network requests.
     * This is a best practice for development.
     */
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * Provides the OkHttpClient, including the Logging Interceptor.
     * We removed the old Auth Interceptor as it's not needed.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // Logs requests/responses
            .build()
    }

    /**
     * Provides a Gson instance for Retrofit.
     */
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    /**
     * Provides the Retrofit instance, configured with the base URL,
     * the OkHttpClient, and the Gson converter.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    /**
     * Provides the ApiService implementation created by Retrofit.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): AudioDbApiService {
        return retrofit.create(AudioDbApiService::class.java)
    }
}