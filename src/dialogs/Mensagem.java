package dialogs;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import entities.Hospede;
import entities.Suite;
import exceptions.ReservasExceptions;
import exceptions.ValorInvalidoExeception;

public abstract class Mensagem {

	public static void telaInicial() {
		System.out.println("\n--------------------------------------");
		System.out.println("           HOTEL 123 LÉGUAS			  ");
		System.out.println(" Sistema de Gerenciamento de Reservas ");
		System.out.println("--------------------------------------\n");
	}

	public static void cadastroDeHospedes() {
		System.out.println("\n-------------------------");
		System.out.println(" Cadastrado dos hóspedes ");
		System.out.println("-------------------------");
	}

	public static void reservarSuite() {
		System.out.println("-------------------------");
		System.out.println("     Reserva de suíte    ");
		System.out.println("-------------------------");
	}

	@SuppressWarnings("resource")
	public static int qtdHospedes() throws ValorInvalidoExeception {
		System.out.print(" Informe o número de hóspedes: ");
		int qtd = new Scanner(System.in).nextInt();
		if (qtd == 0) {
			throw new ValorInvalidoExeception(
					"\n   ** Erro! Quantidade de hóspedes não pode ser nula. Tente novamente **\n");
		}
		return qtd;
	}

	@SuppressWarnings("resource")
	public static int hospedeID() throws ValorInvalidoExeception {
		System.out.print(" -Código do hóspede: ");
		int cod = new Scanner(System.in).nextInt();
		if (cod == 0) {
			throw new ValorInvalidoExeception(
					"\n   ** Erro! O código do hóspede não pode ser nulo. Tente novamente **\n");
		}
		return cod;
	}

	@SuppressWarnings("resource")
	public static String nomeCompleto() throws ValorInvalidoExeception {
		System.out.print(" -Nome completo: ");
		String nome = new Scanner(System.in).nextLine();
		if (nome.isEmpty()) {
			throw new ValorInvalidoExeception(
					"\n   ** Erro! O nome do hóspede não pode estar vazio. Tente novamente. ** \n");
		}
		return nome;
	}

	@SuppressWarnings("resource")
	public static String endereco() {
		System.out.print(" -Endereço: ");
		return new Scanner(System.in).nextLine();
	}

	@SuppressWarnings("resource")
	public static int idade() throws ValorInvalidoExeception {
		System.out.print(" -Idade: ");
		int idade = new Scanner(System.in).nextInt();
		if (idade == 0) {
			throw new ValorInvalidoExeception("\n   ** A idade do hóspede não pode ser nula. Tente novamente **\n");
		}
		return idade;
	}

	@SuppressWarnings("resource")
	public static int numeroSuite(HashMap<Suite, List<Hospede>> lista)
			throws ValorInvalidoExeception, ReservasExceptions {
		System.out.print(" -Número da suite: ");
		int numero = new Scanner(System.in).nextInt();
		if (numero == 0) {
			throw new ValorInvalidoExeception("\n   ** O número da suite não pode ser nulo. Tente novamente **\n");
		}
		for (Suite s : lista.keySet()) {
			if (s.getNumero().equals(numero)) {
				throw new ReservasExceptions("\n   ** Erro! Esta suite já foi reservada. Tente novamente **\n");			}
		}
		return numero;
	}

	@SuppressWarnings("resource")
	public static String tipoDeSuite() {
		System.out.print(" -Tipo de suite: ");
		return new Scanner(System.in).nextLine();
	}

	@SuppressWarnings("resource")
	public static int capacidadeDaSuite(List<Hospede> lista) throws ValorInvalidoExeception, ReservasExceptions {
		int total = 0;
		for (Hospede h : lista) {
			if (h.getIdade() > 2) {
				total++;
			}
		}
		System.out.print(" -Capacidade da suíte: ");
		int capacidade = new Scanner(System.in).nextInt();
		if (capacidade == 0) {
			throw new ValorInvalidoExeception("\n   ** A capacidade da suite não pode ser nula. Tente novamente **\n");
		}
		if (capacidade < total) {
			throw new ReservasExceptions("\n   ** A capacidade da suite não suporta o número de hóspedes **\n");
		}
		return capacidade;
	}

	@SuppressWarnings("resource")
	public static double valorDaDiaria() throws ValorInvalidoExeception {
		System.out.print(" -Valor da diária: R$ ");
		double diaria = new Scanner(System.in).nextDouble();
		if (diaria == 0.0) {
			throw new ValorInvalidoExeception("\n   ** O valor da diária não pode ser nulo. Tente novamente **\n");
		}
		return diaria;
	}

	@SuppressWarnings("resource")
	public static int quantidadeDeDiarias() throws ValorInvalidoExeception {
		System.out.print(" -Quantidade de diária: ");
		int diarias = new Scanner(System.in).nextInt();
		if (diarias == 0) {
			throw new ValorInvalidoExeception("\n   ** O númedo de diárias não pode ser nulo. Tente novamente **\n");
		}
		return diarias;
	}

	public static void sucessoReserva() {
		System.out.println("\n *** Reserva efetuada com sucesso ***\n");
	}

	@SuppressWarnings("resource")
	public static String novaReserva() {
		System.out.print("\nDeseja fazer nova reserva [S/N] ? : ");
		return new Scanner(System.in).nextLine();
	}
}