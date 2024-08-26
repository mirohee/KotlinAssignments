package Lecture1And2

fun main(args: Array<String>) {
    val c1 = Car()
    val c2 = Car(120.0)
    val c3 = Car(gasolineCapacity = 50.0)
    val c4 = Car(120.0, 50.0)
    val c5 = Car(maxSpeed = 120.0, gasolineCapacity = 50.0)

    c1.fillTank()

    for(i in 1..20) {
        c1.accelerate()
    }

    while(c1.speed > 0) {
        c1.decelerate()
    }
}