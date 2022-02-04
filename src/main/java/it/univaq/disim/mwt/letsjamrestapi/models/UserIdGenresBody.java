/*
 * LetsJamApi
 * RESTful Api for LetsJam Website. MWT 2020/2021 - Jacopo Cicoria, Antonio Angelini, Mattia Lenza
 *
 * OpenAPI spec version: 1.0
 * Contact: mattia.lenza@student.univaq.it
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package it.univaq.disim.mwt.letsjamrestapi.models;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * UserIdGenresBody
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-02-04T17:34:00.278Z[GMT]")
public class UserIdGenresBody {
  @JsonProperty("genreId")
  private BigDecimal genreId = null;

  public UserIdGenresBody genreId(BigDecimal genreId) {
    this.genreId = genreId;
    return this;
  }

  /**
   * Get genreId
   * minimum: 1
   * 
   * @return genreId
   **/
  @JsonProperty("genreId")
  @Schema(description = "")
  @Valid
  @DecimalMin("1")
  public BigDecimal getGenreId() {
    return genreId;
  }

  public void setGenreId(BigDecimal genreId) {
    this.genreId = genreId;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserIdGenresBody userIdGenresBody = (UserIdGenresBody) o;
    return Objects.equals(this.genreId, userIdGenresBody.genreId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(genreId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserIdGenresBody {\n");

    sb.append("    genreId: ").append(toIndentedString(genreId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
