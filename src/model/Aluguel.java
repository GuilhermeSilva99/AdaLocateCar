package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Aluguel implements Entidade {
    private String id;
    private BigDecimal valor;
    private LocalDateTime inicioDoAluguel;
    private LocalDateTime fimDoAluguel;
    private String placa;
    private Pessoa pessoa;
    private String local;

    public Aluguel(String id){
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getInicioDoAluguel() {
        return inicioDoAluguel;
    }

    public void setInicioDoAluguel(LocalDateTime inicioDoAluguel) {
        this.inicioDoAluguel = inicioDoAluguel;
    }

    public LocalDateTime getFimDoAluguel() {
        return fimDoAluguel;
    }

    public void setFimDoAluguel(LocalDateTime fimDoAluguel) {
        this.fimDoAluguel = fimDoAluguel;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Aluguel[" +
                "id='" + id + '\'' +
                ", valor=" + valor +
                ", inicioDoAluguel=" + inicioDoAluguel +
                ", fimDoAluguel=" + fimDoAluguel +
                ", veiculo=" + placa +
                ", pessoa=" + pessoa +
                ", local='" + local + '\'' +
                ']';
    }
}
