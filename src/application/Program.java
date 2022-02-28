package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import dialogs.Mensagem;
import entities.Hospede;
import entities.Reserva;
import entities.Suite;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Reserva reservas = new Reserva();

		do {
			List<Hospede> lista = new ArrayList<Hospede>();
			Hospede hospede = null;
			Suite suite = null;
			Mensagem.telaInicial();
			try {
				int numeroDeHospedes = Mensagem.qtdHospedes();
				Mensagem.cadastroDeHospedes();

				// INSERIR DADOS DOS HÓSPEDES
				for (int i = 0; i < numeroDeHospedes; i++) {
					System.out.println(" Dados do hóspede " + (i + 1) + ":");
					int codigo = Mensagem.hospedeID();
					String nome = Mensagem.nomeCompleto();
					String endereco = Mensagem.endereco();
					int idade = Mensagem.idade();
					System.out.println();

					hospede = new Hospede(codigo, nome, endereco, idade);
					lista.add(hospede);
				}

				// INSERIR DADOS DA SUITE
				Mensagem.reservaSuite();
				int numero = Mensagem.numeroSuite();
				String tipo = Mensagem.tipoDeSuite();
				int capacidade = Mensagem.capacidadeDaSuite();
				double valor = Mensagem.valorDaDiaria();

				suite = new Suite(numero, tipo, capacidade, valor);

				if (reservas.validarReserva(suite, lista)) {
					reservas.setQtdPessoas(numeroDeHospedes);
					reservas.setSuite(suite);
					reservas.setQtdDias(Mensagem.quantidadeDeDiarias());
					Mensagem.sucessoReserva();
					System.out.println("   HÓSPEDES");
					System.out.println("   --------");
					for (Hospede h : lista) {
						System.out.println(h);
					}
					System.out.println("   SUÍTE");
					System.out.println("   -----");
					System.out.println(suite);
				}
			} catch (InputMismatchException e) {
				System.out.println("O valor informado é incompatível");
			}

		} while (Mensagem.novaReserva().toUpperCase().equals("S"));
		reservas.imprimirReservas();
		sc.close();
	}
}
