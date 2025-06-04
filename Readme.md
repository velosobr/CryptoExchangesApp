# 📱 CryptoExchangesApp
![CI](https://github.com/velosobr/CryptoExchangesApp/actions/workflows/android-ci.yml/badge.svg)
![Kotlin](https://img.shields.io/badge/Kotlin-2.0.0-blue.svg)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.0-blue.svg)

Aplicativo para teste técnico, desenvolvido em Android - Kotlin com Jetpack Compose que lista exchanges de criptomoedas usando dados da CoinAPI. Este projeto é um MVP técnico, desenvolvido com foco em Clean Architecture, modularização, TDD e boas práticas de engenharia de software.
---
## ⚡ TL;DR

Se você quer uma visão rápida do projeto e suas funcionalidades, confira o GIF de demonstração abaixo:

---

### Funcionalidades principais:
- Listagem de exchanges de criptomoedas.
- Detalhes de cada exchange com informações relevantes.
- Design moderno utilizando Jetpack Compose.
- Arquitetura limpa e modularizada.

---

🎥 **Demonstração:**

![demo](https://github.com/user-attachments/assets/1313d14e-7e88-4ef2-99f7-14e010425355)

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
- Testes unitários de UseCases e ViewModels
- Testes de UI com Jetpack Compose Test
- Simulação com MockK
- Validação de `Flow` com Turbine
- Cobertura de código com Kover

---

## 🚧 Planejamento futuro (Melhorias)

- [ ] Criar módulo centralizador de dependências
- [ ] Obfuscação com R8/Proguard
- [ ] Adicionar check e lint ao CI.
- [ ] Integrar com Codecov para exibir cobertura no PR.
- [ ] Gerar cache para acelerar builds com gradle-build-action.

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

## 🔑 Configuração da API

Para que o aplicativo funcione corretamente, é necessário configurar uma chave de API no arquivo `local.properties`. Siga os passos abaixo:

1. Abra o arquivo `local.properties` na raiz do projeto.
2. Adicione a seguinte linha ao arquivo:

   ```ini
   COIN_API_KEY=sua_chave_colada_aqui

## 👨‍💻 Autor

[Lino Veloso](https://github.com/velosobr) – Android Developer
