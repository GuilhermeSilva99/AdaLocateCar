package view;

import business.GerenciadorDeAlugueis;
import business.GerenciadorDePessoa;
import business.GerenciadorDeVeiculo;
import model.*;
import persistence.AluguelEmMemoriaRepository;
import persistence.PessoaEmMemoriaRepository;
import persistence.Repository;
import persistence.VeiculoEmMemoriaRepository;

import java.time.*;

public class Main {
    public static final String DIVISOR = "--------------------------------------------------------------------------------";
    public static void main(String[] args) {
        Repository<Veiculo> repositorioDeVeiculos = new VeiculoEmMemoriaRepository();
        GerenciadorDeVeiculo gerenciadorDeVeiculo = new GerenciadorDeVeiculo(repositorioDeVeiculos);
        Repository<Pessoa> repositorioDePessoas = new PessoaEmMemoriaRepository();
        GerenciadorDePessoa  gerenciadorDePessoa= new GerenciadorDePessoa(repositorioDePessoas);
        Repository<Aluguel> repositorioDeAluguel = new AluguelEmMemoriaRepository();
        GerenciadorDeAlugueis gerenciadorDeAlugueis = new GerenciadorDeAlugueis(repositorioDeVeiculos, repositorioDePessoas, repositorioDeAluguel);


        //Veiculos
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
        System.out.println(DIVISOR);
        gerenciadorDeVeiculo.listarTodos().forEach(System.out::println);
        System.out.println(DIVISOR);
        gerenciadorDeVeiculo.buscarPorParteDoNome("XRE").forEach(System.out::println);

        gerenciadorDePessoa.adicionarPessoa("Jose Guilherme","09389721466",PessoaFisica.class);
        gerenciadorDePessoa.adicionarPessoa("joao Guilherme","09389721477",PessoaFisica.class);
        gerenciadorDePessoa.editar("09389721477", "zé Guilherme");

        gerenciadorDePessoa.adicionarPessoa("Guilherme TI","854565646",PessoaJuridica.class);
        gerenciadorDePessoa.adicionarPessoa("Gustavo TI","789987",PessoaJuridica.class);
        System.out.println(DIVISOR);
        gerenciadorDePessoa.listarTodos().forEach(System.out::println);
        System.out.println(DIVISOR);
        gerenciadorDePessoa.buscarPorParteDoNome("Gui").forEach(System.out::println);
        System.out.println(DIVISOR);
        gerenciadorDeAlugueis.alugar("AAA0000",
                gerenciadorDePessoa.buscarPorId("09389721466"),
                LocalDateTime.of(2023, Month.FEBRUARY, 15, 14,00),
                "Barra da tijuca");
        gerenciadorDeAlugueis.alugar("ggg",
                gerenciadorDePessoa.buscarPorId("789987"),
                LocalDateTime.of(2023, Month.FEBRUARY, 15, 14,00),
                "Barra de guabiraba");

        gerenciadorDeAlugueis.listarTodos().forEach(System.out::println);
        gerenciadorDeAlugueis.devolver("AAA0000",
                LocalDateTime.of(2023, Month.FEBRUARY, 19, 14,02));
        gerenciadorDeAlugueis.devolver("ggg",
                LocalDateTime.of(2023, Month.FEBRUARY, 17, 14,02));
        System.out.println(DIVISOR);
        gerenciadorDeAlugueis.listarTodos().forEach(System.out::println);
        gerenciadorDeAlugueis.alugar("AAA0000",
                gerenciadorDePessoa.buscarPorId("09389721466"),
                LocalDateTime.of(2023, Month.FEBRUARY, 22, 14,00),
                "Barra de baixo");
        System.out.println(DIVISOR);

        gerenciadorDeAlugueis.listarTodos().forEach(System.out::println);
        gerenciadorDeAlugueis.devolver("AAA0000",
                LocalDateTime.of(2023, Month.FEBRUARY, 23, 14,00));
        System.out.println(DIVISOR);
        gerenciadorDeAlugueis.listarTodos().forEach(System.out::println);
        System.out.println(DIVISOR);
        gerenciadorDeVeiculo.listarTodos().forEach(System.out::println);
        System.out.println(DIVISOR);
        gerenciadorDePessoa.listarTodos().forEach(System.out::println);
    }
}