package com.csanad.goodtimes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.csanad.goodtimes.databinding.FragmentRemindersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RemindersFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel
    var binding:FragmentRemindersBinding?=null
    val adapter by lazy { RemindersAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRemindersBinding.inflate(inflater, container, false)
        mainViewModel= ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        readDatabase()
        binding!!.recyclerView.adapter=adapter
        binding!!.recyclerView.layoutManager=LinearLayoutManager(requireContext())

        binding!!.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_remindersFragment_to_addReminderBottomSheet)
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }

    fun readDatabase(){
        adapter.setData()
    }

    fun requestApiData(){
        mainViewModel.getQuotes()
    }
}