package com.shanewmiller.gorecompanion.Models;

import com.google.gson.annotations.SerializedName;

public class ConditionSummary {
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
