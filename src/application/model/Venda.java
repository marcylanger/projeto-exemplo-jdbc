package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {

	private Integer codigo;
	
	private LocalDate dataVenda;
	
	private Float valorTotal;
	
	private Cliente cliente;
	
	private List<ItemVenda> itens = new ArrayList<ItemVenda>();

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((dataVenda == null) ? 0 : dataVenda.hashCode());
		result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataVenda == null) {
			if (other.dataVenda != null)
				return false;
		} else if (!dataVenda.equals(other.dataVenda))
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		return true;
	}

	

	@Override
	public String toString() {
		return "Venda [codigo=" + codigo + ", dataVenda=" + dataVenda + ", valorTotal=" + valorTotal + ", cliente="
				+ cliente + ", itens=" + itens + "]";
	}

	public Venda(Integer codigo, LocalDate dataVenda, Float valorTotal, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.dataVenda = dataVenda;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
	}
	
	public Venda() {
		
	}
	
	public void calcularValorTotal() {
		this.valorTotal = 0.0F;
		for (ItemVenda itemVenda : itens) {
			this.valorTotal = this.valorTotal + (itemVenda.getValorItem() + itemVenda.getQuantidade());
		}
	}
	
}
