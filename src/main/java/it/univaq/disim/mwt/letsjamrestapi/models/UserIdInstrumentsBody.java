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
 * UserIdInstrumentsBody
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-02-04T17:34:00.278Z[GMT]")
public class UserIdInstrumentsBody {
  @JsonProperty("instrumentId")
  private BigDecimal instrumentId = null;

  public UserIdInstrumentsBody instrumentId(BigDecimal instrumentId) {
    this.instrumentId = instrumentId;
    return this;
  }

  /**
   * Get instrumentId
   * minimum: 1
   * 
   * @return instrumentId
   **/
  @JsonProperty("instrumentId")
  @Schema(description = "")
  @Valid
  @DecimalMin("1")
  public BigDecimal getInstrumentId() {
    return instrumentId;
  }

  public void setInstrumentId(BigDecimal instrumentId) {
    this.instrumentId = instrumentId;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserIdInstrumentsBody userIdInstrumentsBody = (UserIdInstrumentsBody) o;
    return Objects.equals(this.instrumentId, userIdInstrumentsBody.instrumentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(instrumentId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserIdInstrumentsBody {\n");

    sb.append("    instrumentId: ").append(toIndentedString(instrumentId)).append("\n");
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
