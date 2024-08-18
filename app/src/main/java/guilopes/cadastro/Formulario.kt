package guilopes.Cadastro

data class Formulario(
    val nome: String,
    val telefone: String,
    val email: String,
    val cidade: String,
    val uf: String,
    val querReceberEmails: Boolean,
    val genero: String
) {
    override fun toString(): String {
        return """
            Nome: $nome
            Telefone: $telefone
            Email: $email
            Cidade: $cidade
            UF: $uf
            Receber e-mails: ${if (querReceberEmails) "Sim" else "Não"}
            Gênero: $genero
        """.trimIndent()
    }
}