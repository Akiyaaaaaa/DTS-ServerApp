package id.co.metrodata.serverapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import id.co.metrodata.serverapp.models.country;
import id.co.metrodata.serverapp.models.Dto.request.countryReq;
import id.co.metrodata.serverapp.services.countryService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/country")
public class countryControl {
  private countryService countryService;

  @GetMapping
  public List<country> getAll() {
    return countryService.getAll();
  }

  @GetMapping("/{id}")
  public country getById(@PathVariable Integer id) {
    return countryService.getById(id);
  }

  @PostMapping
  public country insert(@RequestBody country country) {
    return countryService.insert(country);
  }

  // with DTO
  @PostMapping("/dto")
  public country insertWithDTO(@RequestBody countryReq countryrReq) {
    return countryService.insertWithDTO(countryrReq);
  }

  // DTO with Model Mapper
  @PostMapping("/dto-mm")
  public country insertWithDTOModelMapper(@RequestBody countryReq countryReq) {
    return countryService.insertWithDTOModelMapper(countryReq);
  }

  @PutMapping("/{id}")
  public country update(@PathVariable Integer id, @RequestBody country country) {
    return countryService.update(id, country);
  }

  @DeleteMapping("/{id}")
  public country delete(@PathVariable Integer id) {
    return countryService.delete(id);
  }
}
