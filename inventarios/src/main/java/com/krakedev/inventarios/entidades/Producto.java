package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class Producto {
	private int proId;
	private String prodNombre;
	private UnidadesMedida unidadesMedida;
	private BigDecimal prodPrecioVenta;
	private boolean prodTieneIva;
	private BigDecimal prodCoste;
	private Catalogo catIdTipoProducto;
	private int prodStock;

	public Producto() {
		super();
	}

	public Producto(int proId, String prodNombre, UnidadesMedida unidadesMedida, BigDecimal prodPrecioVenta,
			boolean prodTieneIva, BigDecimal prodCoste, Catalogo catIdTipoProducto, int prodStock) {
		super();
		this.proId = proId;
		this.prodNombre = prodNombre;
		this.unidadesMedida = unidadesMedida;
		this.prodPrecioVenta = prodPrecioVenta;
		this.prodTieneIva = prodTieneIva;
		this.prodCoste = prodCoste;
		this.catIdTipoProducto = catIdTipoProducto;
		this.prodStock = prodStock;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getProdNombre() {
		return prodNombre;
	}

	public void setProdNombre(String prodNombre) {
		this.prodNombre = prodNombre;
	}

	public UnidadesMedida getUnidadesMedida() {
		return unidadesMedida;
	}

	public void setUnidadesMedida(UnidadesMedida unidadesMedida) {
		this.unidadesMedida = unidadesMedida;
	}

	public BigDecimal getProdPrecioVenta() {
		return prodPrecioVenta;
	}

	public void setProdPrecioVenta(BigDecimal prodPrecioVenta) {
		this.prodPrecioVenta = prodPrecioVenta;
	}

	public boolean isProdTieneIva() {
		return prodTieneIva;
	}

	public void setProdTieneIva(boolean prodTieneIva) {
		this.prodTieneIva = prodTieneIva;
	}

	public BigDecimal getProdCoste() {
		return prodCoste;
	}

	public void setProdCoste(BigDecimal prodCoste) {
		this.prodCoste = prodCoste;
	}

	public Catalogo getCatIdTipoProducto() {
		return catIdTipoProducto;
	}

	public void setCatIdTipoProducto(Catalogo catIdTipoProducto) {
		this.catIdTipoProducto = catIdTipoProducto;
	}

	public int getProdStock() {
		return prodStock;
	}

	public void setProdStock(int prodStock) {
		this.prodStock = prodStock;
	}

	@Override
	public String toString() {
		return "Producto [proId=" + proId + ", prodNombre=" + prodNombre + ", unidadesMedida=" + unidadesMedida
				+ ", prodPrecioVenta=" + prodPrecioVenta + ", prodTieneIva=" + prodTieneIva + ", prodCoste=" + prodCoste
				+ ", catIdTipoProducto=" + catIdTipoProducto + ", prodStock=" + prodStock + "]";
	}

}
