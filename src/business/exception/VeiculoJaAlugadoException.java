package business.exception;

public class VeiculoJaAlugadoException extends RuntimeException{
    public VeiculoJaAlugadoException(String veiculo) {
        super("Veiculo %s indisponivel para aluguel.".formatted(veiculo));
    }
}
