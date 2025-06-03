# 📱 CryptoExchangesApp

Aplicativo para teste técnico, desenvolvido em Android - Kotlin com Jetpack Compose que lista exchanges de criptomoedas usando dados da CoinAPI. Este projeto é um MVP técnico, desenvolvido com foco em Clean Architecture, modularização, TDD e boas práticas de engenharia de software.
---

## 🎯 Objetivo

Construir um app Android robusto, modularizado e orientado a boas práticas de engenharia de software. Os principais pilares adotados foram:

- MVVM + Clean Architecture
- SOLID, TDD, KISS, DRY
- Testes unitários com MockK + Turbine
- Módulo core com Result, UiState, AppException
- Design System básico com Jetpack Compose
- Separação limpa em camadas e módulos
- Modularização real (não apenas por pacotes)

---

## 📦 Estrutura do Projeto

```
exchanges-app/
├── app/              # Navegação, DI, ponto de entrada do app
├── core/             # Result, UiState, AppException
├── designsystem/     # Botões, tema, espaçamentos e componentes visuais
├── domain/           # Entidades e casos de uso
├── data/             # Retrofit, Repositories, Mappers, DTOs
└── features/         # Funcionalidades da aplicação
    ├── exchange_list/    # Tela de listagem de exchanges
    └── exchange_detail/  # Tela de detalhes de uma exchange
```
---

## 🧠 Decisões técnicas

| Decisão                            | Justificativa                                             |
|------------------------------------|------------------------------------------------------------|
| Por que modularizar?               | A modularização garante isolamento real de dependências, melhora o tempo de build, facilita testes, permite o desenvolvimento paralelo entre times e mantém limites claros entre camadas.                       |
| Módulo `data` como android-library | Permitiu uso Timber e outros com Android plugin |

---

## 🔐 Segurança

O projeto foi estruturado considerando recomendações do [OWASP Mobile Top Ten](https://owasp.org/www-project-mobile-top-10/) desde o início:

- ✅ Clean Architecture modularizada: evita vazamento de dados e responsabilidades
- ✅ HTTPS via Retrofit: segurança de transporte garantida
- ✅ Tratamento centralizado de erros com `AppException`
- ✅ Nada sensível é salvo localmente (sem SharedPrefs, Room etc.)
- ✅ Logs restritos apenas para builds de debug
- ✅ Visibilidade controlada com `internal` no módulo `data`

---

## 🧪 Testes Automatizados

- Padrão AAA (Arrange, Act, Assert)
- Testes de UseCases e ViewModels
- Simulação com MockK
- Validação de `Flow` com Turbine
- Cobertura de código com Kover

---

## 🚧 Planejamento futuro (Melhorias)

- [ ] Criar módulo centralizador de dependências
- [ ] Obfuscação com R8/Proguard

---

## 🌐 Camada de Rede

- Retrofit com Moshi (JSON)
- OkHttp com interceptor de logs
- Logging habilitado apenas para debug
- Timber para logs
- Requisições assíncronas e seguras

---

## ✅ CI/CD

- GitHub Actions
- Execução de testes em cada PR
- Relatório de cobertura com Kover

---

## 👨‍💻 Autor

[Lino Veloso](https://github.com/velosobr) – Android Developer
