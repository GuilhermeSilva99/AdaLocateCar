package business.exception;

import model.Tipo;

public class TipoIncompativoComOVeiculo extends RuntimeException{
    public TipoIncompativoComOVeiculo(String veiculo, Tipo tipo) {
        super("Veiculo %s incompativel com o tipo %s.".formatted(veiculo, tipo));
    }
}
