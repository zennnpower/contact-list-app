package com.khayrul.contactlist.ui.contact.addContact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.khayrul.contactlist.R
import com.khayrul.contactlist.data.repository.ContactRepository
import com.khayrul.contactlist.databinding.FragmentAddContactBinding
import com.khayrul.contactlist.ui.contact.base.BaseContactFragment

class AddContactFragment : BaseContactFragment() {
    private lateinit var binding: FragmentAddContactBinding
    private val viewModel: AddContactViewModel by viewModels {
        AddContactViewModel.Provider(ContactRepository.contactRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentAddContactBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etName.addTextChangedListener {
            viewModel.name.value = it.toString()
        }

        binding.etPhone.addTextChangedListener {
            viewModel.phone.value = it.toString()
        }

        binding.btnSave.setOnClickListener {
            viewModel.save()
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("add_contact_finished", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }
    }
}