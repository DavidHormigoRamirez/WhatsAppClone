package com.alanturing.cpifp.whatsappclone.register.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alanturing.cpifp.whatsappclone.MainActivity
import com.alanturing.cpifp.whatsappclone.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    //ASOCIO EL VIEW MODEL A ALA ACTIVITIDAD
    private val viewModel: RegisterViewModel by viewModels()


    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collect {uiState ->
                    binding.progressIndicator.visibility = View.GONE
                    binding.phoneLabel.error = null
                    when(uiState) {
                        is UiState.started -> {}
                        is UiState.error -> {
                            binding.phoneLabel.error = uiState.error
                        }
                        is UiState.loading -> {
                            binding.progressIndicator.visibility = View.VISIBLE
                        }
                        is UiState.success -> {
                                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                                finish()
                        }
                    }

                }
            }
        }

        binding.registerButton.setOnClickListener {
            binding.phoneLabel.error = null
            if (binding.phoneText.text.isNullOrBlank()) {
                binding.phoneLabel.error = "Debe indicar un telefono"
            }
            else {
                viewModel.register(binding.phoneText.text.toString())
            }
        }
    }
}