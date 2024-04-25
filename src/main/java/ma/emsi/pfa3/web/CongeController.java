package ma.emsi.pfa3.web;

import lombok.RequiredArgsConstructor;
import ma.emsi.pfa3.entities.Conge;
import ma.emsi.pfa3.services.ICongeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("conges")
@RequiredArgsConstructor
public class CongeController {
    private final ICongeService iCongeService;

@GetMapping
    public ResponseEntity<List<Conge>> getConges(){
        return new ResponseEntity<>(iCongeService.getConges(), HttpStatus.FOUND);
    }
    @GetMapping("/conges")
    public List<Conge> ListConges(){
           return iCongeService.getConges();}

    @PostMapping("/{idEmploye}")
    public ResponseEntity<Conge> addConge(@PathVariable int idEmploye, @RequestBody Conge conge) {
        Conge newConge = iCongeService.addConge(idEmploye, conge);
        return new ResponseEntity<>(newConge, HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public Conge updateConge(@RequestBody Conge conge,@PathVariable int id){
        return iCongeService.updateConge(conge,id);
    }
    @DeleteMapping("delete/{id}")
    public void deleteConge(@PathVariable int id){
        iCongeService.deleteConge(id);
    }


    @GetMapping("/conge/{id}")
    public Conge getCongeById(@PathVariable int id){
        return iCongeService.getCongeById(id);
    }


}
