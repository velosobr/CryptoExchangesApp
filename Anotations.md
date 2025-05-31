
🟩 Fase 1 – Setup Inicial do Projeto
1.	Criar projeto ExchangeListApp em Jetpack Compose
2.	Configurar:
      •	Kotlin DSL (se preferir)
      •	Compose Material 3
      •	Hilt
      •	Navigation
      •	Retrofit + Moshi
      •	Coroutines
      •	Coil (opcional)
      •	Testes: JUnit5, MockK, Turbine, ComposeTest
3.	local.properties com API_KEY
      •	Adicionar no build.gradle:

buildConfigField("String", "API_KEY", "\"${project.property("COIN_API_KEY")}\"")



⸻

🟦 Fase 2 – Camada core/ (Base do Projeto)

Objetivo: Centralizar estruturas reutilizáveis e padrões globais.

Conteúdo:
•	Result<T>: Wrapper de sucesso/erro
•	AppException: Hierarquia de erros (network, api, etc.)
•	UiState<T>: Loading, Success, Error
•	BaseUseCase<I, O>
•	Extensões úteis

sealed class Result<out T> {
data class Success<T>(val data: T) : Result<T>()
data class Error(val exception: AppException) : Result<Nothing>()
}

sealed class AppException(message: String) : Exception(message) {
object Network : AppException("Network error")
data class Unknown(val error: Throwable) : AppException(error.message ?: "Unknown")
}

sealed class UiState<out T> {
object Loading : UiState<Nothing>()
data class Success<T>(val data: T) : UiState<T>()
data class Error(val message: String) : UiState<Nothing>()
}


⸻

🎨 Fase 3 – Módulo designsystem/

Objetivo: Garantir consistência visual e produtividade

Componentes iniciais:
•	AppTheme.kt, Color.kt, Typography.kt, Spacing.kt
•	PrimaryButton.kt
•	ExchangeCard.kt
•	ErrorBox.kt
•	PreviewTheme para visualização no Compose Preview

⸻

🟨 Fase 4 – Camada domain/

1. Entidade Exchange

data class Exchange(
val id: String,
val name: String,
val volume24hUsd: Double,
val website: String?,
val rank: Int
)

2. UseCases
   •	GetExchangesUseCase
   •	GetExchangeByIdUseCase (ou filtrar da lista)

⸻

🟧 Fase 5 – Camada data/

1. DTOs

@JsonClass(generateAdapter = true)
data class ExchangeDto(
@Json(name = "exchange_id") val id: String,
val name: String,
val website: String?,
@Json(name = "volume_1day_usd") val volume24hUsd: Double,
val rank: Int
)

2. Mapeadores

fun ExchangeDto.toDomain() = Exchange(
id = id,
name = name,
volume24hUsd = volume24hUsd,
website = website,
rank = rank
)

3. CoinApiService

interface CoinApiService {
@GET("v1/exchanges")
suspend fun getExchanges(@Header("X-CoinAPI-Key") apiKey: String): List<ExchangeDto>
}

4. ExchangeRepositoryImpl

Responsável por consumir a API, converter DTO para Entity e tratar erros.

⸻

🟫 Fase 6 – Camada features/

1. exchange_list/
   •	ExchangeListViewModel
   •	Usa GetExchangesUseCase e expõe StateFlow<UiState<List<Exchange>>>
   •	View com LazyColumn, ExchangeCard, loading, erro com retry

2. exchange_detail/
   •	ExchangeDetailViewModel
   •	Recebe exchangeId
   •	Busca dados do repositório (ou filtra da lista)
   •	Exibe nome, site, volume, rank

⸻

🟥 Fase 7 – Navegação e App Layer

Em app/:
•	MainActivity.kt configura Hilt + Compose
•	Navigation entre ExchangeList → ExchangeDetail
•	Usar NavController + NavType.StringType + safe args

⸻

🧪 Fase 8 – Testes

1. Testes de unidade
   •	UseCases
   •	Repository (mock API)
   •	ViewModels com StateFlow, Turbine, etc.

2. Testes de UI (se houver tempo)
   •	ComposeTest: título, erro, botão retry
   •	Snapshot testing (se desejar ir além)

⸻

📘 Fase 9 – README Final

Conteúdo sugerido:
•	✅ Arquitetura usada
•	🧱 Estrutura modular e Clean Architecture
•	🔍 Camadas, responsabilidades
•	🔐 Gerenciamento de chaves
•	🧪 Testes escritos
•	🖼️ Imagens do app (opcional)
•	👨‍💻 Como rodar: local.properties, API_KEY etc.
•	📌 Decisões técnicas, tradeoffs

⸻
