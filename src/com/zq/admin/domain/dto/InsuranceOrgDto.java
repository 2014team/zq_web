package com.zq.admin.domain.dto;

import java.io.Serializable;

public class InsuranceOrgDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String insuranceOrgAlias;
	private String insuranceOrgCode;
	private String insuranceOrgName;
	private String insuranceType;
	private String onsetDate;

	public String getInsuranceOrgAlias() {
		return insuranceOrgAlias;
	}

	public void setInsuranceOrgAlias(String insuranceOrgAlias) {
		this.insuranceOrgAlias = insuranceOrgAlias;
	}

	public String getInsuranceOrgCode() {
		return insuranceOrgCode;
	}

	public void setInsuranceOrgCode(String insuranceOrgCode) {
		this.insuranceOrgCode = insuranceOrgCode;
	}

	public String getInsuranceOrgName() {
		return insuranceOrgName;
	}

	public void setInsuranceOrgName(String insuranceOrgName) {
		this.insuranceOrgName = insuranceOrgName;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getOnsetDate() {
		return onsetDate;
	}

	public void setOnsetDate(String onsetDate) {
		this.onsetDate = onsetDate;
	}

}
