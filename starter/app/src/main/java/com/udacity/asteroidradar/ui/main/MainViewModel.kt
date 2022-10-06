package com.udacity.asteroidradar.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidFilter
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : ViewModel() {
    private val asteroidRepository = AsteroidRepository(getDatabase(app))
    private val filter = MutableLiveData(AsteroidFilter.SAVED)
    var asteroidsList = filter.map {
        when (it) {
            AsteroidFilter.TODAY -> asteroidRepository.todayAsteroids
            AsteroidFilter.WEEK -> asteroidRepository.weekAsteroids
            else -> asteroidRepository.asteroids
        }
    }

    val todayImage = asteroidRepository.todayImage

    init {
        viewModelScope.launch {
            try {
                asteroidRepository.refreshAsteroid()
                asteroidRepository.getImageOfTheDay()
            } catch (e: Exception) {
                println("Exception refreshing data: $e.message")
            }
        }
    }

    fun updateFilter(filter: AsteroidFilter) {
        this.filter.value = filter
    }
}