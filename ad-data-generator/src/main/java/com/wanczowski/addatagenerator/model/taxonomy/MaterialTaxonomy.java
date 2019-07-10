package com.wanczowski.addatagenerator.model.taxonomy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MaterialTaxonomy {
  COTTON("Cotton"),
  DENIM("Denim"),
  LACE("Lace"),
  LEATHER("Leather"),
  SILK("Silk"),
  TWEED("Tweed"),
  WOOL("Wool");

  private String label;
}
