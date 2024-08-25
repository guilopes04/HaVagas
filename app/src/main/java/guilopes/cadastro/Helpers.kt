package guilopes.HaVagas

import android.util.Patterns

class ValidatorHelpers {
    companion object {
        fun isValidEmail(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidPhone(phone: String): Boolean {
            val phonePattern = "^\\(?(\\d{2})\\)? ?(9?\\d{4})-?(\\d{4})$".toRegex()
            return phonePattern.matches(phone)
        }
    }
}