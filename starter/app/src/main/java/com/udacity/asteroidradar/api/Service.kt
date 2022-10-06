package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.models.PictureOfDay
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidService {
    @GET("/neo/rest/v1/feed")
    fun getAsteroid(
        @Query("start_date") start: String,
        @Query("end_date") end: String,
        @Query("api_key") api: String
    ): Deferred<String>

    @GET("/planetary/apod")
    fun getImageOfTheDay(
        @Query("api_key") api: String
    ): Deferred<PictureOfDay>
}