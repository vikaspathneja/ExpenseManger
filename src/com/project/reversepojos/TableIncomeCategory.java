package com.project.reversepojos;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="table_income_category")
@NamedQuery(name="TableIncomeCategory.findAll", query="SELECT t FROM TableIncomeCategory t")
public class TableIncomeCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="income_category_id")
	private int incomeCategoryId;

	@Column (name="user_id")
	private long userId;
	
	@Column(name="income_category_description")
	private String incomeCategoryDescription;

	@Column(name="income_type")
	private String incomeType;

	
	public TableIncomeCategory(long userId) {
		this.userId = userId;
	}

	//bi-directional many-to-one association to TableIncome
	/*@OneToMany(mappedBy="tableIncomeCategory")
	private List<TableIncome> tableIncomes;*/

	//bi-directional many-to-one association to TableIcon
	/*@ManyToOne
	@JoinColumn(name="icon_id")
	private TableIcon tableIcon;*/

	public TableIncomeCategory() {
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userid) {
		this.userId = userid;
	}




	public int getIncomeCategoryId() {
		return this.incomeCategoryId;
	}

	public void setIncomeCategoryId(int incomeCategoryId) {
		this.incomeCategoryId = incomeCategoryId;
	}

	public String getIncomeCategoryDescription() {
		return this.incomeCategoryDescription;
	}

	public void setIncomeCategoryDescription(String incomeCategoryDescription) {
		this.incomeCategoryDescription = incomeCategoryDescription;
	}

	public String getIncomeType() {
		return this.incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

	/*public List<TableIncome> getTableIncomes() {
		return this.tableIncomes;
	}

	public void setTableIncomes(List<TableIncome> tableIncomes) {
		this.tableIncomes = tableIncomes;
	}*/

	/*public TableIncome addTableIncome(TableIncome tableIncome) {
		getTableIncomes().add(tableIncome);
		tableIncome.setTableIncomeCategory(this);

		return tableIncome;
	}

	public TableIncome removeTableIncome(TableIncome tableIncome) {
		getTableIncomes().remove(tableIncome);
		tableIncome.setTableIncomeCategory(null);

		return tableIncome;
	}*/

	
	public TableIncomeCategory(int userId, String incomeCategoryDescription, String incomeType) {
		super();
		this.userId = userId;
		this.incomeCategoryDescription = incomeCategoryDescription;
		this.incomeType = incomeType;
		System.out.println(this.userId);
	}
	
	public TableIncomeCategory(String incomeType) {
		this.incomeType = incomeType;
	}
	

	@Override
	public String toString() {
		return "\nTableIncomeCategory [incomeCategoryId=" + incomeCategoryId + ", userId=" + userId
				+ ", incomeCategoryDescription=" + incomeCategoryDescription + ", incomeType=" + incomeType + "]";
	}



	/*public TableIcon getTableIcon() {
		return this.tableIcon;
	}*/

	/*public void setTableIcon(TableIcon tableIcon) {
		this.tableIcon = tableIcon;
	}*/

}