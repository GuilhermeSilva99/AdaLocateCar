package model;

public class PessoaJuridica  extends Pessoa{
    private String CNPJ;
    public PessoaJuridica(String nome, String CNPJ) {
        super(nome);
        this.CNPJ = CNPJ;
    }

    @Override
    public String getId() {
        return this.CNPJ;
    }

    @Override
    public String toString() {
        return "Pessoa[" +
                "nome='" + getNome() + '\'' +
                ", CNPJ='" + CNPJ + '\'' +
                ", calsse="+ getClass().getSimpleName() +'\''+
                ']';
    }
}
