package jayzdogs.resource;

import jayzdogs.dto.DogDto;
import jayzdogs.dto.NewDogDto;
import jayzdogs.dto.PageableResponse;
import jayzdogs.exception.DogLimitException;
import jayzdogs.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author andrey
 * @date 17.01.19
 */

@CrossOrigin
@RestController
public class DogResource {

    @Autowired
    private DogService dogService;

    @GetMapping("/{id}/dog")
    public ResponseEntity get(@PathVariable("id") Long curatorId,
                              @RequestParam("page") int page,
                              @RequestParam("size") int size,
                              @RequestParam(value = "sortField", required = false) String sortField,
                              @RequestParam(value = "sortDirection", required = false) String sortDirection) {
        PageableResponse all = dogService.getAll(curatorId, page, size, sortField, sortDirection);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/dog/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        DogDto dog = dogService.getById(id);
        return ResponseEntity.ok(dog);
    }

    @PostMapping("/dog")
    public ResponseEntity create(@RequestBody @Valid NewDogDto newDogDto) throws DogLimitException {
        DogDto dog = dogService.create(newDogDto);
        return ResponseEntity.ok(dog);
    }

    @PutMapping("/dog")
    public ResponseEntity update(@RequestBody @Valid NewDogDto dogDto) {
        DogDto update = dogService.update(dogDto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/dog/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        dogService.delete(id);
        return ResponseEntity.ok().build();
    }

}
