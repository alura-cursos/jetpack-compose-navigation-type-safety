# Panucci

![thumb-jetpack-compose-navigation-type-safety](https://user-images.githubusercontent.com/8989346/209191268-910e0adf-4281-43a1-9614-d9c2539789db.png)

## 🔨 Funcionalidades do projeto

Uma extensão do [App base](https://github.com/alura-cursos/jetpack-compose-navigation), com a adição da funcionalidade de estado da tela de detalhes:

- **Carregamento**: tela indicando o carregamento
- **Falha**: tela com mensagem de falha e botões para tentar novamente ou voltar
- **Sucesso**: tela de detalhes com as informações esperadas.

![App Panucci navegando nas telas](https://user-images.githubusercontent.com/8989346/210368275-f17b95f7-7853-4372-946a-a2790dc946d6.gif)

## ✔️ Técnicas e tecnologias utilizadas

Para implementar o App foram utilizadas as seguintes funcionalidades e tecnologias:

- Navigation Type Safety:
  - Destinos e grafo aninhado em arquivos exclusivos
  - Rotas e métodos de navegação em cada arquivo
- Gerenciamento com ViewModel e Ui State
- Estados do UI State com sealed class

## 📁 Acesso ao projeto

Você pode [acessar o código fonte do projeto](https://github.com/alura-cursos/jetpack-compose-navigation/tree/aula-5) ou [baixá-lo](https://github.com/alura-cursos/jetpack-compose-navigation-type-safety/archive/refs/heads/aula-5.zip).

## 🛠️ Abrir e rodar o projeto

Após baixar o projeto, você pode abrir com o Android Studio. Para isso, na tela de launcher clique em:

Open an Existing Project (ou alguma opção similar)
Procure o local onde o projeto está e o selecione (Caso o projeto seja baixado via zip, é necessário extraí-lo antes de procurá-lo)
Por fim clique em OK
O Android Studio deve executar algumas tasks do Gradle para configurar o projeto, aguarde até finalizar. Ao finalizar as tasks, você pode executar o App 🏆

<!-- ## 📚 Mais informações do curso

**Faça um CTA (_call to action_) para o curso do projeto**
