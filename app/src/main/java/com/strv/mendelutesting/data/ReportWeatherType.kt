package com.strv.mendelutesting.data

enum class WeatherType(val displayName: String) {
	NOT_SELECTED("Not selected"),
	SUNNY("Sunny"),
	OVERCAST("Overcast"),
	RAINING("Raining"),
	STORM("Storm"),
	SNOWING("Snowing"),
	RAINING_FISH_AND_FROGS("Raining fish & frogs")
}

data class ReportWeatherType(
	val weatherType: WeatherType,
	val isSelected: Boolean
)
