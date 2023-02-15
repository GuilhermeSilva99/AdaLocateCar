package model;

public class Moto extends Veiculo{
    public Moto(String nome, String placa, Tipo tipo) {
        super(nome, placa);
        this.tipo = tipo;
    }

    @Override
    protected boolean analisarTipoPorVeiculo(Tipo tipo) {
        if(tipo.equals(Tipo.SUV)){
            return false;
        }
        return true;
    }

    @Override
    public String getId() {
        return getPlaca();
    }
}
