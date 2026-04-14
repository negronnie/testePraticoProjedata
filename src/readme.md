# Teste Prático Programação

Na minha abordagem optei por criar um arquivo csv para importar os dados de forma mais consistente evitando uma quantidade desnecessária de boilerplate, dessa forma o código fica um pouco mais legível, na minha opinião. Sem contar que neste caso sou obrigado a sanitizar o arquivo, pulando a primeira linha (cabeçalho).

Na tarefa 3.2, eu optei por usar a lambda para mostrar uma abordagem diferente do for each, já que a outra abordagem seria a seguinte:

````java
for (Funcionario f : funcionarios) {
    if (Objects.equals(f.getNome(), "João") {
        funcionarios.remove();
    }
}
````

O mesmo serve para a tarefa 3.4, onde poderia ter feito uma abordagem semelhante, mas optei pelo _Consumer_ **forEach**.
Em algumas funções esse tipo de abordagem me parece facilitar a leitura. 

O BigDecimal eu poderia ter colocado em uma variável para limpar ainda mais, mas achei desnecessário, conhecendo a implementação do mesmo, acho que não dificulta tanto.

Na tarefa 3.5, do Map, eu já tive um pouco mais de fricção, pois ainda não usei muito o map. O mesmo aconteceu no método _divide()_ do BigDecimal da tarefa 3.12, pois não sabia que ele exigia um argumento de arredondamento. Nessa aí o IntelliJ me salvou :)
