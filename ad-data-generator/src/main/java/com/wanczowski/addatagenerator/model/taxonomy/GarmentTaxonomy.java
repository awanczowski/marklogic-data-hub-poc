package com.wanczowski.addatagenerator.model.taxonomy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GarmentTaxonomy {
  SHIRT("Shirt"),
  SWEATER("Sweater"),
  PANTS("Pants"),
  JEANS("Jeans"),
  JACKET("Jacket");

  private String label;
}
