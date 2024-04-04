package com.alanturing.cpifp.whatsappclone.chat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alanturing.cpifp.whatsappclone.R
import com.alanturing.cpifp.whatsappclone.chat.data.Chat
import com.alanturing.cpifp.whatsappclone.chat.data.ChatRepository
import com.alanturing.cpifp.whatsappclone.chat.data.Contact
import com.alanturing.cpifp.whatsappclone.chat.data.ContactRepository
import com.alanturing.cpifp.whatsappclone.databinding.FragmentSelectContactBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SelectContactFragment : Fragment() {
    private lateinit var binding: FragmentSelectContactBinding
    @Inject
    lateinit var chatRepository:ChatRepository
    @Inject lateinit var contactRepository:ContactRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectContactBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recupero el repo
        //val contactRepository = ContactRepository()
        // Recupero el RV de Pantalla
        val recyclerView = binding.contactList
        // Lo configuro para scroll vertical
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Creo el adaptador y le paso la lista de contactos del repo
        val adapter = ContactListAdapter(::toChat)
        adapter.submitList(contactRepository.contacts)

        // Asocio el adaptador al reciclerview
        recyclerView.adapter = adapter

    }


    fun toChat(view:View,contact: Contact) {


        val chat: Chat? = chatRepository.chats.find {
            it.to == contact.id
        }
        // Si no existe el chat lo creo
        if (chat == null) {
            chatRepository.createChat(contact)
        }

        // Navego siempre
        val action =
            SelectContactFragmentDirections.actionSelectContactFragmentToChatFragment(contact.id)
        findNavController().navigate(action)
    }
}