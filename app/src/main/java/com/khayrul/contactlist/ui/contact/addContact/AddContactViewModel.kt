package com.khayrul.contactlist.ui.contact.addContact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khayrul.contactlist.data.model.Contact
import com.khayrul.contactlist.data.repository.ContactRepository
import java.lang.IllegalArgumentException

class AddContactViewModel(private val repository: ContactRepository): ViewModel() {
    val name: MutableLiveData<String> = MutableLiveData()
    val phone: MutableLiveData<String> = MutableLiveData()

    fun save() {
        if (name.value.isNullOrEmpty() || phone.value.isNullOrEmpty()) {
            // Show error
        } else {
            val contact = Contact(name = name.value ?: "", phone = phone.value ?: "")
            repository.addContact(contact)
        }
    }

    class Provider(private val repository: ContactRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddContactViewModel::class.java)) {
                return AddContactViewModel(repository) as T
            }

            throw IllegalArgumentException("ViewModel is not valid")
        }
    }
}