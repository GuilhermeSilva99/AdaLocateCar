package model;

public class Carro extends Veiculo{

    public Carro(String nome, String placa, Tipo tipo) {
        super(nome, placa);
        this.tipo = tipo;
    }

    @Override
    protected boolean analisarTipoPorVeiculo(Tipo tipo) {
        return true;
    }

    @Override
    public String getId() {
        return getPlaca();
    }
}
