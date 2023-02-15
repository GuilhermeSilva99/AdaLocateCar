package business.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(String id) {
        super("Não existe um veiculo com o placa %s.".formatted(id));
    }
}
