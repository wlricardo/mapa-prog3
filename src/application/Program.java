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
					/* Verifica��o do n�mero de h�spedes */
					numeroDeHospedes = Mensagem.qtdHospedes();
					// reservas.setQtdPessoas(numeroDeHospedes);
					Mensagem.cadastroDeHospedes();
					break;
				} catch (InputMismatchException e) {
					System.out.println("\n   ** Erro ! Informe apenas valores num�ricos **\n");
				} catch (ValorInvalidoExeception e) {
					System.out.println(e.getMessage());
				}
			} while (true);

			// INSERIR DADOS DOS H�SPEDES
			for (int i = 0; i < numeroDeHospedes; i++) {
				System.out.println(" Dados do h�spede " + (i + 1) + ":");

				/* Verifica��o da identifica��o do h�spede */
				int codigo;
				do {
					try {
						codigo = Mensagem.hospedeID();
						break;
					} catch (InputMismatchException e) {
						System.out.println("\n   ** Erro ! Informe apenas valores num�ricos **\n");
					} catch (ValorInvalidoExeception e) {
						System.out.println(e.getMessage());
						// System.out.println("\n ** Erro! C�digo do cliente n�o pode ser nulo **\n");
					}
				} while (true);

				/* Verifica��o do nome do h�spede */
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

				/* Verifica��o da idade do h�spede */
				int idade;
				do {
					try {
						idade = Mensagem.idade();
						break;
					} catch (InputMismatchException e) {
						System.out.println("\n   ** Erro ! Informe apenas valores num�ricos **\n");
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

			/* Verificando n�mero da suite */
			int numero;
			do {
				try {
					numero = Mensagem.numeroSuite();
					break;
				} catch (InputMismatchException e) {
					System.out.println("\n   ** Erro ! Informe apenas valores num�ricos **\n");
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
					System.out.println("\n   ** Erro ! Informe apenas valores num�ricos **\n");
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
					System.out.println("\n   ** Erro ! Informe apenas valores num�ricos **\n");
				} catch (ValorInvalidoExeception e) {
					System.out.println(e.getMessage());
				}
			} while (true);

			/* Cadastrando suite */
			suite = new Suite(numero, tipo, capacidade, valor);

			if (reservas.validarReserva(suite, lista)) {
				reservas.setQtdPessoas(numeroDeHospedes);
				reservas.setSuite(suite);
				/* Verificar n�mero de di�rias */
				do {
					try {
						reservas.setQtdDias(Mensagem.quantidadeDeDiarias());
						break;
					} catch (InputMismatchException e) {
						System.out.println("\n   ** Erro ! Informe apenas valores num�ricos **\n");
					} catch (ValorInvalidoExeception e) {
						System.out.println(e.getMessage());
					}
				} while (true);
				
				Mensagem.sucessoReserva();
				
				System.out.println("   H�SPEDES");
				System.out.println("   --------");
				for (Hospede h : lista) {
					System.out.println(h);
				}
				System.out.println("   SU�TE");
				System.out.println("   -----");
				System.out.println(suite);
			}

		} while (Mensagem.novaReserva().toUpperCase().equals("S"));
		reservas.imprimirReservas();
		sc.close();
	}
}
