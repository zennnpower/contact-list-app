package com.khayrul.contactlist.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.khayrul.contactlist.R
import com.khayrul.contactlist.data.model.Contact
import com.khayrul.contactlist.data.repository.ContactRepository
import com.khayrul.contactlist.databinding.FragmentHomeBinding
import com.khayrul.contactlist.ui.ContactAdapter

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider(ContactRepository.contactRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            setupAdapter(contacts)
        }

        binding.fabAdd.setOnClickListener {
            navigateToAddContact()
        }

        setFragmentResultListener("add_contact_finished") { _, result ->
            if (result.getBoolean("refresh")) {
                viewModel.refresh()
            }
        }
    }

    fun setupAdapter(contacts: List<Contact>) {
        val contactAdapter = ContactAdapter(contacts)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvContacts.adapter = contactAdapter
        binding.rvContacts.layoutManager = layoutManager
    }

    fun navigateToAddContact() {
        val action = HomeFragmentDirections.actionHomeToAddContact()
        NavHostFragment.findNavController(this).navigate(action)
    }
}
