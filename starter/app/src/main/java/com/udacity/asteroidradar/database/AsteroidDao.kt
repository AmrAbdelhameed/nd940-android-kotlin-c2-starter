package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDao {
    @Query("SELECT * FROM databaseAsteroid ORDER BY closeApproachDate ASC")
    fun getAsteroids(): LiveData<List<DatabaseAsteroid>>

    @Query("SELECT * FROM databaseAsteroid WHERE closeApproachDate >= :startDate AND closeApproachDate <= :endDate ORDER BY closeApproachDate ASC")
    fun getAsteroidsByDates(startDate: String, endDate: String): LiveData<List<DatabaseAsteroid>>

    @Query("DELETE FROM databaseAsteroid WHERE closeApproachDate < :today")
    fun deleteAsteroidsBeforeToday(today: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseAsteroid)
}