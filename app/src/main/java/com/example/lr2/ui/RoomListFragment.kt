package com.example.lr2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lr2.R
import com.example.lr2.adapter.RoomAdapter
import com.example.lr2.databinding.FragmentRoomListBinding
import com.example.lr2.model.Room
import com.example.lr2.viewmodel.RoomViewModel

class RoomListFragment : Fragment() {
    private lateinit var binding: FragmentRoomListBinding
    private val roomViewModel: RoomViewModel by activityViewModels()
    private lateinit var adapter: RoomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeRoomList()
        setupAddButton()
    }

    private fun setupRecyclerView() {
        adapter = RoomAdapter(
            mutableListOf(),
            ::onItemClick,
            ::onDeleteClick
        )
        binding.roomsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.roomsRecyclerView.adapter = adapter
    }


    private fun observeRoomList() {
        roomViewModel.rooms.observe(viewLifecycleOwner) { rooms ->
            adapter = RoomAdapter(
                rooms,
                ::onItemClick,
                ::onDeleteClick
            )
            binding.roomsRecyclerView.adapter = adapter
        }
    }

    private fun setupAddButton() {
        binding.toolbar.inflateMenu(R.menu.add_menu)
        binding.toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.menu_add){
                val action = RoomListFragmentDirections.actionRoomListFragmentToAddRoomFragment()
                findNavController().navigate(action)
                true
            } else {
                false
            }
        }
    }

    private fun onItemClick(room: Room) {
        val action = RoomListFragmentDirections.actionRoomListFragmentToRoomDetailsFragment(room.id)
        findNavController().navigate(action)
    }

    private fun onDeleteClick(room: Room) {
        roomViewModel.deleteRoom(room)
    }
}