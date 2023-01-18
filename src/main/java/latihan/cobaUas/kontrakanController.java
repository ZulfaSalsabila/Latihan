/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan.cobaUas;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ROG
 */

@ResponseBody
@RestController
@CrossOrigin
public class kontrakanController {
    
    Kontrakan home = new Kontrakan();
    KontrakanJpaController rumah = new KontrakanJpaController();
    
    @GetMapping("/GET")
    public List<Kontrakan> getKontrakan() {
        List<Kontrakan> urutan = new ArrayList<>();
        
        urutan= rumah.findKontrakanEntities();
        
        return urutan;
    }
    
    @PostMapping("/POST")
    public String createKontrakan(HttpEntity<String> web) throws Exception{
        
        String feedback = "no action";
        
        String local = web.getBody();
        
        ObjectMapper mapper = new ObjectMapper();
        
        // Membuat objek baru untuk finaluas
        
        home = mapper.readValue(local, Kontrakan.class);
        rumah.create(home); // memanggil method create pada JPA controller
        
        feedback = "id = " + home.getId().toString() + " id berhasil ditambahkan";
        
        
        return feedback;
    }
    
    
    @PutMapping("/PUT")
    public String updateKontrakan(HttpEntity<String> web) throws Exception{
        
        String feedback = "no action";
        
        String local = web.getBody();
        
        ObjectMapper mapper = new ObjectMapper();
        
        // Membuat objek baru untuk finaluas
        
        home = mapper.readValue(local, Kontrakan.class);
        rumah.edit(home); // memanggil method create pada JPA controller
        
        feedback = "id = " + home.getId().toString() + " id berhasil diupdate";
        
        
        return feedback;
    }
    
    @DeleteMapping("/DELETE")
    public String deleteKontrakan(HttpEntity<String> web) throws Exception{
        
        String feedback = "no action";
        
        String local = web.getBody();
        
        ObjectMapper mapper = new ObjectMapper();
        
        // Membuat objek baru untuk finaluas
        
        home = mapper.readValue(local, Kontrakan.class);
        rumah.destroy(home.getId()); // memanggil method create pada JPA controller
        
        feedback = "id = " + home.getId().toString() + " id berhasil dihapus";
        
        
        return feedback;
    }
    
}
