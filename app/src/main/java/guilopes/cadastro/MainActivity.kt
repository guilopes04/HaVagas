package guilopes.Cadastro

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import guilopes.Cadastro.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ufAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.ufs_array,
            android.R.layout.simple_spinner_item
        )
        ufAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.ufSpinner.adapter = ufAdapter
        binding.ufSpinner.setSelection(0)

        binding.saveButton.setOnClickListener {
            var valitedFields = false

            val name = binding.name.text.toString()
            if(name.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha seu nome.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val phone = binding.phone.text.toString()
            if(phone.isNotEmpty()){
                val validatedPhone = ValidatorHelpers.isValidPhone(phone)

                if (!validatedPhone) {
                    Toast.makeText(this, "Por favor, preencha um telefone válido.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            val email = binding.email.text.toString()
            if(email.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha seu email.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!ValidatorHelpers.isValidEmail(email)) {
                Toast.makeText(this, "Por favor, preencha um email válido.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val wantsEmail = binding.emailList.isChecked

            val city = binding.city.text.toString()
            if(city.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha sua cidade.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedGenderId = binding.genderGroup.checkedRadioButtonId
            val gender = when (selectedGenderId) {
                R.id.male -> "Masculino"
                R.id.female -> "Feminino"
                else -> "Não especificado"
            }

            val uf = binding.ufSpinner.selectedItem.toString()
            if (uf == "Selecione uma UF") {
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


        binding.clearButton.setOnClickListener {
            binding.name.text.clear()
            binding.phone.text.clear()
            binding.email.text.clear()
            binding.emailList.isChecked = false
            binding.genderGroup.clearCheck()
            binding.city.text.clear()
            binding.ufSpinner.setSelection(0)
        }
    }
}