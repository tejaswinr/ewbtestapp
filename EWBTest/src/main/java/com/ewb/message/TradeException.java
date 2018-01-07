package com.ewb.message;

import java.io.Serializable;
import java.util.Date;

public class TradeException implements Serializable {

	private static final long serialVersionUID = -1L;
	private String id;
	private String stage;
	private String source;
	private String description;
	private String category;
	private Date aging;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getAging() {
		return aging;
	}

	public void setAging(Date aging) {
		this.aging = aging;
	}

}
