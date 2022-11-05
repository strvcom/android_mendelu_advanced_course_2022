package com.strv.mendelutesting.data

import kotlinx.coroutines.delay
import kotlin.random.Random

object ServerMock {

    suspend fun getCurrentWeather(): CurrentWeather {
        val responseTime = Random.nextInt(from = 500, until = 1000).toLong()
        delay(responseTime)

        return CurrentWeather(
            city = mockCity(),
            temperature = mockTemperature(),
            windDirection = mockWindDirection()
        )
    }

    private fun mockCity(): String = listOf("Brno").random()

    private fun mockTemperature(): Double = Random.nextDouble(-7.0, 24.0)

    private fun mockWindDirection(): Int = Random.nextInt(0, 360)

}