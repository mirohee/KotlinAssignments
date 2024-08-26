package org.example

class Major(val name: String) {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun stats(): Triple<Double, Double, Double> {
        val averages = students.map { it.weightedAverage() }
        return Triple(averages.minOrNull() ?: 0.0, averages.maxOrNull() ?: 0.0, averages.average())
    }

    fun stats(courseName: String): Triple<Double, Double, Double> {
        val averages = students.map { it.weightedAverage(courseName) }
        return Triple(averages.minOrNull() ?: 0.0, averages.maxOrNull() ?: 0.0, averages.average())
    }
}
