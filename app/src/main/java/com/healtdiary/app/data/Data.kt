package com.healtdiary.app.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/*
- Height
- Weight
- Blood Pressure (Systol / Diastol)
- Pulse
- Bloodsugar mg/dL
 */

@Entity(tableName = "data")
@Parcelize
data class Data(
    @PrimaryKey(autoGenerate = true) var id: Int? = 0,
    @ColumnInfo(name = "dateTime") val dateTime: String,
    @ColumnInfo(name = "gender") var gender: String,
    @ColumnInfo(name = "height") var height: String,
    @ColumnInfo(name = "weight") var weight: String,
    @ColumnInfo(name = "bloodSystol") var bloodSystol: String,
    @ColumnInfo(name = "bloodDiastol") var bloodDiastol: String,
    @ColumnInfo(name = "pulse") var pulse: String,
    @ColumnInfo(name = "bloodSugar") var bloodSugar: String
) : Parcelable