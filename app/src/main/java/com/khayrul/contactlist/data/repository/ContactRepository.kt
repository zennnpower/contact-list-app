package com.khayrul.contactlist.data.repository

import com.khayrul.contactlist.data.model.Contact

class ContactRepository {
    var counter = 1
    private val contacts: MutableMap<Int, Contact> = mutableMapOf(
        0 to Contact(name = "hello", phone = "1234"),
        1 to Contact(name = "World!", phone = "5678")
    )

    fun getContacts(): List<Contact> {
        return contacts.values.toList()
    }

    fun addContact(contact: Contact): Contact? {
        counter++
        contacts[counter] = contact
        return contacts[counter]
    }

    companion object {
        val contactRepository = ContactRepository()
    }
}
