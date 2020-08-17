package com.healtdiary.app.data

import com.healtdiary.app.util.ConvertDateTime

class NewData(newData: NewData?) {

    var mId: String? = null
    var mDateTime: String? = null
    var mGender: String? = null
    var mHeight: String? = null
    var mWeight: String? = null
    var mBloodSystol: String? = null
    var mBloodDiastol: String? = null
    var mPulse: String? = null
    var mBloodSugar: String? = null

    init {
        mId = newData?.mId
        mDateTime = newData?.mDateTime
        mGender = newData?.mGender
        mHeight = newData?.mHeight
        mWeight = newData?.mWeight
        mBloodSystol = newData?.mBloodSystol
        mBloodDiastol = newData?.mBloodDiastol
        mPulse = newData?.mPulse
        mBloodSugar = newData?.mBloodSugar
    }

    fun toData(): Data {

        val dateTimeGen = ConvertDateTime()

        return Data(
            mId?.toInt(),
            dateTimeGen.getCalendar(),
            mGender!!,
            mHeight!!,
            mWeight!!,
            mBloodSystol!!,
            mBloodDiastol!!,
            mPulse!!,
            mBloodSugar!!
        )
    }

    override fun toString(): String {
        return mDateTime + " " + mGender + " " + mHeight + " " + mWeight + " " + mBloodSystol + " " + mBloodDiastol + " " + mPulse.toString() + " " + mBloodSugar
    }

}