package br.edu.ifsp.scl.ads.prdm.sc3014789.havagas

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.EditText
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

    private fun setupListeners() {
        setupCelularToggle()
        setupDataNascimentoPicker()
        setupFormacaoToggle()
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

    private fun setupFormacaoToggle() = with(activityMainBinding) {
        formacaoSp.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            val ENSINO_BASICO_OPTIONS = listOf(0, 1)
            val ENSINO_SUPERIOR_OPTIONS = listOf(2, 3, 4, 5)
            val POS_GRADUACAO_OPTIONS = listOf(4, 5)

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setVisibilityBySelectedPosition(position)
            }

            private fun setVisibilityBySelectedPosition(position: Int) {
                if (position in ENSINO_BASICO_OPTIONS) {
                    anoFormaturaEt.visibility = View.VISIBLE
                    anoConclusaoIntituicaoLl.visibility = View.GONE
                    tituloMonografiaOrientadorLl.visibility = View.GONE
                    clearEditTexts(listOf(anoConclusaoEt, instituicaoEt, tituloMonografiaEt, orientadorEt))
                } else {
                    anoFormaturaEt.visibility = View.GONE
                    anoFormaturaEt.setText("")
                }

                if (position in ENSINO_SUPERIOR_OPTIONS) {
                    anoConclusaoIntituicaoLl.visibility = View.VISIBLE
                    tituloMonografiaOrientadorLl.visibility = View.GONE
                    clearEditTexts(listOf(tituloMonografiaEt, orientadorEt))
                }
                if (position in POS_GRADUACAO_OPTIONS) {
                    tituloMonografiaOrientadorLl.visibility = View.VISIBLE
                }
            }

            private fun clearEditTexts(editTextList: List<EditText>) {
                for (editText in editTextList)
                    editText.setText("")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }

}