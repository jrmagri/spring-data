package br.com.alura.springdata.service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private Boolean system = true;
    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository repository) {
        this.cargoRepository = repository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual acao de cargo deseja executar? ");
            System.out.println("0 - sair");
            System.out.println("1 - salvar");
            System.out.println("2 - atualizar");
            System.out.println("3 - visualizar");
            System.out.println("4 - deletar");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }

        }
        salvar(scanner);
    }

    public void salvar(Scanner scanner) {
        System.out.println("Descricao do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Salvo");
    }

    public void atualizar(Scanner scanner) {
        System.out.println("id");
        int id = scanner.nextInt();
        System.out.println("Descricao do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Atualizado");
    }

    public void visualizar() {
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));
    }

    private void deletar(Scanner scanner) {
        System.out.println("id");
        int id = scanner.nextInt();
        cargoRepository.deleteById(id);
        System.out.println("Deletado!");
    }
}
