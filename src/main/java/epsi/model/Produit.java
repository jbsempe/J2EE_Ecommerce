package epsi.model;

// Generated 6 mai 2015 11:18:39 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Produit generated by hbm2java
 */
@Entity
@Table(name = "produit", catalog = "bdd_lpdc")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Produit implements java.io.Serializable {

	private long idProduit;
	private String designationProduit;
	private float prix;
	private Set<TypeContenant> typeContenants = new HashSet<TypeContenant>(0);
	private Set<Produit> produitsForIdProduitPlat = new HashSet<Produit>(0);
	private Set<PanierContient> panierContients = new HashSet<PanierContient>(0);
	private Set<Traiteur> traiteurs = new HashSet<Traiteur>(0);
	private Plat plat;
	private Set<Produit> produitsForIdProduitMenu = new HashSet<Produit>(0);
	private Menu menu;
	private Set<CommandeContient> commandeContients = new HashSet<CommandeContient>(
			0);

	public Produit() {
	}

	public Produit(String designationProduit, float prix) {
		this.designationProduit = designationProduit;
		this.prix = prix;
	}

	public Produit(String designationProduit, float prix,
			Set<TypeContenant> typeContenants,
			Set<Produit> produitsForIdProduitPlat,
			Set<PanierContient> panierContients, Set<Traiteur> traiteurs,
			Plat plat, Set<Produit> produitsForIdProduitMenu, Menu menu,
			Set<CommandeContient> commandeContients) {
		this.designationProduit = designationProduit;
		this.prix = prix;
		this.typeContenants = typeContenants;
		this.produitsForIdProduitPlat = produitsForIdProduitPlat;
		this.panierContients = panierContients;
		this.traiteurs = traiteurs;
		this.plat = plat;
		this.produitsForIdProduitMenu = produitsForIdProduitMenu;
		this.menu = menu;
		this.commandeContients = commandeContients;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_Produit", unique = true, nullable = false)
	public long getIdProduit() {
		return this.idProduit;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	@Column(name = "designation_Produit", nullable = false, length = 50)
	public String getDesignationProduit() {
		return this.designationProduit;
	}

	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}

	@Column(name = "prix", nullable = false, precision = 12, scale = 0)
	public float getPrix() {
		return this.prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "_possede", catalog = "bdd_lpdc", joinColumns = { @JoinColumn(name = "id_Produit", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_Type_Contenant", nullable = false, updatable = false) })
	public Set<TypeContenant> getTypeContenants() {
		return this.typeContenants;
	}

	public void setTypeContenants(Set<TypeContenant> typeContenants) {
		this.typeContenants = typeContenants;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "menu_contient", catalog = "bdd_lpdc", joinColumns = { @JoinColumn(name = "id_Produit_Menu", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_Produit_Plat", nullable = false, updatable = false) })
	public Set<Produit> getProduitsForIdProduitPlat() {
		return this.produitsForIdProduitPlat;
	}

	public void setProduitsForIdProduitPlat(
			Set<Produit> produitsForIdProduitPlat) {
		this.produitsForIdProduitPlat = produitsForIdProduitPlat;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produit")
	public Set<PanierContient> getPanierContients() {
		return this.panierContients;
	}

	public void setPanierContients(Set<PanierContient> panierContients) {
		this.panierContients = panierContients;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "produits")
	public Set<Traiteur> getTraiteurs() {
		return this.traiteurs;
	}

	public void setTraiteurs(Set<Traiteur> traiteurs) {
		this.traiteurs = traiteurs;
	}

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "produit")
	public Plat getPlat() {
		return this.plat;
	}

	public void setPlat(Plat plat) {
		this.plat = plat;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "menu_contient", catalog = "bdd_lpdc", joinColumns = { @JoinColumn(name = "id_Produit_Plat", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_Produit_Menu", nullable = false, updatable = false) })
	public Set<Produit> getProduitsForIdProduitMenu() {
		return this.produitsForIdProduitMenu;
	}

	public void setProduitsForIdProduitMenu(
			Set<Produit> produitsForIdProduitMenu) {
		this.produitsForIdProduitMenu = produitsForIdProduitMenu;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "produit")
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produit")
	public Set<CommandeContient> getCommandeContients() {
		return this.commandeContients;
	}

	public void setCommandeContients(Set<CommandeContient> commandeContients) {
		this.commandeContients = commandeContients;
	}

}