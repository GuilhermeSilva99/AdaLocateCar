package view;

import business.GerenciadorDeVeiculo;
import model.*;
import persistence.Repository;
import persistence.VeiculoEmMemoriaRepository;

public class Main {
    public static void main(String[] args) {
        Repository<Veiculo> repositorioDeVeiculos = new VeiculoEmMemoriaRepository();
        GerenciadorDeVeiculo gerenciadorDeVeiculo = new GerenciadorDeVeiculo(repositorioDeVeiculos);
        //Motos
        gerenciadorDeVeiculo.adicionarVeiculo("XRE300", "AAA0000", Tipo.MEDIO, Moto.class);
        gerenciadorDeVeiculo.adicionarVeiculo("XRE302220", "aaa", Tipo.PEQUENO, Moto.class);
        gerenciadorDeVeiculo.editar("aaa","XRE190");
        //Carros
        gerenciadorDeVeiculo.adicionarVeiculo("Civic", "AAA0002", Tipo.MEDIO, Carro.class);
        gerenciadorDeVeiculo.adicionarVeiculo("onix", "bbb", Tipo.PEQUENO, Carro.class);
        gerenciadorDeVeiculo.adicionarVeiculo("hilux", "ccc", Tipo.SUV, Carro.class);
        gerenciadorDeVeiculo.editar("bbb","onix turbo");
        //Caminhao
        gerenciadorDeVeiculo.adicionarVeiculo("Bob", "CCA0002", Tipo.MEDIO, Caminhao.class);
        gerenciadorDeVeiculo.adicionarVeiculo("1113", "dddd", Tipo.PEQUENO, Caminhao.class);
        gerenciadorDeVeiculo.adicionarVeiculo("cargo", "ggg", Tipo.MEDIO, Caminhao.class);
        gerenciadorDeVeiculo.editar("ggg","cargo truck");
        System.out.println("---------------------------");
        gerenciadorDeVeiculo.listarTodos().forEach(System.out::println);
        System.out.println("_______________________________________________");
        gerenciadorDeVeiculo.buscarPorParteDoNome("XRE").forEach(System.out::println);
        System.out.println("Hello world!");
    }
}