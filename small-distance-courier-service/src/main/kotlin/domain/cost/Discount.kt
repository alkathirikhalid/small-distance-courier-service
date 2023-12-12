package domain.cost

class Discount {
    fun calculateDiscount(packageOffer: Double, packageCost: Double): Double {
        return (packageOffer / 100) * packageCost
    }
}