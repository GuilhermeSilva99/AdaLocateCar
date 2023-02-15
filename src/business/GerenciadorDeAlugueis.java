package business;

import business.exception.VeiculoJaAlugadoException;
import model.Aluguel;
import model.Pessoa;
import model.Veiculo;
import persistence.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
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


    public Aluguel alugar(String placa, Pessoa pessoa, LocalDateTime inicio, String local){
        Aluguel aluguel = new Aluguel(pegarId());
        Veiculo veiculo = repositorioDeVeiculos.consultar(placa);
        if(veiculo != null) {
            if (veiculo.isAlugado()) {
                throw new VeiculoJaAlugadoException(veiculo.getPlaca());
            } else {
                veiculo.setAlugado(true);
                aluguel.setPlaca(placa);
                aluguel.setPessoa(pessoa);
                aluguel.setInicioDoAluguel(inicio);
                aluguel.setLocal(local);
            }
            repositorioDeAluguel.salvar(aluguel);
            return aluguel;
        }
        return null;
    }

    public void devolver(String placa, LocalDateTime fim){
        Aluguel aluguel = buscarAluguelPorPlacaDoVeiculo(placa);
        if(aluguel != null){
            aluguel.setFimDoAluguel(fim);
            calcularValorAluguel(aluguel);
            Veiculo veiculo = repositorioDeVeiculos.consultar(aluguel.getPlaca());
            veiculo.setAlugado(false);
            repositorioDeVeiculos.atualizar(veiculo);

        }
    }

    private void calcularValorAluguel(Aluguel aluguel) {
        long dias = calcularDiasAluguel(aluguel);
        BigDecimal valorDaDiaria = BigDecimal.ZERO;
        Veiculo veiculo = repositorioDeVeiculos.consultar(aluguel.getPlaca());
        valorDaDiaria = switch (veiculo.getTipo()){
            case PEQUENO -> new BigDecimal("100");
            case MEDIO -> new BigDecimal("150");
            case SUV -> new BigDecimal("200");
        };
        BigDecimal diasBig = new BigDecimal(dias);
        BigDecimal subTotal = valorDaDiaria.multiply(diasBig);

        BigDecimal desconto = BigDecimal.ZERO;
        if(aluguel.getPessoa().getClass().getSimpleName().equals("PessoaFisica") && dias >= 5){
            desconto = new BigDecimal("0.05").multiply(subTotal);
        }
        if(aluguel.getPessoa().getClass().getSimpleName().equals("PessoaJuridica") && dias >= 3){
            desconto = new BigDecimal("0.1").multiply(subTotal);
        }
        aluguel.setValor(subTotal.subtract(desconto));
    }

    private long calcularDiasAluguel(Aluguel aluguel) {
        Duration duracao  = Duration.between(aluguel.getInicioDoAluguel(), aluguel.getFimDoAluguel());
        long minutos = duracao.toMinutes();
        long dias = minutos/1440;
        if(minutos%1440 > 0){
            dias +=1;
        }
        return dias;
    }

    private Aluguel buscarAluguelPorPlacaDoVeiculo(String placa) {
        List<Aluguel> alugueis = listarTodos();
        for (int i = 0; i < alugueis.size(); i++) {
            if(alugueis.get(i).getPlaca().equals(placa) && alugueis.get(i).getFimDoAluguel()==null){
                return alugueis.get(i);
            }
        }
        return null;
    }

    public void atualizar(Aluguel aluguel){
        repositorioDeAluguel.atualizar(aluguel);
    }
    public List<Aluguel> listarTodos() {
        return repositorioDeAluguel.listarTodos();
    }

    private String pegarId(){
        id +=1;
        return Integer.toString(id);
    }
}
