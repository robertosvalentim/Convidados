package com.r2solution.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.r2solution.convidados.viewmodel.GuestFormViewModel
import com.r2solution.convidados.R

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestFormViewModel
    private lateinit var mBotaoSave : Button
    private lateinit var mEditTextNome: EditText
    private lateinit var mRadioPresenca: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mBotaoSave = findViewById(R.id.button_save)
        mEditTextNome = findViewById(R.id.edit_nome)
        mRadioPresenca = findViewById(R.id.radio_presence)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)


        setListeners()
        observe()
    }

    override fun onClick(v: View) {
        val id = v.id
        if(id == R.id.button_save){
            val nome = mEditTextNome.text.toString()
            val presenca = mRadioPresenca.isChecked

            mViewModel.save(nome,presenca)
        }
    }

    private fun observe(){
        mViewModel.saveGuest.observe(this, Observer{
            if(it){
                Toast.makeText(applicationContext,"Sucesso!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext,"Falha!", Toast.LENGTH_LONG).show()
            }
            finish()
        })
    }
    private fun setListeners() {
        mBotaoSave.setOnClickListener(this)
    }


}