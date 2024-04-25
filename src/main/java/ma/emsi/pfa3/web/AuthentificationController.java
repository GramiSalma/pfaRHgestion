package ma.emsi.pfa3.web;

import lombok.RequiredArgsConstructor;
import ma.emsi.pfa3.entities.Employe;
import ma.emsi.pfa3.services.IAbsenceService;
import ma.emsi.pfa3.services.IEmployeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("absences")
@RequiredArgsConstructor
public class AuthentificationController {


    private final IEmployeService iEmployeService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Employe user)
    {
        Employe existingUser = iEmployeService.findByEmail(user.getEmail());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword()))
        {   System.out.println("ok");
            return ResponseEntity.ok(existingUser);
        }
        else
        {     System.out.println("no");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}

