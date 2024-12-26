package com.example.lr2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lr2.model.Room

class RoomViewModel : ViewModel() {

    private val _rooms = MutableLiveData<MutableList<Room>>()
    val rooms: LiveData<MutableList<Room>> = _rooms

    init {
        // Заполним список номеров
        val initialRooms = mutableListOf(
            Room(1, "Одноместный комфорт", 3000.0, 1500.0, "Односпальная", true, "Душ", "Паркет"),
            Room(2, "Одноместный стандарт", 2500.0, 1200.0, "Односпальная", false, "Душ", "Ламинат"),
            Room(3, "Одноместный люкс", 4000.0, 2000.0, "Односпальная", true, "Ванна", "Паркет"),
            Room(4, "Двухместный стандарт", 3500.0, 1800.0, "Двуспальная", true, "Душ", "Ламинат"),
            Room(5, "Двухместный комфорт", 4500.0, 2200.0, "Двуспальная", true, "Ванна", "Паркет"),
            Room(6, "Двухместный люкс", 6000.0, 3000.0, "Двуспальная", true, "Ванна", "Плитка")
        )
        _rooms.value = initialRooms
    }

    fun addRoom(room: Room) {
        val currentRooms = _rooms.value ?: mutableListOf()
        currentRooms.add(room)
        _rooms.value = currentRooms
    }

    fun deleteRoom(room: Room) {
        val currentRooms = _rooms.value ?: mutableListOf()
        currentRooms.remove(room)
        _rooms.value = currentRooms
    }

    fun updateRoom(room: Room){
        val currentRooms = _rooms.value ?: mutableListOf()
        val index = currentRooms.indexOfFirst { it.id == room.id }
        if(index != -1){
            currentRooms[index] = room
            _rooms.value = currentRooms
        }
    }

    fun getRoomById(id: Int) : Room? {
        return _rooms.value?.find { it.id == id }
    }
}