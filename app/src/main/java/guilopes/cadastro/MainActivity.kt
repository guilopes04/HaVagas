package guilopes.HaVagas

import Formulario
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import guilopes.HaVagas.databinding.ActivityMainBinding
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val educationAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.education_levels,
            android.R.layout.simple_spinner_item
        )
        educationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.education.adapter = educationAdapter
        binding.education.setSelection(0)

        binding.education.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val education = binding.education.selectedItem.toString()

                when (education) {
                    "Ensino Fundamental", "Ensino Médio" -> {
                        binding.conclusionYear.visibility = VISIBLE
                        binding.institution.visibility = GONE
                        binding.thesisTitle.visibility = GONE
                        binding.advisor.visibility = GONE
                    }
                    "Ensino Superior", "Pós-graduação" -> {
                        binding.conclusionYear.visibility = VISIBLE
                        binding.institution.visibility = VISIBLE
                        binding.thesisTitle.visibility = GONE
                        binding.advisor.visibility = GONE
                    }
                    "Mestrado", "Doutorado" -> {
                        binding.conclusionYear.visibility = VISIBLE
                        binding.institution.visibility = VISIBLE
                        binding.thesisTitle.visibility = VISIBLE
                        binding.advisor.visibility = VISIBLE
                    }
                    else -> {
                        binding.conclusionYear.visibility = GONE
                        binding.institution.visibility = GONE
                        binding.thesisTitle.visibility = GONE
                        binding.advisor.visibility = GONE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.birthDate.setOnClickListener {

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    binding.birthDate.setText(formattedDate)
                },
                year, month, day
            )
            datePickerDialog.show()
        }

        binding.saveButton.setOnClickListener {
            var valitedFields = true

            val name = binding.name.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha seu nome.", Toast.LENGTH_SHORT).show()
                valitedFields = false
                return@setOnClickListener
            }

            val phone1 = binding.phone1.text.toString()
            val phone2 = binding.phone2.text.toString()
            val selectedPhoneType = binding.phoneType.checkedRadioButtonId
            val phoneType = when (selectedPhoneType) {
                R.id.comercial -> "Comercial"
                R.id.house -> "Residencial"
                else -> "Não especificado"
            }

            val phoneTypeLowerCase = phoneType.lowercase(Locale.getDefault())

            if (phone1.isEmpty()) {
                Toast.makeText(this, """Por favor, preencha o telefone $phoneTypeLowerCase.""", Toast.LENGTH_SHORT).show()
                valitedFields = false
                return@setOnClickListener
            } else if (!ValidatorHelpers.isValidPhone(phone1)) {
                Toast.makeText(this, """Por favor, preencha o telefone $phoneTypeLowerCase válido.""", Toast.LENGTH_SHORT).show()
                valitedFields = false
                return@setOnClickListener
            }

            if (binding.phone2.visibility == VISIBLE) {
                if (phone2.isEmpty()) {
                    Toast.makeText(
                        this,
                        """Por favor, preencha um telefone celular.""",
                        Toast.LENGTH_SHORT
                    ).show()
                    valitedFields = false
                    return@setOnClickListener
                } else if (!ValidatorHelpers.isValidPhone(phone2)) {
                    Toast.makeText(
                        this,
                        "Por favor, preencha um telefone celular válido.",
                        Toast.LENGTH_SHORT
                    ).show()
                    valitedFields = false
                    return@setOnClickListener
                }
            }

            val email = binding.email.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha seu email.", Toast.LENGTH_SHORT).show()
                valitedFields = false
                return@setOnClickListener
            } else if (!ValidatorHelpers.isValidEmail(email)) {
                Toast.makeText(this, "Por favor, preencha um email válido.", Toast.LENGTH_SHORT).show()
                valitedFields = false
                return@setOnClickListener
            }

            val birthDate = binding.birthDate.text.toString()
            if (birthDate.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha a data de nascimento.", Toast.LENGTH_SHORT).show()
                valitedFields = false
                return@setOnClickListener
            }

            val selectedGenderId = binding.genderGroup.checkedRadioButtonId
            val gender = when (selectedGenderId) {
                R.id.male -> "Masculino"
                R.id.female -> "Feminino"
                else -> "Não especificado"
            }

            val education = binding.education.selectedItem.toString()
            if (education == "Selecione o nivel de escolaridade") {
                Toast.makeText(this, "Por favor, selecione um nível de escolaridade.", Toast.LENGTH_SHORT).show()
                valitedFields = false
                return@setOnClickListener
            }

            val conclusionYear = binding.conclusionYear.text.toString()
            val institution = binding.institution.text.toString()
            val thesisTitle = binding.thesisTitle.text.toString()
            val advisor = binding.advisor.text.toString()

            when (education) {
                "Ensino Fundamental", "Ensino Médio" -> {

                    if(!validatedConclusionYear(conclusionYear)) {
                        valitedFields = false
                        return@setOnClickListener
                    }
                }
                "Ensino Superior", "Pós-graduação" -> {
                    if(!validatedConclusionYear(conclusionYear)) {
                        valitedFields = false
                        return@setOnClickListener
                    }
                    if(!validatedInstitution(institution)){
                        valitedFields = false
                        return@setOnClickListener
                    }
                }
                "Mestrado", "Doutorado" -> {
                    if(!validatedConclusionYear(conclusionYear)) {
                        valitedFields = false
                        return@setOnClickListener
                    }
                    if(!validatedInstitution(institution)){
                        valitedFields = false
                        return@setOnClickListener
                    }
                    if(!validatedThesisTitle(thesisTitle)){
                        valitedFields = false
                        return@setOnClickListener
                    }
                    if(!validatedAdvisor(advisor)){
                        valitedFields = false
                        return@setOnClickListener
                    }
                }
            }

            val jobsInterest = binding.jobsInterest.text.toString()
            if (jobsInterest.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha vagas de interesse.", Toast.LENGTH_SHORT).show()
                valitedFields = false
                return@setOnClickListener
            }

            val wantsEmail = binding.emailList.isChecked

            if (valitedFields) {
                val formulario = Formulario(
                    nome = name,
                    telefone1 = phone1,
                    phoneType = phoneType,
                    telefone2 = phone2,
                    email = email,
                    querReceberEmails = wantsEmail,
                    genero = gender,
                    dataNascimento = birthDate,
                    nivelEscolaridade = education,
                    vagasInteresse = jobsInterest
                )

                Toast.makeText(this, formulario.toString(), Toast.LENGTH_LONG).show()
            }
        }


        binding.clearButton.setOnClickListener {
            binding.name.text.clear()
            binding.phone1.text.clear()
            binding.phone2.visibility = GONE
            binding.phone2.text.clear()
            binding.email.text.clear()
            binding.emailList.isChecked = false
            binding.birthDate.text?.clear()
            binding.genderGroup.clearCheck()
            binding.education.setSelection(0)
            binding.conclusionYear.text.clear()
            binding.institution.text.clear()
            binding.thesisTitle.text.clear()
            binding.advisor.text.clear()
            binding.jobsInterest.text.clear()
        }

        binding.addPhone.setOnClickListener {
            binding.phone2.visibility = VISIBLE
        }
    }

    private fun validatedConclusionYear(conclusionYear: String): Boolean {
        if (conclusionYear.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o ano de conclusão.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validatedInstitution(institution: String): Boolean {
        if (institution.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o nome da instituição.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validatedThesisTitle(thesisTitle: String): Boolean {
        if (thesisTitle.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o título de monografia.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validatedAdvisor(advisor: String): Boolean {
        if (advisor.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o nome do orientador.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}