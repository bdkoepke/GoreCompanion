package com.shanewmiller.gorecompanion.Models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ConditionSummaryResult {
	@SerializedName("Trails")
	public Integer trailCount;
	
	@SerializedName("Lifts")
	public Integer liftCount;
	
	@SerializedName("Base")
	public String base;
	
	@SerializedName("Primary")
	public String primarySurface;
	
	@SerializedName("Secondary")
	public String secondarySurface;
}
