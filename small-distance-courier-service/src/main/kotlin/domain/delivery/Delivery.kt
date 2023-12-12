package domain.delivery

data class Delivery(
    var baseCost: Double = 0.0,
    var packages: ArrayList<Package> = ArrayList()
)
