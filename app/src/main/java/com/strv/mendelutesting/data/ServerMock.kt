package com.strv.mendelutesting.data

import kotlinx.coroutines.delay
import kotlin.random.Random

object ServerMock {

    suspend fun getCurrentWeather(): CurrentWeather {
        val randomGenerator = Random(System.currentTimeMillis())
        val responseTime = randomGenerator.nextInt(from = 500, until = 1000).toLong()
        delay(responseTime)

        return CurrentWeather(
            city = mockCity(),
            temperature = mockTemperature(randomGenerator),
            windDirection = mockWindDirection(randomGenerator)
        )
    }

    private fun mockCity(): String = listOf("Brno").random()

    private fun mockTemperature(generator: Random): Double = generator.nextDouble(-7.0, 24.0)

    private fun mockWindDirection(generator: Random): Int = generator.nextInt(0, 360)

}