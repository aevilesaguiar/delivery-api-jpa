#  MODELAGEM DE REST API - Parte 2  

## Assuntos Abordados 

- Spring Data JPA



## Projeto a ser Desenvolvido

Sistema de Delivery de Comida, tipo Ifood/Uber Eats, Será um MVP(Produto minimo Viavel) de sistema de comidas;


## Tecnologias

- Maven
- Spring Data JPA (biblioteca que vai ajudar a criar repositorio para usa o jakarta persistence)
- POSTMAN (para testar uma restApi, as requisições)
- Mysql Server
- Flyway (ferramenta para gerenciamento de schema de banco de dados, para manutenção das colunas, tabelas relacionamentos e etc)
- Spring Boot DevTools (restarta a aplicação automaticamente)

- 
## Spring Data JPA

Quando implementamos o nosso código existe muito código boilerPlate, ous eja código repetitivo. Nas implementações dos nossos
códigos nós temos muita coisa repetida.

O spring Dtaa JPA é um subprojeto do spring Data, ele é focado em ajudar a implementar repositórios JPA com muito mais produtividade, 
eliminando boa parte do código boiler plate, para criar um repositório com spring data JPA, basta que criemos uma interface 
que herda outra interface do próprio spring data jpa. A nossa interface que herda uma interface dele. Em tempo de execução o Spring
Data JPA instancia para nós uma implementação com vários métodos, como de pesquisa, paginação e outros. E quando queremos um novo
método de consulta que ele não fornece, então temos que criar esse método de consulta, e mesmo assim na maioria das vezes temos apenas
declarar a assinatura na interface que não tem implementação e ele mesmo fornece uma implementação em tempo de execução.

Temos apenas uma interface sem classe implementando ela. O spring Data JPA implementa para nós. O Spring Data JPA ajuda a eliminar
código boiler plate nós não perdemos o controle, pois podemos criar metodos customizados.

Ele fornece uma estrutura que funciona com JPA e fornece uma abstração completa sobre a camada de acesso a dados . O Spring Data JPA traz
o conceito de Repositórios JPA, um conjunto de Interfaces que define métodos de consulta. O Repository e o Entity Bean representam a 
camada DAO no aplicativo.


Spring Data NÃO é uma especificação ou padrão, mas é uma abstração


O que é Spring Data JPA?
O  Spring Data JPA  é um dos muitos  projetos Spring Data  e visa trazer consistência no acesso a dados para datastores 
relacionais. Muitas pessoas consideram o Spring Data JPA como uma implementação de JPA. Na realidade, é falso. O Spring Data 
JPA usa uma implementação de JPA padrão chamada Hibernate. A implementação padrão do JPA é configurável e, se desejarmos, 
também podemos usar outras implementações.

Falando com precisão, o Spring Data JPA é um complemento para o JPA. Ele fornece uma estrutura que funciona com JPA e fornece
uma abstração completa sobre a camada de acesso a dados.

O Spring Data JPA traz o conceito de  Repositórios JPA , um conjunto de  Interfaces  que define métodos de consulta . 
O Repository e o Entity Bean representam a camada DAO no aplicativo. Não há mais necessidade de escrever consultas nativas. 
Às vezes, precisamos escrever consultas ou parte de consultas, mas essas são consultas JQL e não consultas de banco de dados
nativas.

Abstração DAO (Repositórios)
Com o Spring Data JPA , não precisamos escrever uma camada de acesso a dados ou escrever qualquer instrução SQL. Com base 
na especificação JPA , a implementação JPA subjacente habilita os objetos Entity e seu mapeamento de metadados. Também 
permite um gerenciador de entidades responsável por persistir e recuperar entidades do banco de dados .

