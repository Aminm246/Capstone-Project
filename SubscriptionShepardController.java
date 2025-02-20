package edu.metro.subscriptionshepard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SubscriptionShepardController {

    @Autowired
    private SubscriptionService subService;

    @RequestMapping(value={"","/","/dashboard"})
    public String dashboard(Model model){
        Subscription subscription = new Subscription();
        model.addAttribute("subscription", subscription);
        model.addAttribute("subscriptions", subService.retrieveAll());
        return "dashboard";
    }

    @PostMapping("/addSpending")
    public String addSpending(@ModelAttribute Subscription sub, Model model){
        subService.create(sub);
        model.addAttribute("dashboard", subService.retrieveAll());
        return "redirect:/dashboard";
    }

    @GetMapping("/retrieve/{id}")
    public String retrieveAll(@PathVariable int id, Model model){
        model.addAttribute("subscription", subService.retrieve(id));
        return "/subscription";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model){
        subService.delete(id);
        return "redirect:/dashboard";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Subscription sub, Model model){
        subService.update(sub);
        return "redirect:/dashboard";
    }

}

