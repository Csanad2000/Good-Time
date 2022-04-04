package com.csanad.goodtimes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.csanad.goodtimes.databinding.FragmentRemindersBinding
import dagger.hilt.android.AndroidEntryPoint

class RemindersFragment : Fragment() {
    var binding:FragmentRemindersBinding?=null

    /*lateinit var mainViewModel:MainViewModel
    lateinit var remindersViewModel:RemindersViewModel
    val adapter by lazy { RemindersAdapter() }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRemindersBinding.inflate(inflater, container, false)
        /*binding.lifecycleOwner=this
        binding.mainViewModel=mainViewModel

        setupRecyclerView()
        readDatabase()*/

        binding!!.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_remindersFragment_to_addReminderBottomSheet)
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}