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
import exceptions.ReservasExceptions;
import exceptions.ValorInvalidoExeception;

public class Program {

	public static void main(String[] args) throws ValorInvalidoExeception {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Reserva reserva = new Reserva();
		double valorTotalDasHospedagens = 0;

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

			int numero;
			String tipo;
			int capacidade;
			double valor;

			do {
				try {
					/* Verificando n�mero da suite */
					do {
						try {
							numero = Mensagem.numeroSuite(reserva.getListaDeHospedesPorSuite());
							break;
						} catch (InputMismatchException e) {
							System.out.println("\n   ** Erro ! Informe apenas valores num�ricos **\n");
						} catch (ValorInvalidoExeception e) {
							System.out.println(e.getMessage());
						}
					} while (true);

					tipo = Mensagem.tipoDeSuite();

					/* Verificar capacidade da suite */
					do {
						try {
							capacidade = Mensagem.capacidadeDaSuite(lista);
							break;
						} catch (InputMismatchException e) {
							System.out.println("\n   ** Erro ! Informe apenas valores num�ricos **\n");
						} catch (ValorInvalidoExeception e) {
							System.out.println(e.getMessage());
						}
					} while (true);

					/* Verificar valor da suite */
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
					break;
				} catch (ReservasExceptions e) {
					System.out.println(e.getMessage());
				}
			} while (true);

			/* Cadastrando suite */
			suite = new Suite(numero, tipo, capacidade, valor);

			/* Adicionando reserva efetuada � lista de h�spedes por suite */
			reserva.validarReserva(suite, lista);
			reserva.setQtdPessoas(numeroDeHospedes);
			reserva.setSuite(suite);

			/* Verificar n�mero de di�rias */
			do {
				try {
					reserva.setQtdDias(Mensagem.quantidadeDeDiarias());
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

			System.out.println("   VALOR DA ESTADIA");
			System.out.println("   ----------------");
			System.out.println("   Total de di�rias: " + reserva.getQtdDias());
			System.out.println("   R$ " + reserva.calcularDiaria(suite));
			valorTotalDasHospedagens += reserva.calcularDiaria(suite);

		} while (Mensagem.novaReserva().toUpperCase().equals("S"));
		reserva.imprimirReservas();
		System.out.println("---------------------------------");
		System.out.println("VALOR TOTAL DAS ESTADIAS: R$ " + valorTotalDasHospedagens);
		sc.close();
	}
}