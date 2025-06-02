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

Neste app, mesmo com os dados da exchange já carregados na lista, optei por criar uma feature separada com GetExchangeByIdUseCase, pois quis simular a presença de dois casos de uso distintos e validar o fluxo completo (repository → usecase → ViewModel → UI) conforme a Clean Architecture. Mas, na prática, se eu estivesse desenvolvendo isso em um produto real, optaria por passar o objeto diretamente para a tela para evitar sobrecarga de rede e manter simplicidade.

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

🧰Módulo de Data – Configuração de Rede

Implementei a camada de rede utilizando Retrofit com Moshi como conversor de JSON, integrando com a CoinAPI. Para a gestão das chamadas HTTP, configurei um OkHttpClient customizado que inclui um interceptor de logging durante o build de debug.

Decidi utilizar o Timber como solução de logging por ser leve, extensível e ideal para ambientes Android. O interceptor registra os detalhes das requisições e respostas, além do tempo de execução de cada chamada, o que facilita bastante a depuração durante o desenvolvimento:

Tomei o cuidado de restringir esse tipo de logging apenas ao ambiente de desenvolvimento para garantir segurança e conformidade com diretrizes como o OWASP Top Ten, evitando vazamento de dados sensíveis em produção.

Essa abordagem mantém a camada de data desacoplada, testável e alinhada aos princípios da Clean Architecture.

No projeto CryptoExchangesApp, optei por transformar o módulo data em um módulo Android (android-library) para permitir o uso de bibliotecas como Retrofit, Timber e outras que exigem o plugin Android.

Apesar disso, mantenho a proteção da arquitetura seguindo os princípios da Clean Architecture e boas práticas de encapsulamento.

✅ Estratégias adotadas:
•	Exposição mínima:
Somente as implementações de interfaces de repositórios são públicas (por exemplo, ExchangeRepositoryImpl).
Todo o restante – como DTOs, APIs Retrofit, mapeadores e utilitários – são marcados como internal, evitando o uso indevido por outros módulos.
•	Separação por responsabilidades:
O módulo data possui pacotes organizados como repository/, remote/, mapper/ e di/, facilitando a manutenção e o controle de visibilidade.
•	Injeção controlada:
Todas as dependências do data são expostas exclusivamente via arquivo DataModule.kt, utilizando o Koin como injetor de dependências.
•	Segurança e qualidade:
Essa abordagem impede que outras partes do app (como UI ou app) tenham acesso direto a detalhes de implementação da camada de dados, garantindo uma arquitetura limpa, testável e de fácil evolução.

Essa decisão ajuda a manter o projeto escalável, sustentável e alinhado com práticas modernas utilizadas em grandes empresas.
⸻

⚠️ Melhorias planejadas para versões futuras:
• Criação de um modulo para Centralização de Dependências tendo em vista. 
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
