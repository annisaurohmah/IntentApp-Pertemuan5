package com.example.intentapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intentapp.MainActivity.Companion.EXTRA_NAME
import com.example.intentapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val EXTRA_NAME = "EXT_NAME"
    private lateinit var binding: ActivitySecondBinding

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->
// Memeriksa result code
            if (result.resultCode == Activity.RESULT_OK) {
// Mengambil data Intent
                val data = result.data
// Mendapatkan alamat dari data Intent
                val name = data?.getStringExtra(EXTRA_NAME)
                val address = data?.getStringExtra(EXTRA_ADDRESS)
// Menetapkan teks di TextView
                binding.txtName.text = "$name beralamat di $address"
            }
        }
    companion object{
        const val EXTRA_ADDRESS = "extra_address"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        with(binding) {
            txtName.text = name

            btnToThirdActivity.setOnClickListener {
                val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                    .apply { putExtra(EXTRA_NAME,name) }
                launcher.launch(intent)
        }




        }
    }


}