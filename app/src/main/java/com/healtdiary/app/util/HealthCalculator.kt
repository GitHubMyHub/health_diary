package com.healtdiary.app.util

class HealthCalculator {

    fun getSystol(systol: Int, isType: Boolean = false): String {

        var color = ""
        var type = ""

        when {
            systol < 105 -> {
                // Niedrig
                color = "#4464AD"
                type = "Niedrig"
            }
            systol in 105..119 -> {
                // Optimal
                color = "#F19007"
                type = "Optimal"
            }
            systol in 120..129 -> {
                // Normal
                color = "#F19007"
                type = "Normal"
            }
            systol in 130..139 -> {
                // Hochnormal
                color = "#F19007"
                type = "Hochnormal"
            }
            systol in 140..159 -> {
                // Hypertonie Grad 1
                color = "#ED363B"
                type = "Hypertonie Grad 1"
            }
            systol in 160..179 -> {
                // Hypertonie Grad 2
                color = "#ED363B"
                type = "Hypertonie Grad 2"
            }
            systol >= 180 -> {
                // Hypertonie Grad 3
                color = "#D03636"
                type = "Hypertonie Grad 3"
            }
        }

        return if (!isType) {
            color
        } else {
            "Systol: $type"
        }
    }

    fun getDiastol(diastol: Int, isType: Boolean = false): String {

        var color = ""
        var type = ""

        when {
            diastol < 65 -> {
                // Niedrig
                color = "#4464AD"
                type = "Niedrig"
            }
            diastol in 65..79 -> {
                // Optimal
                color = "#F19007"
                type = "Optimal"
            }
            diastol in 80..84 -> {
                // Normal
                color = "#F19007"
                type = "Normal"
            }
            diastol in 85..89 -> {
                // Hochnormal
                color = "#F19007"
                type = "Hochnormal"
            }
            diastol in 90..99 -> {
                // Hypertonie Grad 1
                color = "#ED363B"
                type = "Hypertonie Grad 1"
            }
            diastol in 100..109 -> {
                // Hypertonie Grad 2
                color = "#ED363B"
                type = "Hypertonie Grad 2"
            }
            diastol >= 110 -> {
                // Hypertonie Grad 3
                color = "#D03636"
                type = "Hypertonie Grad 3"
            }
        }

        return if (!isType) {
            color
        } else {
            "Diastol: $type"
        }
    }

    fun calculatePuls(gender: String, pulse: Int, isType: Boolean = false): String {

        var color = ""
        var type = ""

        if (gender == "1") {
            when {
                pulse <= 60 -> {
                    // Sehr gut
                    color = "#9ACD32"
                    type = "Sehr gut"
                }
                pulse in 61..69 -> {
                    // Gut
                    color = "#9ACD32"
                    type = "Gut"
                }
                pulse in 70..73 -> {
                    // Normal
                    color = "#4464AD"
                    type = "Normal"
                }
                pulse in 74..80 -> {
                    // Unterdurchschnittlich
                    color = "#ED363B"
                    type = "Unterdurchschnittlich"
                }
                pulse >= 110 -> {
                    // Schlecht
                    color = "#D03636"
                    type = "Schlecht"
                }
            }

        } else {
            when {
                pulse <= 65 -> {
                    // Sehr gut
                    color = "#9ACD32"
                    type = "Sehr gut"
                }
                pulse in 66..74 -> {
                    // Gut
                    color = "#9ACD32"
                    type = "Gut"
                }
                pulse in 75..78 -> {
                    // Normal
                    color = "#4464AD"
                    type = "Normal"
                }
                pulse in 79..84 -> {
                    // Unterdurchschnittlich
                    color = "#ED363B"
                    type = "Unterdurchschnittlich"
                }
                pulse >= 85 -> {
                    //Schlecht
                    color = "#D03636"
                    type = "Schlecht"
                }
            }
        }

        return if (!isType) {
            color
        } else {
            "Puls: $type"
        }

    }

    fun calculateBloodSugar(bloodSugar: Int, isType: Boolean = false): String {

        var color = ""
        var type = ""

        when {
            bloodSugar < 60 -> {
                // Akut niedrig
                color = "#D03636"
                type = "Akut niedrig"
            }
            bloodSugar in 60..79 -> {
                // Niedrig
                color = "#F19007"
                type = "Niedrig"
            }
            bloodSugar in 80..119 -> {
                // Optimaler Bereich
                color = "#F19007"
                type = "Optimaler Bereich"
            }
            bloodSugar in 120..159 -> {
                // Gute Einstellungen
                color = "#9ACD32"
                type = "Gute Einstellungen"
            }
            bloodSugar in 160..219 -> {
                // Schlechte Einstellung
                color = "#F19007"
                type = "Schlechte Einstellung"
            }
            bloodSugar >= 220 -> {
                // Sehr schlechte Einstellung
                color = "#D03636"
                type = "Sehr schlechte Einstellung"
            }
        }

        return if (!isType) {
            color
        } else {
            "Blutzucker: $type"
        }
    }

    fun calculateBMI(gender: String, height: Int, weight: Int, isType: Boolean = false): String {
        val bmi = weight / (height * height)

        var color = ""
        var type = ""

        if (gender == "1") {
            when {
                bmi < 20 -> {
                    // Untergewicht | Blue
                    color = "#4464AD"
                    type = "Untergewicht"
                }
                bmi in 20..25 -> {
                    // Normalgewicht | Green
                    color = "#F19007"
                    type = "Normalgewicht"
                }
                bmi in 26..30 -> {
                    // Übergewicht | Orange
                    color = "#F19007"
                    type = "Übergewicht"
                }
                bmi in 31..40 -> {
                    // Adipositas | Red
                    color = "#ED363B"
                    type = "Adipositas"
                }
                bmi >= 40 -> {
                    //starke Adipositas | Dark Red
                    color = "#D03636"
                    type = "starke Adipositas"

                }
            }
        } else {
            when {
                bmi < 19 -> {
                    // Untergewicht | Blue
                    color = "#4464AD"
                    type = "Untergewicht"
                }
                bmi in 19..24 -> {
                    // Normalgewicht | Green
                    color = "#F19007"
                    type = "Normalgewicht"
                }
                bmi in 25..30 -> {
                    // Übergewicht | Orange
                    color = "#F19007"
                    type = "Übergewicht"
                }
                bmi in 31..40 -> {
                    // Adipositas | Red
                    color = "#ED363B"
                    type = "Adipositas"
                }
                bmi >= 40 -> {
                    //starke Adipositas | Dark Red
                    color = "#D03636"
                    type = "starke Adipositas"
                }
            }
        }

        return if (!isType) {
            color
        } else {
            "Gewicht: $type"
        }

    }

}