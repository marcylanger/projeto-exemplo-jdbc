package application.model;

public class ItemVenda {

	private Integer codigo;
	
	private Venda venda;

	private Produto produto;
	
	private Float valorItem;
	
	private Integer quantidade;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Float getValorItem() {
		return valorItem;
	}

	public void setValorItem(Float valorItem) {
		this.valorItem = valorItem;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((valorItem == null) ? 0 : valorItem.hashCode());
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
		ItemVenda other = (ItemVenda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (valorItem == null) {
			if (other.valorItem != null)
				return false;
		} else if (!valorItem.equals(other.valorItem))
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemVenda [codigo=" + codigo + ", produto=" + produto + ", valorItem=" + valorItem
				+ ", quantidade=" + quantidade + "]";
	}

	public ItemVenda(Integer codigo, Venda venda, Produto produto, Float valorItem, Integer quantidade) {
		super();
		this.codigo = codigo;
		this.venda = venda;
		this.produto = produto;
		this.valorItem = valorItem;
		this.quantidade = quantidade;
	}

	public ItemVenda() {
		
	}
	
}

