package guilopes.Cadastro

import android.util.Patterns

class ValidatorHelpers {
    companion object {
        fun isValidEmail(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidPhone(phone: String): Boolean {
            return Patterns.PHONE.matcher(phone).matches()
        }
    }
}