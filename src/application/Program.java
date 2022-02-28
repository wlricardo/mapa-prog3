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
import exceptions.ValorInvalidoExeception;

public class Program {

	public static void main(String[] args) throws ValorInvalidoExeception {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Reserva reservas = new Reserva();

		do {
			List<Hospede> lista = new ArrayList<Hospede>();
			Hospede hospede = null;
			Suite suite = null;
			int numeroDeHospedes = 0;
			
			Mensagem.telaInicial();
			
			do {
				try {
					/* Verificação do número de hóspedes */
					numeroDeHospedes = Mensagem.qtdHospedes();
					// reservas.setQtdPessoas(numeroDeHospedes);
					Mensagem.cadastroDeHospedes();
					break;
				} catch (InputMismatchException e) {
					System.out.println("\n   ** Erro ! Informe apenas valores numéricos **\n");
				} catch (ValorInvalidoExeception e) {
					System.out.println(e.getMessage());
				}
			} while (true);

			// INSERIR DADOS DOS HÓSPEDES
			for (int i = 0; i < numeroDeHospedes; i++) {
				System.out.println(" Dados do hóspede " + (i + 1) + ":");

				/* Verificação da identificação do hóspede */
				int codigo;
				do {
					try {
						codigo = Mensagem.hospedeID();
						break;
					} catch (InputMismatchException e) {
						System.out.println("\n   ** Erro ! Informe apenas valores numéricos **\n");
					} catch (ValorInvalidoExeception e) {
						System.out.println(e.getMessage());
						// System.out.println("\n ** Erro! Código do cliente não pode ser nulo **\n");
					}
				} while (true);

				/* Verificação do nome do hóspede */
				String nome;
				do {
					try {
						nome = Mensagem.nomeCompleto();
						break;
					} catch (ValorInvalidoExeception e) {
						System.out.println(e.getMessage());
					}
				} while (true);

				String endereco = Mensagem.endereco();

				/* Verificação da idade do hóspede */
				int idade;
				do {
					try {
						idade = Mensagem.idade();
						break;
					} catch (InputMismatchException e) {
						System.out.println("\n   ** Erro ! Informe apenas valores numéricos **\n");
					} catch (ValorInvalidoExeception e) {
						System.out.println(e.getMessage());
					}
				} while (true);
				System.out.println();

				hospede = new Hospede(codigo, nome, endereco, idade);
				lista.add(hospede);
			}

			// INSERIR DADOS DA SUITE
			Mensagem.reservarSuite();

			/* Verificando número da suite */
			int numero;
			do {
				try {
					numero = Mensagem.numeroSuite();
					break;
				} catch (InputMismatchException e) {
					System.out.println("\n   ** Erro ! Informe apenas valores numéricos **\n");
				} catch (ValorInvalidoExeception e) {
					System.out.println(e.getMessage());
				}
			} while (true);

			String tipo = Mensagem.tipoDeSuite();

			/* Verificar capacidade da suite */
			int capacidade;
			do {
				try {
					capacidade = Mensagem.capacidadeDaSuite();
					break;
				} catch (InputMismatchException e) {
					System.out.println("\n   ** Erro ! Informe apenas valores numéricos **\n");
				} catch (ValorInvalidoExeception e) {
					System.out.println(e.getMessage());
				}
			} while (true);

			/* Verificar valor da suite */
			double valor;
			do {
				try {
					valor = Mensagem.valorDaDiaria();
					break;
				} catch (InputMismatchException e) {
					System.out.println("\n   ** Erro ! Informe apenas valores numéricos **\n");
				} catch (ValorInvalidoExeception e) {
					System.out.println(e.getMessage());
				}
			} while (true);

			/* Cadastrando suite */
			suite = new Suite(numero, tipo, capacidade, valor);

			if (reservas.validarReserva(suite, lista)) {
				reservas.setQtdPessoas(numeroDeHospedes);
				reservas.setSuite(suite);
				/* Verificar número de diárias */
				do {
					try {
						reservas.setQtdDias(Mensagem.quantidadeDeDiarias());
						break;
					} catch (InputMismatchException e) {
						System.out.println("\n   ** Erro ! Informe apenas valores numéricos **\n");
					} catch (ValorInvalidoExeception e) {
						System.out.println(e.getMessage());
					}
				} while (true);
				
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

		} while (Mensagem.novaReserva().toUpperCase().equals("S"));
		reservas.imprimirReservas();
		sc.close();
	}
}
