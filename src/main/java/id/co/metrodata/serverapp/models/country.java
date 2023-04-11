package id.co.metrodata.serverapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class country {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "country_id")
  private Integer id;

  @Column(name = "country_code", nullable = false, length = 2, unique = true)
  private String code;

  @Column(name = "country_name", nullable = false, length = 40)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "region_id", nullable = false)
  private region region;

  // public country(Integer id, String name,
  // id.co.metrodata.serverapp.models.region region) {
  // this.id = id;
  // this.name = name;
  // this.region = region;
  // }

  // public country() {

  // }

  // public Integer getid() {
  // return id;
  // }

  // public void setid(Integer id) {
  // this.id = id;
  // }

  // public String getname() {
  // return name;
  // }

  // public void setname(String name) {
  // this.name = name;
  // }

  // public region getRegion() {
  // return region;
  // }

  // public void setRegion(region region) {
  // this.region = region;
  // }

}
