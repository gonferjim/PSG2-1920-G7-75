
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "donations")
public class Donation extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "cause_id")
	private Cause		cause;

//	@Column(name = "donor_name")
//	@NotBlank
//	private String		donorName;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "donation_owners", joinColumns = @JoinColumn(name = "donation_id"),
			inverseJoinColumns = @JoinColumn(name = "owner_id"))
	private Set<Owner> owners;

	@Column(name = "donation_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate	date;

	@Column(name = "quantity")
	@NotNull
	@Positive
	private Integer		quantity;


	public Cause getCause() {
		return this.cause;
	}
	public void setCause(final Cause cause) {
		this.cause = cause;
	}
	public Set<String> getDonorNames(final Set<Owner> owners) {
		Set<String> nombres = new HashSet<String>();
		for(Owner o:this.owners) {
			nombres.add(o.getFirstName());
		}
		return nombres;
	}
	
	public void setDonorName(final String name, Integer ownerId) {
		for(Owner o:this.owners) {
			if(o.getId().equals(ownerId)) {
				o.setFirstName(name);
			}
		}
	}

	public LocalDate getDate() {
		return this.date;
	}
	public void setDate(final LocalDate date) {
		this.date = date;
	}
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
	}

}
