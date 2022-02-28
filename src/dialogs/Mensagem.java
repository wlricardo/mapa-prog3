package dialogs;

import java.util.Scanner;

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

	public static void reservaSuite() {
		System.out.println("-------------------------");
		System.out.println("     Reserva de suíte    ");
		System.out.println("-------------------------");
	}

	@SuppressWarnings("resource")
	public static int qtdHospedes() {
		System.out.print(" Informe o número de hóspedes: ");
		return new Scanner(System.in).nextInt();
	}

	@SuppressWarnings("resource")
	public static int hospedeID() {
		System.out.print(" -Código do hóspede: ");
		return new Scanner(System.in).nextInt();
	}

	@SuppressWarnings("resource")
	public static String nomeCompleto() {
		System.out.print(" -Nome completo: ");
		return new Scanner(System.in).nextLine();
	}

	@SuppressWarnings("resource")
	public static String endereco() {
		System.out.print(" -Endereço: ");
		return new Scanner(System.in).nextLine();
	}

	@SuppressWarnings("resource")
	public static int idade() {
		System.out.print(" -Idade: ");
		return new Scanner(System.in).nextInt();
	}

	@SuppressWarnings("resource")
	public static int numeroSuite() {
		System.out.print(" -Número da suite: ");
		return new Scanner(System.in).nextInt();
	}

	@SuppressWarnings("resource")
	public static String tipoDeSuite() {
		System.out.print(" -Tipo de suite: ");
		return new Scanner(System.in).nextLine();
	}

	@SuppressWarnings("resource")
	public static int capacidadeDaSuite() {
		System.out.print(" -Capacidade da suíte: ");
		return new Scanner(System.in).nextInt();
	}

	@SuppressWarnings("resource")
	public static double valorDaDiaria() {
		System.out.print(" -Valor da diária: R$ ");
		return new Scanner(System.in).nextDouble();
	}

	@SuppressWarnings("resource")
	public static int quantidadeDeDiarias() {
		System.out.print(" -Quantidade de diária: ");
		return new Scanner(System.in).nextInt();
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
