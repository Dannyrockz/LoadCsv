package com.example.loadcsv.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loadcsv.R
import com.example.loadcsv.view.UserDetailsAdapter
import com.example.loadcsv.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val userDetailsAdapter = UserDetailsAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        rvUserDetails.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userDetailsAdapter
        }
        viewModel.getDataFromCSV().observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                userDetailsAdapter.updateUserList(list)
            }
        })

    }

}