package advanced.generic

import study.ch10.Animal

fun main() {
    val goldFishCage = Cage<GoldFish>()
    goldFishCage.put(GoldFish())
    val goldFish = goldFishCage.getFirst()

    val fishCage = Cage<Fish>()
    fishCage.put(Carp())
    fishCage.moveFrom(goldFishCage)
}

class Cage<T> where T : Animal, T : Comparable<T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T {
        return animals.first()
    }

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun moveFrom(otherCage: Cage<out T>) {
        this.animals.addAll(otherCage.animals)
    }

    fun sorted(): List<T> {
        return this.animals.sorted()
    }
}

abstract class Fish : Animal("", 0), Comparable<Fish> {
    override fun move() {
        TODO("Not yet implemented")
    }
}

class GoldFish : Fish() {
    override fun compareTo(other: Fish): Int {
        TODO("Not yet implemented")
    }
}

class Carp : Fish() {
    override fun compareTo(other: Fish): Int {
        TODO("Not yet implemented")
    }
}
