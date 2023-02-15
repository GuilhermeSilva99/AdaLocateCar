package model;

public abstract class Veiculo implements Entidade{
    private String placa;
    private String nome;
    private boolean alugado;
    protected Tipo tipo;

    public Veiculo(String nome, String placa){
        this.nome = nome;
        this.placa = placa;
        alugado = false;
    }
    public String getPlaca(){
        return placa;
    }
    public String getNome(){
        return nome;
    }
    public boolean isAlugado() {
        return alugado;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", nome='" + nome + '\'' +
                ", alugado=" + alugado +
                ", tipo=" + tipo + '\''+
                ", calsse="+ getClass().getSimpleName() +'\''+
                '}';
    }
}
