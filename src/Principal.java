import model.entities.Funcionario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // População da lista conforme 3.1
        List<Funcionario> funcionarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/dados.csv"))) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");
                funcionarios.add(new Funcionario(campos[0], LocalDate.parse(campos[1], fmt), new BigDecimal(campos[2]), campos[3]));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Listagem dos Funcionários conforme formato solicitado em 3.3.
        System.out.println("Lista de Funcionários cadastrados:");
        for (Funcionario f : funcionarios) {
            System.out.printf("%-10s %s  R$ %,.2f  %s%n",
                    f.getNome(),
                    f.getDataNascimento().format(fmt),
                    f.getSalario(),
                    f.getFuncao());
        }

        // Remover João da lista conforme 3.2
        funcionarios.removeIf(f -> Objects.equals(f.getNome(), "João"));

        System.out.printf("%n%n");
        System.out.println("João foi removido da lista:");
        for (Funcionario f : funcionarios) {
            System.out.printf("%-10s %s  R$ %,.2f  %s%n",
                    f.getNome(),
                    f.getDataNascimento().format(fmt),
                    f.getSalario(),
                    f.getFuncao());
        }

        // Aumento de 10% do salário conforme 3.4
        funcionarios.forEach(
                f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.1"))));

        System.out.printf("%n%n");
        System.out.println("Todos ganharam 10% de aumento:");
        for (Funcionario f : funcionarios) {
            System.out.printf("%-10s %s  R$ %,.2f  %s%n",
                    f.getNome(),
                    f.getDataNascimento().format(fmt),
                    f.getSalario(),
                    f.getFuncao());
        }

        // Agrupar os funcionários em um Map conforme 3.5 e imprimir funcionarios agrupados por função conforme 3.6
        Map<String, List<Funcionario>> porFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        System.out.printf("%n%n");
        System.out.println("Funcionários agrupados por função:");

        porFuncao.forEach((funcao, lista) -> {
            System.out.println(funcao + ":");
            lista.forEach(f -> System.out.printf("  %-10s %s  R$ %,.2f%n",
                    f.getNome(),
                    f.getDataNascimento().format(fmt),
                    f.getSalario()));
        });

        // Imprimir funcionários que fazem aniversário nos meses 10 e 12 conforme 3.8
        System.out.printf("%n%n");
        System.out.println("Funcionários que nasceram em Outubro e Dezembro:");

        for (Funcionario f : funcionarios) {
            if (f.getDataNascimento().getMonth() == Month.OCTOBER || f.getDataNascimento().getMonth() == Month.DECEMBER) {
                System.out.printf("  %-10s %s%n", f.getNome(), f.getDataNascimento().format(fmt));
            }
        }

        // Imprimir o funcionário com a maior idade conforme 3.9
        Funcionario maiorIdade = funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow();

        System.out.printf("%n%n");
        System.out.printf("Funcionario com maior idade: %n  %-10s %s", maiorIdade.getNome(), maiorIdade.getDataNascimento().format(fmt));


        // Imprimir lista de funcionários na ordem alfabética conforme 3.10.
        System.out.printf("%n%n");
        System.out.println("Lista de Funcionários na Ordem Alfabética: ");

        List<Funcionario> ordenados = funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).toList();
        for (Funcionario f : ordenados) {
            System.out.printf("  %-10s %n", f.getNome());
        }

        // Imprimir total dos salários dos funcionários conforme 3.11
        BigDecimal soma = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            soma = soma.add(f.getSalario());
        }

        System.out.printf("%n%n");
        System.out.printf("Total dos salários dos funcionários: R$ %,.2f", soma);

        // Imprimir quantos salários mínimos ganha cada funcionário conforme 3.12
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.printf("%n%n");
        for (Funcionario f : funcionarios) {
            BigDecimal quantidade = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_EVEN);
            System.out.printf("%n %10s recebe %,.2f salários mínimos", f.getNome(), quantidade);
        }

        System.out.printf("%n%n");
    }
}

