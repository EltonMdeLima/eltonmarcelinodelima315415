# Regras de Negócio e Rastreabilidade

Mapeamento de como o código atende aos itens específicos do **Edital N° 001/2026 (Anexo II-C)**.

## 1. Matriz de Rastreabilidade

| Item do Edital | Solução Implementada | Arquivo Fonte |
| :--- | :--- | :--- |
| **Item b. (Docker)** | Solução entregue 100% em containers. | `docker-compose.yml` |
| **Item f. (Sync)** | Job agendado que consome API externa. | `SyncService.java` |
| **Item f.iii (Histórico)** | Lógica de "Inativar anterior / Criar novo". | `SyncService.java` |
| **Item g. (Upload)** | Persistência em Object Storage (MinIO). | `MinioService.java` |
| **Performance** | Algoritmo O(n) usando HashMaps. | `DiffAlgorithm.java` |

## 2. Regras de Sincronização (Core Logic)

Para garantir performance linear **O(n)** e não quadrática O(n²), o algoritmo segue estes passos:

1. **Carregar Memória:** Busca todos os registros locais ativos em um `Map<ExternalID, Regional>`.
2. **Iteração Única:** Percorre a lista da API Externa uma única vez.
3. **Comparação (Diff):**
   * Se ID não existe no Map local -> **INSERT**.
   * Se ID existe mas Hash (conteúdo) é diferente -> **VERSIONAR** (Update Old + Insert New).
   * Se ID existe e Hash é igual -> **NO-OP** (Ignorar).
4. **Limpeza:** Itens que sobraram no Map local (não vieram na API) -> **INATIVAR**.
