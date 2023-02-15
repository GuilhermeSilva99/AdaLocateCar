package model;

public class Caminhao extends Veiculo{
    public Caminhao(String nome, String placa, Tipo tipo) {
        super(nome, placa);
        this.tipo = tipo;
    }

        @Override
    public String getId() {
        return getPlaca();
    }
}
