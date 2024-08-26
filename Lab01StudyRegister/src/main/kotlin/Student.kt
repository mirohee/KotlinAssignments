package org.example

class Student(name: String, age: Int) : Human(name, age) {
    val courses = mutableListOf<CourseRecord>()

    fun addCourse(course: CourseRecord) {
        courses.add(course)
    }

    fun weightedAverage(): Double {
        val totalCredits = courses.sumOf { it.credits }
        return if (totalCredits == 0) 0.0 else courses.sumOf { it.grade * it.credits } / totalCredits
    }

    fun weightedAverage(courseName: String): Double {
        val filteredCourses = courses.filter { it.name == courseName }
        val totalCredits = filteredCourses.sumOf { it.credits }
        return if (totalCredits == 0) 0.0 else filteredCourses.sumOf { it.grade * it.credits } / totalCredits
    }

    fun weightedAverage(year: Int): Double {
        val filteredCourses = courses.filter { it.yearCompleted == year }
        val totalCredits = filteredCourses.sumOf { it.credits }
        return if (totalCredits == 0) 0.0 else filteredCourses.sumOf { it.grade * it.credits } / totalCredits
    }

    fun minMaxGrades(): Pair<Double, Double> {
        val grades = courses.map { it.grade }
        return Pair(grades.minOrNull() ?: 0.0, grades.maxOrNull() ?: 0.0)
    }
}
