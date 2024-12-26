package com.example.lr2.model
import android.os.Parcel
import android.os.Parcelable



data class Room(
    val id: Int,
    val name: String,
    var pricePerDay: Double,
    var priceForThreeHours: Double,
    var bedType: String,
    var hasTV: Boolean,
    var bathroomType: String,
    var floorType: String
) : Parcelable{
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeDouble(pricePerDay)
        parcel.writeDouble(priceForThreeHours)
        parcel.writeString(bedType)
        parcel.writeByte(if (hasTV) 1 else 0)
        parcel.writeString(bathroomType)
        parcel.writeString(floorType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Room> {
        override fun createFromParcel(parcel: Parcel): Room {
            return Room(
                parcel.readInt(),
                parcel.readString() ?: "",
                parcel.readDouble(),
                parcel.readDouble(),
                parcel.readString() ?: "",
                parcel.readByte() != 0.toByte(),
                parcel.readString() ?: "",
                parcel.readString() ?: ""
            )
        }

        override fun newArray(size: Int): Array<Room?> {
            return arrayOfNulls(size)
        }
    }
}