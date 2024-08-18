package guilopes.Cadastro

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameEditText: EditText = findViewById(R.id.name)
        val phoneEditText: EditText = findViewById(R.id.phone)
        val emailEditText: EditText = findViewById(R.id.email)
        val emailListCheckBox: CheckBox = findViewById(R.id.email_list)
        val genderRadioGroup: RadioGroup = findViewById(R.id.gender_group)
        val cityEditText: EditText = findViewById(R.id.city)
        val ufSpinner: Spinner = findViewById(R.id.uf_spinner)
        val clearButton: Button = findViewById(R.id.clear_button)
        val saveButton: Button = findViewById(R.id.save_button)

        val ufAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.ufs_array,
            android.R.layout.simple_spinner_item
        )
        ufAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        ufSpinner.adapter = ufAdapter
        ufSpinner.setSelection(0)

        saveButton.setOnClickListener {
            var valitedFields = false

            val name = nameEditText.text.toString()
            if(name.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha seu nome.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val phone = phoneEditText.text.toString()
            if(phone.isNotEmpty()){
                val validatedPhone = ValidatorHelpers.isValidPhone(phone)

                if (!validatedPhone) {
                    Toast.makeText(this, "Por favor, preencha um telefone válido.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            val email = emailEditText.text.toString()
            if(email.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha seu email.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!ValidatorHelpers.isValidEmail(email)) {
                Toast.makeText(this, "Por favor, preencha um email válido.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val wantsEmail = emailListCheckBox.isChecked

            val city = cityEditText.text.toString()
            if(city.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha sua cidade.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedGenderId = genderRadioGroup.checkedRadioButtonId
            val gender = when (selectedGenderId) {
                R.id.male -> "Masculino"
                R.id.female -> "Feminino"
                else -> "Não especificado"
            }

            val uf = ufSpinner.selectedItem.toString()
            val selectedUf = ufSpinner.selectedItem.toString()
            if (selectedUf == "Selecione uma UF") {
                Toast.makeText(this, "Por favor, selecione uma UF válida.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            valitedFields = true

            if(valitedFields){
                val formulary = Formulario(
                    nome = name,
                    telefone = phone,
                    email = email,
                    cidade = city,
                    uf = uf,
                    querReceberEmails = wantsEmail,
                    genero = gender
                )

                Toast.makeText(this, formulary.toString(), Toast.LENGTH_LONG).show()
            }
        }


        clearButton.setOnClickListener {
            nameEditText.text.clear()
            phoneEditText.text.clear()
            emailEditText.text.clear()
            emailListCheckBox.isChecked = false
            genderRadioGroup.clearCheck()
            cityEditText.text.clear()
            ufSpinner.setSelection(0)
        }
    }
}
