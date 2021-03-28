package ee.company.crm.application.web.profile;

import ee.company.crm.domain.service.profile.SectorDto;
import ee.company.crm.domain.service.profile.SectorService;
import ee.company.crm.domain.service.profile.ProfileDto;
import ee.company.crm.domain.service.profile.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProfileController {
    private ProfileService profileService;
    private SectorService sectorService;

    private final static String VIEW_NAME = "profile";
    private final static String MODEL_NAME = "profileModel";

    public ProfileController(ProfileService profileService, SectorService sectorService) {
        this.profileService = profileService;
        this.sectorService = sectorService;
    }
    @RequestMapping(value = {"", "/" ,"profile"})
    public ModelAndView profile() {
        return new ModelAndView(VIEW_NAME, MODEL_NAME, profileService.find()).addObject("globalMessage", "Welcome");
    }

    @RequestMapping(value = "add")
    public ModelAndView add() {
        return createModelView(new ProfileDto());
    }

    @RequestMapping(value="modify", method = RequestMethod.POST)
    public ModelAndView create(@Valid final ProfileDto profileDto, final BindingResult result, final RedirectAttributes redirect) {
        if (result.hasErrors()) {
            ModelAndView model = createModelView(profileDto);
            model.addObject("formErrors", result.getAllErrors());
            return model;
        }

        if (profileDto.getId() == null) {
            profileService.create(profileDto);
            redirect.addFlashAttribute("globalMessage", "Successfully created a new profile");
        } else {
            profileService.update(profileDto);
            redirect.addFlashAttribute("globalMessage", "Successfully updated the profile");
        }

        return new ModelAndView("redirect:/profile");
    }

    @RequestMapping(value = "profile/{id}")
    public ModelAndView view(@PathVariable Integer id) {
        Optional<ProfileDto> profile = profileService.findById(id);
        ProfileDto profileDto = profile.orElse(new ProfileDto());
        return createModelView(profileDto);
    }

    private ModelAndView createModelView(ProfileDto profileDto) {
        List<SectorDto> sectorDtoList = sectorService.findAllSectors();
        ModelAndView model = new ModelAndView();
        model.addObject("profileDto", profileDto);
        model.addObject("sectorList", sectorDtoList);
        model.setViewName(VIEW_NAME);
        return model;
    }
}
