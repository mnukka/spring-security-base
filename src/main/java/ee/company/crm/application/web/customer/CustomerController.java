package ee.company.crm.application.web.customer;

import ee.company.crm.domain.service.customer.SectorDto;
import ee.company.crm.domain.service.customer.CustomerDetailsService;
import ee.company.crm.domain.service.customer.CustomerDto;
import ee.company.crm.domain.service.customer.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CustomerController {
    private CustomerService customerService;
    private CustomerDetailsService customerDetailsService;

    public CustomerController(CustomerService customerService, CustomerDetailsService customerDetailsService) {
        this.customerService = customerService;
        this.customerDetailsService = customerDetailsService;
    }
    @RequestMapping(value = {"", "/" ,"customers"})
    public ModelAndView list() {
        Iterable<CustomerDto> customers = customerService.findAll();
        return new ModelAndView("list", "customers", customers);
    }

    @RequestMapping(value = "add")
    public ModelAndView add() {
        return createModelView(new CustomerDto());
    }

    @RequestMapping(value="modify", method = RequestMethod.POST)
    public ModelAndView create(@Valid final CustomerDto customerDto, final BindingResult result, final RedirectAttributes redirect) {
        if (result.hasErrors()) {
            ModelAndView model = createModelView(customerDto);
            model.addObject("formErrors", result.getAllErrors());
            return model;
        }

        if (customerDto.getId() == null) {
            customerService.createCustomer(customerDto);
            redirect.addFlashAttribute("globalMessage", "Successfully created a new customer");
        } else {
            customerService.updateCustomer(customerDto);
            redirect.addFlashAttribute("globalMessage", "Successfully updated the customer");
        }

        return new ModelAndView("redirect:/customers");
    }

    @RequestMapping(value = "customer/{id}")
    public ModelAndView view(@PathVariable Integer id) {
        Optional<CustomerDto> customerDtoOptional = customerService.getCustomerByCustomerId(id);
        CustomerDto customerDto = customerDtoOptional.orElse(new CustomerDto());
        return createModelView(customerDto);
    }

    private ModelAndView createModelView(CustomerDto customerDto) {
        List<SectorDto> sectorDtoList = customerDetailsService.findAllSectors();
        ModelAndView model = new ModelAndView();
        model.addObject("customerDto", customerDto);
        model.addObject("sectorList", sectorDtoList);
        model.setViewName("customer");
        return model;
    }
}
