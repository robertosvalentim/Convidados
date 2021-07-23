package com.r2solution.convidados.view

import android.content.Intent
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
import com.r2solution.convidados.service.constants.GuestConstants
import com.r2solution.convidados.view.adapter.GuestAdapter
import com.r2solution.convidados.view.listener.GuestListener
import com.r2solution.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var mViewModel: AllGuestsViewModel
    private lateinit var recycler: RecyclerView
    private var _binding: FragmentAllBinding? = null
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //1 - Obter Recycler
        recycler = root.findViewById(R.id.recycler_all_guests)

        //2 - Definir layout
        recycler.layoutManager = LinearLayoutManager(context)

        //3 - Definir um adapter
        recycler.adapter = mAdapter

        mListener = object: GuestListener{
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()

                bundle.putInt(GuestConstants.GUESTID,id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load()
            }

        }
        mAdapter.attachListener(mListener)

        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load()
    }

    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}