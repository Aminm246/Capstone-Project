package edu.metro.iteration2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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



}
