package business;

import business.exception.RegistroJaExistenteException;
import business.exception.RegistroNaoEncontradoException;
import business.exception.TipoIncompativoComOVeiculo;
import model.*;
import persistence.Repository;

import java.util.List;

public class GerenciadorDeVeiculo {

    private final Repository<Veiculo> repositorioDeVeiculos;

    public GerenciadorDeVeiculo(Repository<Veiculo> repositorioDeVeiculos) {
        this.repositorioDeVeiculos = repositorioDeVeiculos;
    }

    public <T> Veiculo adicionarVeiculo(String nome, String placa, Tipo tipo, Class<T> classe) {

        if (existeVeiculo(placa)) {
            Veiculo veiculo= repositorioDeVeiculos.consultar(placa);
            throw new RegistroJaExistenteException(veiculo.getClass().getSimpleName(), placa);
        }
        if (classe.getSimpleName().equals("Moto") || classe.getSimpleName().equals("Caminhao")) {
            if (tipo.equals(Tipo.SUV)) {
                throw new TipoIncompativoComOVeiculo(classe.getSimpleName(), tipo);
            }
        }
        Veiculo novoVeiculo = null;
        switch (classe.getSimpleName()) {
            case "Moto":
                novoVeiculo = new Moto(nome, placa, tipo);
                break;
            case "Carro":
                novoVeiculo = new Carro(nome, placa, tipo);
                break;
            case "Caminhao":
                novoVeiculo = new Caminhao(nome, placa, tipo);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + classe.getSimpleName());
        }

        repositorioDeVeiculos.salvar(novoVeiculo);
        return novoVeiculo;
    }

    public boolean existeVeiculo(String placa) {
        return repositorioDeVeiculos.consultar(placa) != null;
    }

    public List<Veiculo> listarTodos() {
        return repositorioDeVeiculos.listarTodos();
    }

    public Veiculo editar(String placa, String nome){
        if (!existeVeiculo(placa)) {
            throw new RegistroNaoEncontradoException(placa);
        }

        Veiculo veiculo = repositorioDeVeiculos.consultar(placa);
        veiculo.setNome(nome);
        repositorioDeVeiculos.atualizar(veiculo);
        return veiculo;
    }

}
