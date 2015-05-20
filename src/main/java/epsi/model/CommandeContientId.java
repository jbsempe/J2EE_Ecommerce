package epsi.model;

// Generated 6 mai 2015 11:18:39 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CommandeContientId generated by hbm2java
 */
@Embeddable
public class CommandeContientId implements java.io.Serializable {

	private int idCommande;
	private int idProduit;

	public CommandeContientId() {
	}

	public CommandeContientId(int idCommande, int idProduit) {
		this.idCommande = idCommande;
		this.idProduit = idProduit;
	}

	@Column(name = "id_Commande", nullable = false)
	public int getIdCommande() {
		return this.idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	@Column(name = "id_Produit", nullable = false)
	public int getIdProduit() {
		return this.idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CommandeContientId))
			return false;
		CommandeContientId castOther = (CommandeContientId) other;

		return (this.getIdCommande() == castOther.getIdCommande())
				&& (this.getIdProduit() == castOther.getIdProduit());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdCommande();
		result = 37 * result + this.getIdProduit();
		return result;
	}

}