package ma.emsi.pfa3.web;

import lombok.RequiredArgsConstructor;
import ma.emsi.pfa3.entities.Absence;
import ma.emsi.pfa3.entities.Conge;
import ma.emsi.pfa3.services.IAbsenceService;
import ma.emsi.pfa3.services.ICongeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("absences")
@RequiredArgsConstructor
public class AbsenceController {
    private final IAbsenceService iAbsenceService;
    public ResponseEntity<List<Absence>> getAbsences(){
        return new ResponseEntity<>(iAbsenceService.getAbsences(), HttpStatus.FOUND);
    }
    @GetMapping("/absences")
    public List<Absence> ListAbsence(){
        return iAbsenceService.getAbsences();}

    @PostMapping("/{idEmploye}")
    public ResponseEntity<Absence> addAbsence(@PathVariable int idEmploye, @RequestBody Absence absence) {
        Absence newAbsence= iAbsenceService.addAbsence(idEmploye, absence);
        return new ResponseEntity<>(newAbsence, HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public Absence updateAbsence(@RequestBody Absence absence,@PathVariable int id){
        return iAbsenceService.updateAbsence(absence,id);
    }
    @DeleteMapping("delete/{id}")
    public void deleteAbsence(@PathVariable int id){
        iAbsenceService.deleteAbsence(id);
    }


    @GetMapping("/absence/{id}")
    public Absence getAbsenceById(@PathVariable int id){
        return iAbsenceService.getAbsenceById(id);
    }

}
