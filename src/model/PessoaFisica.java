package model;

public class PessoaFisica extends Pessoa{
    private String CPF;
    public PessoaFisica(String nome, String CPF) {
        super(nome);
        this.CPF = CPF;
    }

    @Override
    public String getId() {
        return this.CPF;
    }

    @Override
    public String toString() {
        return "Pessoa[" +
                "nome='" + getNome() + '\'' +
                ", CPF='" + CPF + '\'' +
                ", calsse="+ getClass().getSimpleName() +'\''+
                ']';
    }
}
