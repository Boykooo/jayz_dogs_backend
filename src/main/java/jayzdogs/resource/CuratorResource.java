package jayzdogs.resource;

import jayzdogs.dto.CuratorDto;
import jayzdogs.dto.PageableResponse;
import jayzdogs.service.CuratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author andrey
 * @date 17.01.19
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/curator")
public class CuratorResource {

    @Autowired
    private CuratorService curatorService;

    @GetMapping
    public ResponseEntity getAll(@RequestParam("page") int page,
                                 @RequestParam("size") int size,
                                 @RequestParam(value = "sortField", required = false) String sortField,
                                 @RequestParam(value = "sortDirection", required = false) String sortDirection) {
        PageableResponse curators = curatorService.getAll(page, size, sortField, sortDirection);
        return ResponseEntity.ok(curators);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CuratorDto curatorDto) {
        CuratorDto curator = curatorService.createOrUpdate(curatorDto);
        return ResponseEntity.ok(curator);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody CuratorDto curatorDto) {
        curatorService.createOrUpdate(curatorDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        curatorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
