package com.r2solution.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.r2solution.convidados.R
import com.r2solution.convidados.databinding.FragmentAllBinding
import com.r2solution.convidados.view.adapter.GuestAdapter
import com.r2solution.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private lateinit var recycler: RecyclerView
    private var _binding: FragmentAllBinding? = null
    private val mAdapter: GuestAdapter = GuestAdapter()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //1 - Obter Recycler
        recycler = root.findViewById(R.id.recycler_all_guests)

        //2 - Definir layout
        recycler.layoutManager = LinearLayoutManager(context)

        //3 - Definir um adapter
        recycler.adapter = mAdapter

        observer()

        allGuestsViewModel.load()


        return root
    }

    private fun observer() {
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}