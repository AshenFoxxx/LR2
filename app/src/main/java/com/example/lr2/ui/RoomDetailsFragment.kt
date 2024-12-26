package com.example.lr2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lr2.databinding.FragmentRoomDetailsBinding
import com.example.lr2.model.Room
import com.example.lr2.viewmodel.RoomViewModel

class RoomDetailsFragment : Fragment() {
    private lateinit var binding: FragmentRoomDetailsBinding
    private val roomViewModel: RoomViewModel by activityViewModels()
    private val args: RoomDetailsFragmentArgs by navArgs()
    private lateinit var room: Room

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        room =  roomViewModel.getRoomById(args.roomId)!!

        displayRoomDetails(room)
        setupEditButton()
        setupBackButton()
    }

    private fun displayRoomDetails(room: Room) {
        binding.roomNameTextView.text = room.name
        binding.pricePerDayTextView.text = "Цена за сутки: ${room.pricePerDay} руб."
        binding.priceForThreeHoursTextView.text = "Цена за 3 часа: ${room.priceForThreeHours} руб."
        binding.bedTypeTextView.text = "Тип кровати: ${room.bedType}"
        binding.hasTvTextView.text = "Телевизор: ${if (room.hasTV) "Есть" else "Нет"}"
        binding.bathroomTypeTextView.text = "Санузел: ${room.bathroomType}"
        binding.floorTypeTextView.text = "Покрытие пола: ${room.floorType}"
    }

    private fun setupEditButton() {
        binding.editButton.setOnClickListener {
            val action = RoomDetailsFragmentDirections.actionRoomDetailsFragmentToEditRoomFragment(room)
            findNavController().navigate(action)
        }
    }

    private fun setupBackButton() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}