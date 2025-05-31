✅ Projeto Android Kotlin com Jetpack Compose — Listagem de Exchanges

🎯 Objetivo

Construir um app robusto, com Clean Architecture, boas práticas de engenharia de software e foco em
qualidade técnica e desacoplamento.

	•	MVVM + Clean Architecture
	•	SOLID, TDD, KISS, DRY, etc.
	•	Testes unitários
	•	Design System
	•	Módulo core com padrão Result, erros globais, UiState
	•	Módulo designsystem
	•	Separação limpa em camadas e módulos

exchanges-app/
├── app/                         # App principal (navegação, DI, UI root)
├── core/                        # Result, Exceptions, UiState
├── designsystem/                # Botões, cards, espaçamentos, tema
├── domain/                      # Entidades e UseCases
├── data/                        # DTOs, Repositories, Retrofit
└── features/
├── exchange_list/          # Lista de exchanges
└── exchange_detail/        # Detalhes da exchange


• MVVM + Clean Architecture
• SOLID, TDD, KISS, DRY, etc.
• Testes unitários
• Design System
• Módulo core com padrão Result, erros globais, UiState
• Módulo designsystem
• Separação limpa em camadas e módulos

⸻

✅ Projeto Android Kotlin com Jetpack Compose — Listagem de Exchanges

🎯 Objetivo

Construir um app robusto, com Clean Architecture, boas práticas de engenharia de software e foco em
qualidade técnica e desacoplamento.

⸻

📦 Estrutura do Projeto (Módulos)

exchanges-app/
├── app/                         # App principal (navegação, DI, UI root)
├── core/                        # Result, Exceptions, UiState, BaseUseCases
├── designsystem/                # Botões, cards, espaçamentos, tema
├── domain/                      # Entidades e UseCases
├── data/                        # DTOs, Repositories, Retrofit
└── features/
├── exchange_list/          # Lista de exchanges
└── exchange_detail/        # Detalhes da exchange

⸻
Por que usar módulos separados na Clean Architecture?

“Uso módulos separados porque isso garante isolamento real de dependências, diferente dos pacotes
internos que permitem qualquer acesso.
Modularizar melhora o tempo de build, facilita testes unitários, reuso de código, e torna o projeto
mais escalável e sustentável.
Também ajuda no trabalho em equipe, pois cada time pode focar em seu módulo sem acoplamento
acidental.
É uma prática comum em apps de produção e favorece uma arquitetura limpa e bem definida.”

🔐 Segurança

Este projeto foi desenvolvido seguindo boas práticas recomendadas pelo OWASP Mobile Top Ten, visando
um app seguro desde as primeiras fases.

✅ Ações já aplicadas:
• Separação clara de responsabilidades (Clean Architecture modular)
Garante que o domínio e a camada de dados não dependam de frameworks Android, reduzindo o risco de
vazamentos e exposição indevida de dados.
• Uso de HTTPS via Retrofit
A API CoinAPI já fornece endpoints seguros por HTTPS, evitando intercepção de dados sensíveis.
• Tratamento centralizado de erros com AppException
Ajuda a prevenir falhas inesperadas e exposição de mensagens de erro para o usuário final.
• Nenhum dado sensível salvo localmente (até o momento)
Não há uso de SharedPreferences, banco de dados ou cache persistente neste MVP.
• Módulo core com Result, UiState e outras proteções estruturais
Reduz chances de crashes e comportamentos inesperados.

⸻

⚠️ Melhorias planejadas para versões futuras:
• Obfuscação com Proguard/R8
Para evitar engenharia reversa em produção e proteger lógica crítica e strings sensíveis.
• Remoção de logs de debug em builds de produção
Evita exposição de informações internas.
• Suporte a SSL Pinning (se necessário)
Para validar a identidade do servidor e mitigar ataques man-in-the-middle.
• Armazenamento seguro (caso necessário)
Se houver armazenamento local no futuro, será usado EncryptedSharedPreferences ou equivalente com
criptografia AES.
• Autenticação segura (caso implementada)
Planeja-se uso de token JWT, OAuth2 ou outras estratégias seguras conforme a necessidade.

🧪 Testes automatizados

Foram implementados testes unitários focando nos casos de uso (`UseCases`) e nas interações com a
camada de dados. Utilizamos boas práticas como o padrão AAA (Arrange, Act, Assert), simulação de
dependências com Test Doubles via MockK, e cobertura dos fluxos principais de sucesso e falha.
