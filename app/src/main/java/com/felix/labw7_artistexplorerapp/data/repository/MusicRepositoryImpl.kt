package com.felix.labw7_artistexplorerapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//class WeatherRepositoryImpl @Inject constructor(
//    private val apiService: ApiService
//) : WeatherRepository {
//
//    override fun getWeather(city: String): Flow<Result<WeatherApiResponse>> = flow {
//        try {
//            val response = apiService.getWeather(city)
//            emit(Result.success(response))
//        } catch (e: Exception) {
//            emit(Result.failure(e))
//        }
//    }
//}