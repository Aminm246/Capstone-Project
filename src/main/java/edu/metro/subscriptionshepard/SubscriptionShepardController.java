package edu.metro.subscriptionshepard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class SubscriptionShepardController {

    @Autowired
    private SubscriptionService subService;

    @RequestMapping(value={"","/","/dashboard"})
    public String dashboard(Model model){
        Subscription subscription = new Subscription();
        List<Subscription> subscriptions = subService.retrieveAll();

        // Calculate total monthly spending for the dashboard summary
        // This converts all subscription prices to their monthly equivalent
        double totalMonthly = 0.0;
        for (Subscription sub : subscriptions) {
            double monthlyPrice = sub.getPrice();
            // Convert yearly subscriptions to monthly cost
            if ("Yearly".equals(sub.getPaymentFrequency())) {
                monthlyPrice = sub.getPrice() / 12;
            }
            // Convert quarterly subscriptions to monthly cost
            else if ("Quarterly".equals(sub.getPaymentFrequency())) {
                monthlyPrice = sub.getPrice() / 3;
            }
            // Convert weekly subscriptions to monthly cost (4.33 weeks average in a month)
            else if ("Weekly".equals(sub.getPaymentFrequency())) {
                monthlyPrice = sub.getPrice() * 4.33;
            }
            totalMonthly += monthlyPrice;
        }

        // Add all required attributes to the model
        model.addAttribute("subscription", subscription);
        model.addAttribute("subscriptions", subscriptions);
        // This totalMonthly value will be used in the dashboard to show the monthly summary
        model.addAttribute("totalMonthly", totalMonthly);
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
