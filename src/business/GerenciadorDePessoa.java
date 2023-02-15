package business;

import business.exception.RegistroJaExistenteException;
import business.exception.RegistroNaoEncontradoException;
import model.*;
import persistence.Repository;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDePessoa {

    private final Repository<Pessoa> repositorioDePessoas;

    public GerenciadorDePessoa(Repository<Pessoa> repositorioDePessoas) {
        this.repositorioDePessoas = repositorioDePessoas;
    }

    public <T> Pessoa adicionarPessoa(String nome, String id, Class<T> classe) {

        if (existePessoa(id)) {
            Pessoa Pessoa= repositorioDePessoas.consultar(id);
            throw new RegistroJaExistenteException(Pessoa.getClass().getSimpleName(), id);
        }

        Pessoa novaPessoa = null;
        switch (classe.getSimpleName()) {
            case "PessoaFisica":
                novaPessoa = new PessoaFisica(nome, id);
                break;
            case "PessoaJuridica":
                novaPessoa = new PessoaJuridica(nome, id);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + classe.getSimpleName());
        }

        repositorioDePessoas.salvar(novaPessoa);
        return novaPessoa;
    }

    public boolean existePessoa(String placa) {
        return repositorioDePessoas.consultar(placa) != null;
    }

    public List<Pessoa> listarTodos() {
        return repositorioDePessoas.listarTodos();
    }

    public Pessoa editar(String id, String nome){
        if (!existePessoa(id)) {
            throw new RegistroNaoEncontradoException("Pessoa",id);
        }

        Pessoa Pessoa = repositorioDePessoas.consultar(id);
        Pessoa.setNome(nome);
        repositorioDePessoas.atualizar(Pessoa);
        return Pessoa;
    }

    public List<Pessoa> buscarPorParteDoNome(String nome){
        List<Pessoa> Pessoas = new ArrayList<>();

        List<Pessoa> PessoasCadastrados = listarTodos();

        for (int i = 0; i < PessoasCadastrados.size(); i++) {
            if(PessoasCadastrados.get(i).getNome().contains(nome)){
                Pessoas.add(PessoasCadastrados.get(i));
            }
        }
        return Pessoas;
    }

}
