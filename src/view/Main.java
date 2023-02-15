package view;

import model.Moto;
import model.Tipo;
import model.Veiculo;
import persistence.Repository;
import persistence.VeiculoEmMemoriaRepository;

public class Main {
    public static void main(String[] args) {
        Repository<Veiculo> repositorioDeVeiculos = new VeiculoEmMemoriaRepository();
        
        Moto moto = new Moto("XRE300","RZO2E21", Tipo.MEDIO);

        System.out.println("Hello world!");
    }
}