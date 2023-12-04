package com.spring.keyvaluestore.Controller;

import com.spring.keyvaluestore.DAO.kvDAO;
import com.spring.keyvaluestore.model.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    kvDAO dao;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<KeyValue> kvList = dao.list();
        model.addAttribute("kvList", kvList);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewForm(Model model){
        KeyValue keyValue = new KeyValue();
        model.addAttribute("kvEntry");
        return "new_form";
    }

    @RequestMapping(value = "/save", method=RequestMethod.POST)
    public String save(@ModelAttribute("KeyValue") KeyValue keyValue){
        dao.save(keyValue);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name="id") String Id){
        ModelAndView mav = new ModelAndView("edit_form");
        KeyValue keyValue = dao.get(Id);
        mav.addObject("KeyValue", keyValue);
        return mav;
    }

    @RequestMapping("/update")
    public String update(@ModelAttribute("KeyValue") KeyValue keyValue){
        dao.update(keyValue);

        return  "redirect:/";

    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name="id") String Id){
        dao.delete(Id);
        return "redirect:/";
    }

}
