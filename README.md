# MAESTRO - Gestão de Acervo e Sincronização

> **Candidato:** Elton Marcelino de Lima
> **Contexto:** Prova Prática - Edital N° 001/2026

Solução *Cloud Native* para gestão de artistas e sincronização de regionais, atendendo aos requisitos de portabilidade (Docker), performance (Algoritmo O(n)) e upload de mídia (S3).

## 🚀 Quick Start (Como Rodar)

**Pré-requisitos:** Docker e Docker Compose instalados.

1. Na raiz do projeto, execute:
   ```bash
   docker-compose up --build
   ```
2. Aguarde os logs indicarem que o `maestro-api` e `maestro-web` iniciaram.

## 🔗 Acesso aos Serviços

| Serviço | URL | Credenciais |
| :--- | :--- | :--- |
| **Aplicação Web (Frontend)** | http://localhost:4200 | N/A |
| **Swagger API (Backend)** | http://localhost:8080/q/swagger-ui | N/A |
| **MinIO Console (Storage)** | http://localhost:9001 | `minioadmin` / `minioadmin` |
| **Banco de Dados** | localhost:5432 | `maestro` / `maestro_password` |

## ✅ Guia de Validação (Roteiro de Teste)

### 1. Testar Upload de Imagem (Item 'g' do Edital)
* Acesse o Frontend, cadastre um Artista e faça upload de uma imagem.
* Verifique no **MinIO Console** se o arquivo apareceu no bucket `capas`.

### 2. Testar Sincronização e Histórico (Item 'f' do Edital)
* A sincronização roda automaticamente via *Scheduler*.
* Para forçar: `POST http://localhost:8080/sync/force` via Swagger.
* **Evidência de Histórico:** Consulte o banco e verifique que registros alterados possuem `ativo=false` (versão antiga) e um novo registro foi criado com `ativo=true`.

---
*Para detalhes técnicos da arquitetura e regras de negócio, consulte a pasta `docs/`.*
