package com.alanturing.cpifp.whatsappclone.chat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alanturing.cpifp.whatsappclone.chat.data.ChatRepository
import com.alanturing.cpifp.whatsappclone.databinding.FragmentChatListBinding

class ChatListFragment : Fragment() {
    private lateinit var binding: FragmentChatListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       binding = FragmentChatListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val createChatButton = binding.createChatButton
        binding.chatList.layoutManager = LinearLayoutManager(context)

        val listAdapter = ChatListAdapter(::toChat)
        val repo = ChatRepository()

        listAdapter.submitList(repo.chats)
        binding.chatList.adapter = listAdapter

        createChatButton.setOnClickListener {
            val action = ChatListFragmentDirections.actionChatListFragmentToSelectContactFragment()
            findNavController().navigate(action)
        }

    }

    private fun toChat(view: View) {
        val action = ChatListFragmentDirections.actionChatListFragmentToChatFragment()
        findNavController().navigate(action)
    }

}