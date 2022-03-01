package entities;

import java.util.Objects;

public class Suite {

	private Integer numero;
	private String tipo;
	private Integer capacidade;
	private Double valorDiaria;

	public Suite() {
	}

	public Suite(Integer numero, String tipo, Integer capacidade, Double valorDiaria) {
		this.numero = numero;
		this.tipo = tipo;
		this.capacidade = capacidade;
		this.valorDiaria = valorDiaria;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Suite other = (Suite) obj;
		return Objects.equals(numero, other.numero) && Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {
		return "   Suite #: " + numero 
				+ "\n   Tipo de suíte: " + tipo.toUpperCase() 
				+ "\n   Capacidade: " + capacidade + " hóspedes" 
				+ "\n   Valor da diária: R$ " + valorDiaria 
				+ "\n";
	}
}