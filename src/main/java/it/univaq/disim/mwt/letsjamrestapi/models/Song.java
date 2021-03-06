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

import javax.annotation.Generated;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * Song
 */
@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class Song   {
  @JsonProperty("id")
  private BigDecimal id = null;

  @JsonProperty("spotifyId")
  private String spotifyId = null;

  @JsonProperty("author")
  private String author = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("albumName")
  private String albumName = null;

  @JsonProperty("albumType")
  private String albumType = null;

  @JsonProperty("image")
  private String image = null;

  @JsonProperty("isExplicit")
  private Boolean isExplicit = null;

  @JsonProperty("duration")
  private BigDecimal duration = null;

  @JsonProperty("lyrics")
  private String lyrics = null;

  @JsonProperty("genre")
  private Genre genre = null;

  public Song id(BigDecimal id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * minimum: 1
   * @return id
   **/
  @JsonProperty("id")
  @Schema(description = "")
  @Valid
 @DecimalMin("1")  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public Song spotifyId(String spotifyId) {
    this.spotifyId = spotifyId;
    return this;
  }

  /**
   * Get spotifyId
   * @return spotifyId
   **/
  @JsonProperty("spotifyId")
  @Schema(description = "")
  public String getSpotifyId() {
    return spotifyId;
  }

  public void setSpotifyId(String spotifyId) {
    this.spotifyId = spotifyId;
  }

  public Song author(String author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
   **/
  @JsonProperty("author")
  @Schema(required = true, description = "")
  @NotNull
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Song title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   **/
  @JsonProperty("title")
  @Schema(required = true, description = "")
  @NotNull
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Song albumName(String albumName) {
    this.albumName = albumName;
    return this;
  }

  /**
   * Get albumName
   * @return albumName
   **/
  @JsonProperty("albumName")
  @Schema(description = "")
  public String getAlbumName() {
    return albumName;
  }

  public void setAlbumName(String albumName) {
    this.albumName = albumName;
  }

  public Song albumType(String albumType) {
    this.albumType = albumType;
    return this;
  }

  /**
   * Get albumType
   * @return albumType
   **/
  @JsonProperty("albumType")
  @Schema(description = "")
  public String getAlbumType() {
    return albumType;
  }

  public void setAlbumType(String albumType) {
    this.albumType = albumType;
  }

  public Song image(String image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
   **/
  @JsonProperty("image")
  @Schema(description = "")
  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Song isExplicit(Boolean isExplicit) {
    this.isExplicit = isExplicit;
    return this;
  }

  /**
   * Get isExplicit
   * @return isExplicit
   **/
  @JsonProperty("isExplicit")
  @Schema(required = true, description = "")
  @NotNull
  public Boolean isIsExplicit() {
    return isExplicit;
  }

  public void setIsExplicit(Boolean isExplicit) {
    this.isExplicit = isExplicit;
  }

  public Song duration(BigDecimal duration) {
    this.duration = duration;
    return this;
  }

  /**
   * Get duration
   * @return duration
   **/
  @JsonProperty("duration")
  @Schema(required = true, description = "")
  @NotNull
  @Valid
  public BigDecimal getDuration() {
    return duration;
  }

  public void setDuration(BigDecimal duration) {
    this.duration = duration;
  }

  public Song lyrics(String lyrics) {
    this.lyrics = lyrics;
    return this;
  }

  /**
   * Get lyrics
   * @return lyrics
   **/
  @JsonProperty("lyrics")
  @Schema(description = "")
  public String getLyrics() {
    return lyrics;
  }

  public void setLyrics(String lyrics) {
    this.lyrics = lyrics;
  }

  public Song genre(Genre genre) {
    this.genre = genre;
    return this;
  }

  /**
   * Get genre
   * @return genre
   **/
  @JsonProperty("genre")
  @Schema(required = true, description = "")
  @NotNull
  @Valid
  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Song song = (Song) o;
    return Objects.equals(this.id, song.id) &&
        Objects.equals(this.spotifyId, song.spotifyId) &&
        Objects.equals(this.author, song.author) &&
        Objects.equals(this.title, song.title) &&
        Objects.equals(this.albumName, song.albumName) &&
        Objects.equals(this.albumType, song.albumType) &&
        Objects.equals(this.image, song.image) &&
        Objects.equals(this.isExplicit, song.isExplicit) &&
        Objects.equals(this.duration, song.duration) &&
        Objects.equals(this.lyrics, song.lyrics) &&
        Objects.equals(this.genre, song.genre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, spotifyId, author, title, albumName, albumType, image, isExplicit, duration, lyrics, genre);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Song {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    spotifyId: ").append(toIndentedString(spotifyId)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    albumName: ").append(toIndentedString(albumName)).append("\n");
    sb.append("    albumType: ").append(toIndentedString(albumType)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    isExplicit: ").append(toIndentedString(isExplicit)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    lyrics: ").append(toIndentedString(lyrics)).append("\n");
    sb.append("    genre: ").append(toIndentedString(genre)).append("\n");
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

  public enum SongSortEnum {
    TITLE("brani.title"),
    ALBUMNAME("brani.album_name"),
    CREATEDATETIME("brani.create_date_time"),
    DURATION("brani.duration");

    private String value;

    SongSortEnum(String value) {
      this.value = value;
    }

    public String toString() {
      return String.valueOf(value);
    }

    public static SongSortEnum fromValue(String text) {
      for (SongSortEnum b : SongSortEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  public enum SongAlbumType{
    ALBUM("ALBUM"),
    SINGLE("SINGLE"),
    COMPILATION("COMPILATION");

    private String value;

    SongAlbumType(String value) {
      this.value = value;
    }

    public String toString() {
      return String.valueOf(value);
    }

    public static SongSortEnum fromValue(String text) {
      for (SongSortEnum b : SongSortEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
}
