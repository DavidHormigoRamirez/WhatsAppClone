package com.alanturing.cpifp.whatsappclone.register.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
                    when(uiState) {
                        is UiState.error -> {

                            val error = uiState as UiState.error
                            Toast.makeText(this@RegisterActivity,
                                error.error,
                                Toast.LENGTH_LONG)
                            binding.phoneLabel.error = error.error
                        }
                        is UiState.loading -> {
                            binding.phoneLabel.error = null
                        }
                        is UiState.success -> {
                                binding.phoneLabel.error = null
                                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                                finish()
                        }


                    }
                    // HACER COSAS CUANDO CAMBIE user

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