Métodos de consulta
Os repositórios no Spring Data JPA fornecem uma maneira robusta e descomplicada de escrever consultas SQL Query Methods . 
O nome dos métodos declarados nas interfaces do repositório é convertido em consultas SQL de baixo nível pelo Spring.

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();

    Optional<Employee> findById(Long employeeId);

    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    List<Employee> findByDepartmentNameAndAgeLessThanOrderByDateOfJoiningDesc(String departmentName, int maxAge);

}
O primeiro método será select * from employees.
O segundo método coloca uma cláusula where no employeeIdcampo.
O terceiro tem duas cláusulas WHERE, cada uma para nome e sobrenome.
A última é uma consulta mais complexa que encontra uma lista de funcionários por nome de departamento e mais recente 
que a data especificada e classifica os resultados em ordem decrescente de data de ingresso.


- Estamos no mundo de diversas tecnologias de armazenamento de banco de dados, e cada uma vem com sua API de acesso a dados e drivers.
- O Spring Data e seus subprojetos fornecem uma maneira uniforme de acessar vários armazenamentos de dados.
- Java Persistence API ( JPA ) é uma especificação fornecida pelo Java para APIs que acessam vários bancos de dados SQL.
- ORM significa Mapeamento Relacional de Objetos, onde um Objeto Java mapeia para uma entidade de banco de dados, e com APIs, 
precisamos trabalhar com objetos e não com consultas nativas.
- Spring Data JPA é um subprojeto do Spring Data e fornece uma abstração sobre a camada de acesso a dados usando Java 
Persistence API e implementações ORM como Hibernate .



JpaRepository é uma interface e possui métodos especificos do JPA, mas ela herda outra interface chamada PagingAndSortingRepository
essa interface é responsável por fornecer métodos que trabalham com ordenação(findAll(sort)) e com paginação(findAll(Pageable pageable)).
E a PagingAndSortingRepository herda a outra interface chamada CrudRepository o qual possui as operações básicas de um repositório.
E CRudRepository herda outra interface  a qual não declara nenhum metodo.
Ou seja quando nossa interface herda JpaRepository ela herda todas as outras interfaces, por que é uma herança.

Podemos não necessariamente herdar JpaRepository, poderimaos herdar CrudRepository depende do projeto.


## Optional Java

Optional é uma classe que foi implementada no Java 8, que tem o objetivo de simplificar os códigos, facilitando a vida 
dos desenvolvedores.
A finalidade da classe é fornecer uma solução de nível de tipo para representar valores opcionais em vez de  referências nulas .

O Optional nos ajuda a evitar os erros NullPointerException, tirar a necessidade da verificação (if x != null) e também 
a escrever um código com menos linhas e mais bonito.


## Criando Objetos Opcionais

Para criar um objeto Optional vazio , simplesmente precisamos usar seu método estático empty() :

@Test
public void whenCreatesEmptyOptional_thenCorrect() {
Optional<String> empty = Optional.empty();
assertFalse(empty.isPresent());
}

Observe que usamos o método isPresent() para verificar se existe um valor dentro do objeto Optional . 
Um valor está presente somente se tivermos criado Optional com um valor não nulo . Veremos o método isPresent() na próxima seção.

Também podemos criar um objeto Optional com o método estático  of() :

@Test
public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
String name = "baeldung";
Optional<String> opt = Optional.of(name);
assertTrue(opt.isPresent());
}

No entanto, o argumento passado para o método of() não pode ser nulo. Caso contrário, obteremos um NullPointerException :

@Test(expected = NullPointerException.class)
public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
String name = null;
Optional.of(name);
}

Mas caso esperemos alguns valores nulos , podemos usar o método ofNullable() :

@Test
public void givenNonNull_whenCreatesNullable_thenCorrect() {
String name = "baeldung";
Optional<String> opt = Optional.ofNullable(name);
assertTrue(opt.isPresent());
}
Ao fazer isso, se passarmos uma referência nula , ela não lançará uma exceção, mas retornará um objeto Optional vazio:

@Test
public void givenNull_whenCreatesNullable_thenCorrect() {
String name = null;
Optional<String> opt = Optional.ofNullable(name);
assertFalse(opt.isPresent());
}





## Vamos ver alguns exemplos de uso de Optional:

empty
Retorna uma instância de Optional vazia.

Optional<Pessoa> pessoaEmpty = Optional.empty();
System.out.println(pessoaEmpty);

of
Retorna um Optional com o valor pedido pelo método. O retorno não pode ser nulo.

