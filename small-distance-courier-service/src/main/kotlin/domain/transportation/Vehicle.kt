package domain.transportation

import domain.delivery.Package

data class Vehicle(
    val maxSpeed: Double,
    val maxWeight: Double,
    var packagesToDeliver: ArrayList<Package> = ArrayList(),
)