package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import exceptions.ValorInvalidoExeception;

public class Reserva {

	private Suite suite;
	private Integer qtdPessoas;
	private Integer qtdDias;

	private List<Hospede> hospedesDaSuite = new ArrayList<>();
	private HashMap<Suite, List<Hospede>> listaDeHospedesPorSuite = new HashMap<>();

	public Reserva() {
	}

	public Reserva(Suite suite, Integer qtdPessoas, Integer qtdDias) {
		if (suite.getCapacidade() >= qtdPessoas) {
			this.suite = suite;
			this.qtdPessoas = qtdPessoas;
			this.qtdDias = qtdDias;
		}
	}

	public Suite getSuite() {
		return suite;
	}

	public void setSuite(Suite suite) {
		this.suite = suite;
	}

	public Integer getQtdPessoas() {
		return qtdPessoas;
	}

	public void setQtdPessoas(Integer qtdPessoas) throws ValorInvalidoExeception {
		/*
		 * if (qtdPessoas == 0) { throw new
		 * ValorInvalidoExeception("\n ** Erro! Quantidade de hóspedes não pode ser nula **\n"
		 * ); }
		 */
		this.qtdPessoas = qtdPessoas;
	}

	public Integer getQtdDias() {
		return qtdDias;
	}

	public void setQtdDias(Integer qtdDias) {
		this.qtdDias = qtdDias;
	}

	public List<Hospede> getHospedes() {
		return hospedesDaSuite;
	}

	public HashMap<Suite, List<Hospede>> getListaDeHospedesPorSuite() {
		return listaDeHospedesPorSuite;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hospedesDaSuite, suite);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(hospedesDaSuite, other.hospedesDaSuite) && Objects.equals(suite, other.suite);
	}

	public int qtdDeHospedesMaioresDoisAnos(List<Hospede> hospedes) {
		int quantidade = 0;
		for (Hospede h : hospedes) {
			if (h.getIdade() > 2) {
				quantidade++;
			}
		}
		return quantidade;
	}

	public boolean verificarCapacidade(Suite suite, List<Hospede> hospedes) {
		if (suite.getCapacidade() >= qtdDeHospedesMaioresDoisAnos(hospedes)) {
			return true;
		}
		return false;
	}

	public boolean validarReserva(Suite suite, List<Hospede> hospedes) throws ValorInvalidoExeception {
		if (!verificarCapacidade(suite, hospedes)) {
			throw new ValorInvalidoExeception("Quantidade de hóspedes acima da capacidade permitida");
		} else {
			listaDeHospedesPorSuite.put(suite, hospedes);
		}
		return true;
	}

	public void imprimirReservas() {
		System.out.println("\nLISTA DE RESERVAS:\n");
		for (Suite s : listaDeHospedesPorSuite.keySet()) {
			System.out.println("-------------------------");
			System.out.println("  Hóspedes da suite #" + s.getNumero() + "\n");
			System.out.println(s);
			for (Hospede hospede : listaDeHospedesPorSuite.get(s)) {
				System.out.println(hospede);
			}
		}
	}

	public double calcularDiaria(Suite suite) {
		if (this.qtdDias > 7) {
			return 0.8 * (suite.getValorDiaria() * this.qtdDias);
		}
		return suite.getValorDiaria() * this.qtdDias;
	}
}