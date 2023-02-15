package business;

import business.exception.RegistroJaExistenteException;
import business.exception.RegistroNaoEncontradoException;
import business.exception.TipoIncompativoComOVeiculo;
import model.*;
import persistence.Repository;

import java.util.ArrayList;
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
            throw new RegistroNaoEncontradoException("Veiculo",placa);
        }

        Veiculo veiculo = repositorioDeVeiculos.consultar(placa);
        veiculo.setNome(nome);
        repositorioDeVeiculos.atualizar(veiculo);
        return veiculo;
    }

    public Veiculo atualizar(Veiculo veiculo){
        repositorioDeVeiculos.atualizar(veiculo);
        return veiculo;
    }

    public List<Veiculo> buscarPorParteDoNome(String nome){
        List<Veiculo> veiculos = new ArrayList<>();

        List<Veiculo> veiculosCadastrados = listarTodos();

        for (int i = 0; i < veiculosCadastrados.size(); i++) {
            if(veiculosCadastrados.get(i).getNome().contains(nome)){
                veiculos.add(veiculosCadastrados.get(i));
            }
        }
        return veiculos;
    }

    public Veiculo buscarPorId(String id){
        return repositorioDeVeiculos.consultar(id);
    }

}
