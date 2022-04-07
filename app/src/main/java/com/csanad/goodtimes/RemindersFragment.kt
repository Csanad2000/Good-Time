package com.csanad.goodtimes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.csanad.goodtimes.databinding.FragmentRemindersBinding
import com.csanad.goodtimes.quotes.database.quote.QuotesEntity
import com.csanad.goodtimes.reminders.Collection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

        binding!!.recyclerView.adapter=adapter
        binding!!.recyclerView.layoutManager=LinearLayoutManager(requireContext())
        readDatabase()

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
        //TODO
        lifecycleScope.launch {
            mainViewModel.readReminders.observe(viewLifecycleOwner) {
                adapter.setData(it)
            }
        }
    }

    fun requestApiData(){
        mainViewModel.getQuotes()
        mainViewModel.quotesResponse.observe(viewLifecycleOwner,{
            when(it){
                is NetworkResult.Success->{
                    for (i in it.data!!.indices)
                    mainViewModel.insertQuotes(QuotesEntity(it.data!![i]))
                }
                is NetworkResult.Error->{
                    Toast.makeText(requireContext(),it.message.toString(),Toast.LENGTH_SHORT).show()
                    //loadQuoteFromCache()
                }
                is NetworkResult.Loading->{

                }
            }
        })
    }

    fun loadQuoteFromCache(){
        lifecycleScope.launch {
            mainViewModel.readQuotes.observe(viewLifecycleOwner,{
                if (it.isNotEmpty()){

                }else{

                }
            })
        }
    }
}