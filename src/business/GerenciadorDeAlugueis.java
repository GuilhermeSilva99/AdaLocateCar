package business;

import business.exception.VeiculoJaAlugadoException;
import model.Aluguel;
import model.Pessoa;
import model.Veiculo;
import persistence.Repository;

import java.time.LocalDateTime;
import java.util.List;

public class GerenciadorDeAlugueis {
    private Integer id = 0;
    private final Repository<Veiculo> repositorioDeVeiculos;
    private final Repository<Pessoa> repositorioDePessoas;
    private final Repository<Aluguel> repositorioDeAluguel;

    public GerenciadorDeAlugueis(Repository<Veiculo> repositorioDeVeiculos, Repository<Pessoa> repositorioDePessoas, Repository<Aluguel> repositorioDeAluguel) {
        this.repositorioDeVeiculos = repositorioDeVeiculos;
        this.repositorioDePessoas = repositorioDePessoas;
        this.repositorioDeAluguel = repositorioDeAluguel;
    }


    public Aluguel alugar(Veiculo veiculo, Pessoa pessoa, LocalDateTime inicio, String local){
        Aluguel aluguel = new Aluguel(pegarId());

        if(veiculo.isAlugado()){
            throw new VeiculoJaAlugadoException(veiculo.getPlaca());
        }else {
            veiculo.setAlugado(true);
            aluguel.setVeiculo(veiculo);
            aluguel.setPessoa(pessoa);
            aluguel.setInicioDoAluguel(inicio);
            aluguel.setLocal(local);
        }

        repositorioDeAluguel.salvar(aluguel);
        return aluguel;

    }

    public List<Aluguel> listarTodos() {
        return repositorioDeAluguel.listarTodos();
    }

    private String pegarId(){
        id +=1;
        return Integer.toString(id);
    }
}
