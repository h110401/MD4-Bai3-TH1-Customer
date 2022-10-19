package rikkei.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rikkei.academy.model.Customer;
import rikkei.academy.service.CustomerServiceIMPL;
import rikkei.academy.service.ICustomerService;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/customer"})
public class CustomerController {

    private final ICustomerService customerService = new CustomerServiceIMPL();

    @GetMapping
    public String showList(Model model) {
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers", customerList);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "create";
    }

    @PostMapping("/save")
    public String save(Customer customer) {
        customer.setId(customerService.findAll().get(customerService.findAll().size() - 1).getId() + 1);
        customerService.save(customer);
        return "redirect:/customer";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("customer", customerService.findById(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update(Customer customer) {
        customerService.update(customer.getId(), customer);
        return "redirect:/customer";
    }

    @GetMapping("/{id}/delete")
    public String delete(Model model, @PathVariable int id) {
        model.addAttribute("customer", customerService.findById(id));
        return "delete";
    }

    @PostMapping("/remove")
    public String remove(Customer customer) {
        customerService.remove(customer.getId());
        return "redirect:/customer";
    }

    @GetMapping("/{id}/view")
    public String view(Model model, @PathVariable int id) {
        model.addAttribute("customer", customerService.findById(id));
        return "view";
    }

}
