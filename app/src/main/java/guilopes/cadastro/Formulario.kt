data class Formulario(
    val nome: String,
    val telefone1: String,
    val phoneType: String,
    val telefone2: String,
    val email: String,
    val querReceberEmails: Boolean,
    val genero: String,
    val dataNascimento: String,
    val nivelEscolaridade: String,
    val anoConclusao: String,
    val instituicao: String,
    val monografia: String,
    val orientador: String,
    val vagasInteresse: String
) {
    override fun toString(): String {
        return """
            Nome: $nome
            Telefone $phoneType: $telefone1 
            Telefone Celular: $telefone2
            Email: $email
            Receber e-mails: ${if (querReceberEmails) "Sim" else "Não"}
            Gênero: $genero
            Data de Nascimento: $dataNascimento
            Nível de Escolaridade: $nivelEscolaridade
            Ano de conclusão: $anoConclusao
            Instituição: $instituicao
            Monografia: $monografia
            Orientador: $orientador
            Vagas de Interesse: $vagasInteresse
        """.trimIndent()
    }
}