Optional<Integer> pessoaOf = Optional.of(pessoaStream.mapToInt(p-> p.getIdade()).min().getAsInt());
System.out.println(pessoaOf);

ofNullable
Retorna um Optional com o valor pedido pelo método. O valor pode ser nulo ou não. Esse método é o mais usado.

Optional<Integer> pessoaOfNullable = Optional.ofNullable(pessoaStream.mapToInt(p-> p.getIdade()).min().getAsInt());
System.out.println(pessoaOfNullable);


ifPresent
O ifPresent faz um teste para saber se uma situação está ou não presente. Por exemplo, para saber se determinado funcionário está ou não cadastrado.

Optional<String> buscaMatricula = Optional.ofNullable(buscaPorMatricula(""));
if (buscaMatricula.isPresent())
System.out.println("Funcionário encontrado");
else
System.out.println("Funcionário não encontrado");

System.out.println(buscaMatricula);


Reduce

Se quisermos somar as idades das pessoas com idade acima de 30 anos, podemos usar o sum, como vemos no trecho de código abaixo:

int result = streamPessoa.filter(p-> p.idade > 30).mapToInt(p-> p.idade).sum();
System.out.println(result);
Como temos duas pessoas com mais de 30 anos de idade (Daiane, 34 e Daniel, 35) obtivemos uma saída, que é a soma das duas idades: 69

Mas se, dessa forma, tentarmos somar as idades de das pessoas acima de 40 anos:


int result = streamPessoa.filter(p-> p.idade > 40).mapToInt(p-> p.idade).sum();
System.out.println(result);
Como não temos nenhuma pessoa, a saída será um número inteiro: 0.

Mas, nesse caso, como não temos pessoas com a idade acima de 40 anos, não seria mais coerente um resultado vazio? Por isso, vamos usar o Optional reduce, como no exemplo abaixo:

OptionalInt result = streamPessoa.filter(p -> p.idade > 40).mapToInt(p-> p.idade).reduce((a, b) -> a + b);
if (result.isPresent()) {
System.out.println(result.getAsInt());
Dessa forma, o Java nos retornará um Optional vazio.

Como podemos ver, o Optional nos ajuda a evitar retorno de inteiro = 0 (quando não esperado), e principalmente o temido NullPointerException. 
Além de deixar o código mais bonito e poder ser combinado com vários métodos.


## Valor padrão com orElse()

O método orElse() é usado para recuperar o valor encapsulado em uma instância Optional . 
Leva um parâmetro, que atua como um valor padrão. O método orElse() retorna o valor encapsulado se estiver presente e seu argumento caso contrário:

@Test
public void whenOrElseWorks_thenCorrect() {
String nullName = null;
String name = Optional.ofNullable(nullName).orElse("john");
assertEquals("john", name);
}


##  Valor padrão com orElseGet()

O método orElseGet()  é semelhante ao orElse() . Porém, ao invés de pegar um valor para retornar se o valor Optional não
estiver presente, ele pega uma interface funcional do fornecedor, que é invocada e retorna o valor da invocação:

@Test
public void whenOrElseGetWorks_thenCorrect() {
String nullName = null;
String name = Optional.ofNullable(nullName).orElseGet(() -> "john");
assertEquals("john", name);
}



## Observações Importantes

@Service é apenas para adicionar semantica ao código já o @Repository não  ela tem uma funcionalidade que é um tradutor de exceptions,
ou seja, existe uma classe que gerencia/intercepta esses métodos, e se por acaso ela encontrar algumas exceptions de determinado tipo
ela pode traduzir para exceptions de outro tipo especifico do próprio spring. Quando alteramos @Component para @Repository temos que 
testar nosso código pois ele pode lançar Exceções diferentes 


- JPQL é a linguagem de consulta JPQA

Faremos uma busca por nome usando JPQL . No POSTMAN passaremos por exemplo: localhost:8080/teste/cozinhas/por-nome/nome=Tailandesa . 
Ou seja passaremos por parametros de query. No postman temos os querys Params


## Referencias

- https://www.baeldung.com/java-optional