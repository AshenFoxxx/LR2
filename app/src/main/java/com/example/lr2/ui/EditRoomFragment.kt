package com.example.lr2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lr2.databinding.FragmentEditRoomBinding
import com.example.lr2.model.Room
import com.example.lr2.viewmodel.RoomViewModel

class EditRoomFragment : Fragment() {
    private lateinit var binding: FragmentEditRoomBinding
    private val roomViewModel: RoomViewModel by activityViewModels()
    private val args: EditRoomFragmentArgs by navArgs() // Правильно получаем аргументы
    private lateinit var room: Room

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        room = args.room
        setInitialData()
        setupSaveButton()
        setupBackButton()
    }

    private fun setInitialData() {
        binding.roomNameEditText.setText(room.name)
        binding.pricePerDayEditText.setText(room.pricePerDay.toString())
        binding.priceForThreeHoursEditText.setText(room.priceForThreeHours.toString())
        binding.bedTypeEditText.setText(room.bedType)
        binding.hasTvEditText.setText(room.hasTV.toString())
        binding.bathroomTypeEditText.setText(room.bathroomType)
        binding.floorTypeEditText.setText(room.floorType)
    }

    private fun setupSaveButton() {
        binding.saveButton.setOnClickListener {
            if(validateFields()){
                val editedRoom = Room(
                    room.id,
                    binding.roomNameEditText.text.toString(),
                    binding.pricePerDayEditText.text.toString().toDouble(),
                    binding.priceForThreeHoursEditText.text.toString().toDouble(),
                    binding.bedTypeEditText.text.toString(),
                    binding.hasTvEditText.text.toString().toBoolean(),
                    binding.bathroomTypeEditText.text.toString(),
                    binding.floorTypeEditText.text.toString()
                )
                roomViewModel.updateRoom(editedRoom)
                findNavController().popBackStack()
            } else{
                Toast.makeText(requireContext(), "Заполните все обязательные поля", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateFields(): Boolean {
        return binding.roomNameEditText.text.isNotEmpty() && binding.pricePerDayEditText.text.isNotEmpty()
    }

    private fun setupBackButton() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}