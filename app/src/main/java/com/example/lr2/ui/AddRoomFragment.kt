package com.example.lr2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lr2.databinding.FragmentAddRoomBinding
import com.example.lr2.model.Room
import com.example.lr2.viewmodel.RoomViewModel

class AddRoomFragment : Fragment() {
    private lateinit var binding: FragmentAddRoomBinding
    private val roomViewModel: RoomViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSaveButton()
        setupBackButton()
    }


    private fun setupSaveButton() {
        binding.saveButton.setOnClickListener {
            if(validateFields()){
                val newRoom = Room(
                    getNextId(),
                    binding.roomNameEditText.text.toString(),
                    binding.pricePerDayEditText.text.toString().toDouble(),
                    binding.priceForThreeHoursEditText.text.toString().toDouble(),
                    binding.bedTypeEditText.text.toString(),
                    binding.hasTvEditText.text.toString().toBoolean(),
                    binding.bathroomTypeEditText.text.toString(),
                    binding.floorTypeEditText.text.toString()
                )
                roomViewModel.addRoom(newRoom)
                findNavController().popBackStack()
            } else{
                Toast.makeText(requireContext(), "Заполните все обязательные поля", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getNextId() : Int {
        return   (roomViewModel.rooms.value?.maxOf { it.id } ?: 0) + 1
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