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

    @GetMapping("/addSpending")
    public String showAddForm(Model model) {
        model.addAttribute("subscription", new Subscription());
        return "addSpending";
    }

    @PostMapping("/addSpending")
    public String addSpending(@ModelAttribute Subscription sub, Model model){
        subService.create(sub);
        model.addAttribute("subscriptions", subService.retrieveAll());
        return "redirect:/dashboard";
    }

    @GetMapping("/retrieve/{id}")
    public String retrieveAll(@PathVariable Long id, Model model){
        model.addAttribute("subscription", subService.retrieve(id).orElse(new Subscription()));
        return "/subscription";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        subService.delete(id);
        return "redirect:/dashboard";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Subscription sub, Model model){
        subService.update(sub);
        return "redirect:/dashboard";
    }
}
