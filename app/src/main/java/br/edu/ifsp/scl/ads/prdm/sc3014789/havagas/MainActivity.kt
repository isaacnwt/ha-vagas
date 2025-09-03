package br.edu.ifsp.scl.ads.prdm.sc3014789.havagas

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3014789.havagas.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(activityMainBinding) {
            setContentView(activityMainBinding.root)
            setupListeners()
        }
    }

    private fun setupListeners() = with(activityMainBinding) {
        setupCelularToggle()
        setupDataNascimentoPicker()
    }

    private fun setupCelularToggle() = with(activityMainBinding) {
        adicionarCelularCb.setOnClickListener { view ->
            if ((view as CheckBox).isChecked) {
                celularEt.visibility = View.VISIBLE
            } else {
                celularEt.visibility = View.GONE
                celularEt.setText("")
            }
        }
    }

    private fun setupDataNascimentoPicker() {
        activityMainBinding.dataNascimentoEt.setOnClickListener {
            val calendario = Calendar.getInstance()
            val ano = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(
                this,
                { _, anoSelecionado, mesSelecionado, diaSelecionado ->
                    val dataFormatada =
                        "%02d/%02d/%d".format(diaSelecionado, mesSelecionado + 1, anoSelecionado)
                    activityMainBinding.dataNascimentoEt.setText(dataFormatada)
                },
                ano, mes, dia
            )

            datePicker.show()
        }
    }

}