# 7DOC-JAVA

O projeto a seguir foi desenvolvido como parte do desafio 7 Days of Code, uma iniciativa que estimula desenvolvedores a aprimorarem seus conhecimentos em uma tecnologia específica ao longo de sete dias. Neste desafio de Java, o objetivo principal foi consumir APIs para obter dados sobre filmes e séries e exibi-los em um HTML dinâmico. Durante a semana, foram aplicadas diversas técnicas e conceitos essenciais, como manipulação de dados, modelagem de classes, abstração e boas práticas de programação.

## O desenvolvimento do projeto

- **Dia 1**: Consumo de APIs via requisições HTTP e obtenção dos dados no formato JSON.
- **Dia 2**: Processamento e refinamento dos dados utilizando expressões regulares (Regex).
- **Dia 3**: Modelagem do domínio, criando classes e aplicando encapsulamento.
- **Dia 4**: Geração do HTML para exibir os dados processados.
- **Dia 5**: Refatoração do código para melhorar organização e reutilização.
- **Dia 6**: Implementação de abstrações por meio de interfaces.
- **Dia 7**: Implementação de ordenação dos dados.

## Melhorias e alterações

- **Alteração da API**: Inicialmente, o projeto deveria utilizar a IMDB API, mas, devido à mudança no modelo de acesso (agora pago), optei por usar a [TMDB API](https://www.themoviedb.org/). A TMDB oferece um endpoint específico para consultar os melhores filmes. Embora os dados sejam retornados paginados, utilizei um loop para resgatar os primeiros 250 filmes.

- **Dados Exibidos**: Além de filmes, também resgatei as 250 melhores séries via TMDB API e as últimas 100 séries de quadrinhos modificadas, utilizando a [Marvel API](https://developer.marvel.com/). Também adicionei um menu interativo que permite ao usuário escolher qual tipo de conteúdo deseja gerar em HTML e qual tipo de ordenação deseja aplicar aos dados.

- **Estrutura do Projeto**: O projeto foi organizado em duas principais categorias:
  - **Models**: Contém classes representando filmes, séries e séries de quadrinhos.
  - **Services**: Contém diversas classes de serviços, incluindo:
    - **ApiClient**: Responsável pelas requisições HTTP.
    - **JsonParser** (MovieParser, SeriesParser, etc.): Responsáveis por desserializar os dados usando expressões regulares e listas.
    - **HtmlGenerator**: Responsável por gerar um arquivo HTML a partir dos dados processados.
    - **MarvelUrlGenerator**: Gera a URL da API da Marvel (incluindo o timestamp e o hash).
    - **Main**: Controla a execução, exibindo o menu, inicializando dependências, processando os dados e invocando o gerador de HTML.

## Como Utilizar

Para rodar o projeto, não é necessário baixar bibliotecas externas, uma vez que a proposta foi utilizar **Java puro**.

### Instalação do Java

Certifique-se de ter o **Java Development Kit (JDK)** instalado em sua máquina. Você pode baixá-lo [aqui](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html). Após a instalação, verifique se o Java está configurado corretamente rodando o seguinte comando no terminal:

```bash
java -version
```

### Configuração das APIs

- **TMDB API**: Crie uma conta gratuita na [TMDB](https://www.themoviedb.org/) e obtenha sua chave de API.
- **Marvel API**: Crie uma conta na [Marvel Developer API](https://developer.marvel.com/) e obtenha suas chaves públicas e privadas.

Após obter as chaves, defina as variáveis de ambiente no seu sistema com os seguintes nomes:
- **TMDB_KEY** (Chave de API da TMDB)
- **MARVEL_PRIVATE_KEY** (Chave privada da Marvel)
- **MARVEL_PUBLIC_KEY** (Chave pública da Marvel)

Caso não queira usar variáveis de ambiente, você pode inserir as chaves diretamente no código. Para isso, basta substituir o uso de `System.getenv()` nas funções `generate()`, em **MarvelUrlGenerator**, e `generateTmdbUrl()`, em **Main**, com as chaves diretamente inseridas.

### Execução do Projeto

Após a configuração das variáveis ou das chaves, basta executar a aplicação. O menu será exibido, permitindo que você escolha qual conteúdo deseja gerar em HTML e como deseja ordená-lo. O arquivo gerado será salvo na raiz do projeto com o nome `content.html`.
