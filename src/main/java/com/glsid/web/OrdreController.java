package com.glsid.web;
import com.glsid.Dao.OrdreRepository;
import com.glsid.Dao.SocieteRepository;
import com.glsid.Entities.Achat;
import com.glsid.Entities.Ordre;
import com.glsid.Entities.Societe;
import com.glsid.Entities.Vente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by X-MART on 5/7/2017.
 */
@Controller
public class OrdreController {

    @Autowired
    private OrdreRepository ordreRepository ;
    @Autowired
    private SocieteRepository societeRepository;
    @RequestMapping("/ordres")
    private String index(){
        return "ordre";
    }
    @RequestMapping("/consulterOrdres")
    private String consulterOrdres(String code, Model model,
                                   @RequestParam(name = "page",defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "5") int size){
        System.out.println(code);
        model.addAttribute("code",code);
        try {
            Societe societe=societeRepository.findOne(code);
            model.addAttribute("societe",societe);
            Page<Ordre> ordrePage=ordreRepository.findOrdreBySociete(societe,new PageRequest(page,size));
            model.addAttribute("ordrePage",ordrePage.getContent());
            int[] pages=new int[ordrePage.getTotalPages()];
            model.addAttribute("pages",pages);
        }catch (Exception e){
            model.addAttribute("exception",e);
        }
        return "ordre";
    }

    @RequestMapping(value = "/saveOrdre",method = RequestMethod.POST)
    public String saveOperation(Model model,String code,
                                int NombreActions, double prix,String type_ordre){
        model.addAttribute("code",code);

        try {
            Societe societe=societeRepository.findOne(code);
            if(type_ordre.equals("vente")){
                Vente vente=new Vente(new Date(),NombreActions,prix,societe);
                ordreRepository.save(vente);
            }else {
                Achat achat=new Achat(new Date(),NombreActions,prix,societe);
                ordreRepository.save(achat);
            }
        }catch (Exception e){
            model.addAttribute("error",e);
            return "redirect:/consulterOrdres?code="+code+"&error="+e.getMessage();
        }
        return "redirect:/consulterOrdres?code="+code;
    }
}
