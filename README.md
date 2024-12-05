# Quiz API Backend

## Descrição

O **Quiz API Backend** é a base para uma plataforma de quizzes interativa. Ele fornece endpoints para criar, gerenciar e jogar quizzes. A aplicação é desenvolvida em **Spring Boot** e utiliza uma arquitetura escalável e orientada a boas práticas de desenvolvimento, incluindo **TDD (Test-Driven Development)**.

---

## Funcionalidades Principais

- **Autenticação de Usuários**:
    - Registro e login de usuários com segurança.
    - Controle de acesso para funcionalidades restritas a usuários autenticados.

- **CRUD de Usuários**:
    - Operações completas (criação, leitura, atualização e exclusão) sobre usuários.
    - Garantia de que apenas o próprio usuário pode gerenciar suas informações.

- **CRUD de Quizzes**:
    - Criação, leitura, atualização e exclusão de quizzes pelos seus respectivos criadores.
    - Restrições de acesso para garantir que apenas o autor do quiz possa editá-lo ou excluí-lo.

- **Jogar Quizzes**:
    - Usuários não autenticados podem acessar quizzes públicos para jogar sem necessidade de registro.
    - Suporte a lógica de perguntas e respostas diretamente no backend.

---

## Arquitetura e Tecnologias

- **Backend**: Spring Boot (Java 22)
- **Banco de Dados**:
    - PostgreSQL para armazenamento de usuários.
    - MongoDB para armazenamento de quizzes.
- **Autenticação**: JWT (JSON Web Tokens) para controle de acesso seguro.
- **Testes**: TDD implementado com foco em cobertura ampla e confiabilidade.

---

## Testes e Boas Práticas

A aplicação adota **Test-Driven Development (TDD)** para garantir qualidade e reduzir regressões. Todos os testes seguem as seguintes diretrizes:

### Tipos de Testes Implementados
1. **Testes Unitários**:
    - Cobertura de métodos e serviços isolados.
    - Mock de dependências críticas.
2. **Testes de Integração**:
    - Validação de interações entre componentes como controladores, serviços e bancos de dados.
3. **Testes de Regressão**:
    - Garantia de que novas alterações não introduzam bugs em funcionalidades existentes.
4. **Testes de Autenticação**:
    - Verificação de segurança no acesso a endpoints restritos.
    - Validação de tokens JWT.
5. **Testes de Regras de Negócio**:
    - Restrições para que apenas criadores editem ou excluam seus quizzes.
    - Garantia de acesso público aos quizzes para usuários não autenticados.

### Frameworks e Ferramentas
- **JUnit**: Framework principal para execução de testes.
- **Mockito**: Criação de mocks e simulação de dependências.
- **Testcontainers**: Utilizado para testes de integração com bancos de dados PostgreSQL e MongoDB.
- **Spring Boot Test**: Suporte para execução de testes de integração nativos do framework.

### Práticas Adotadas
- Escrita de testes antes de implementar funcionalidades.
- Garantia de 100% de cobertura de código crítico.
- Execução automática de testes via CI/CD em cada push.

---

## Próximos Passos

- Implementar um sistema de pontuação para jogadores.
- Suporte a jogos em tempo real com WebSocket.
- Funcionalidades de analytics para quizzes (estatísticas e desempenho).

---

### Checklist de Funcionalidades

#### **1. CRUD de Usuários**
- [x] Criação de usuários (com restrições e validações apropriadas).
- [ ] Leitura de dados do usuário autenticado.
- [ ] Atualização dos dados do próprio usuário.
- [ ] Exclusão de conta de usuário.

#### **2. Autenticação de Usuários**
- [ ] Registro de novos usuários (com validação de dados).
- [ ] Login com geração de tokens JWT.
- [ ] Middleware/Interceptor para validação de usuários autenticados.
- [ ] Endpoint para atualização de senha.
- [ ] Logout (revogação de tokens, se necessário).

#### **3. CRUD de Quizzes**
- [ ] Criação de quizzes:
    - [ ] Validação para que apenas usuários autenticados possam criar.
    - [ ] Estrutura básica do quiz: título, descrição, perguntas e respostas.
- [ ] Leitura de quizzes:
    - [ ] Retornar lista de quizzes públicos.
    - [ ] Retornar detalhes de um quiz específico.
- [ ] Atualização de quizzes:
    - [ ] Restringir edição apenas ao criador do quiz.
- [ ] Exclusão de quizzes:
    - [ ] Garantir que somente o criador possa deletar seu quiz.

#### **4. Jogar Quizzes**
- [ ] Endpoint para iniciar um quiz.
- [ ] Validação das respostas do jogador.
- [ ] Retorno de pontuação e/ou resultados ao final do quiz.

#### **5. Integração com Banco de Dados**
- [ ] Configuração do PostgreSQL para armazenamento de usuários.
- [ ] Configuração do MongoDB para armazenamento de quizzes.

#### **6. Testes Automatizados**
- [ ] Testes unitários para serviços e regras de negócio:
    - [ ] Autenticação.
    - [ ] Validação de permissões (CRUD).
- [ ] Testes de integração para endpoints:
    - [ ] Autenticação e controle de acesso.
    - [ ] CRUD de usuários.
    - [ ] CRUD de quizzes.
- [ ] Testes de regressão para evitar quebra de funcionalidades existentes.

#### **7. Configurações Gerais**
- [ ] Configuração de variáveis de ambiente para credenciais e tokens.
- [ ] Configuração de Docker para ambientes de desenvolvimento.
- [ ] Logs básicos para rastreamento de erros e sucesso.
- [ ] Documentação da API com Swagger ou OpenAPI.

#### **8. Funcionalidades Futuras (Extras)**
- [ ] Sistema de pontuação para usuários autenticados.
- [ ] Rankings ou placares globais.
- [ ] Integração com WebSocket para quizzes em tempo real.