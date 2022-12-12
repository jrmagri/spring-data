package br.com.alura.springdata.service;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.FuncionarioProjecao;
import br.com.alura.springdata.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {
    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }


    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual acao deseja executar? ");
            System.out.println("0 - sair");
            System.out.println("1 - Buscar funcionario pelo nome");
            System.out.println("2 - Buscar funcionario pelo nome, salario maior e data de contratação");
            System.out.println("3 - Buscar funcionario por data de contratação");
            System.out.println("4 - Pesquisa funcionario por salario");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                case 3:
                    buscaFuncionarioDataContratacao(scanner);
                case 4:
                    pesquisaFuncionarioSalario();
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaFuncionarioNome(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar?");
        String nome = scanner.next();
        List<Funcionario> list = funcionarioRepository.findByNome(nome);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar?");
        String nome = scanner.next();

        System.out.println("Qual data deseja pesquisar?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        System.out.println("Qual salario deseja pesquisar?");
        Double salario = scanner.nextDouble();


        List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacao(Scanner scanner) {
        System.out.println("Qual data deseja pesquisar?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);
        List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
        list.forEach(System.out::println);
    }

    private void pesquisaFuncionarioSalario() {
        List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
        list.forEach(f -> System.out.println("Funcionario: id: " + f.getID()
                + " | nome: " + f.getNome() + " | salario: " + f.getSalario()));
    }

}
