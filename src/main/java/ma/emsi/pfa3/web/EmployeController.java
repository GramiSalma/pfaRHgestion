package ma.emsi.pfa3.web;

import lombok.AllArgsConstructor;
import ma.emsi.pfa3.repositories.EmployeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class EmployeController {


private EmployeRepository employeRepository;
@GetMapping(path="/index")
public String employes(){
    return "employes";
}
}
