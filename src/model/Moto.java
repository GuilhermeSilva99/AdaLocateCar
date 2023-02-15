package model;

import java.io.Serializable;

public class Moto extends Veiculo implements Serializable {
    public Moto(String nome, String placa, Tipo tipo) {
        super(nome, placa);
        this.tipo = tipo;
    }

      @Override
    public String getId() {
        return getPlaca();
    }
}
