package com.glsid.web;

import com.glsid.Dao.OrdreRepository;
import com.glsid.Dao.SocieteRepository;
import com.glsid.Entities.Societe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by X-MART on 5/7/2017.
 */
@Controller
public class SocieteController {
    @Autowired
    private SocieteRepository societeRepository;

    @RequestMapping("/societe")
    private String index(Model model,
                         @RequestParam(name = "page",defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<Societe> societePage=societeRepository.findAll(new PageRequest(page,size));
        model.addAttribute("societePage",societePage.getContent());
        int[] pages=new int[societePage.getTotalPages()];
        model.addAttribute("pages",pages);
        return "societe";
    }

    @RequestMapping("/consulterSociete")
    private String consulterSociete(Model model,String nom,
                                    @RequestParam(name = "page",defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "5") int size) {

        try {
            model.addAttribute("nom", nom);
            Page<Societe> societePage=societeRepository.findSocietesByNomContains(nom,new PageRequest(page,size));
            model.addAttribute("societePage", societePage.getContent());
            int[] pages=new int[societePage.getTotalPages()];
            model.addAttribute("pages",pages);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "societe";
    }

    @RequestMapping(value = "/saveSociete", method = RequestMethod.POST)
    public String saveSociete(Model model, String code,
                                String nom) {
        try {
            System.out.println(code);
            System.out.println(nom);
            Societe societe= new Societe(code,nom);
            System.out.println(societe.getCode());
            societeRepository.save(societe);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            model.addAttribute("error",e);
            return "redirect:/societe";
        }
        return "redirect:/societe";

    }

}
