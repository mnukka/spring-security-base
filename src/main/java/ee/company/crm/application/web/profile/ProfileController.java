package ee.company.crm.application.web.profile;

import ee.company.crm.domain.service.sector.SectorService;
import ee.company.crm.domain.service.user.profile.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    private final static String PROFILE_VIEW = "profile";
    private final static String INDEX_VIEW = "index";
    private final static String MODEL = "profileModel";

    public ProfileController(ProfileService profileService, SectorService sectorService) {
        this.profileService = profileService;
        this.sectorService = sectorService;
    }
    @RequestMapping(value = {"", "/" ,"index"})
    public ModelAndView profile() {
        return new ModelAndView(INDEX_VIEW, MODEL, fetchProfileOrCreateEmpty());
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

        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value = "profile/edit")
    public ModelAndView edit() {
        ProfileDto profile = fetchProfileOrCreateEmpty();
        return createModelView(profile);
    }

    private ProfileDto fetchProfileOrCreateEmpty() {
        Optional<ProfileDto> profile = profileService.find();
        return profile.orElse(new ProfileDto());
    }

    private ModelAndView createModelView(ProfileDto profileDto) {
        List<SectorDto> sectorDtoList = sectorService.findAllSectors();
        return new ModelAndView(PROFILE_VIEW, MODEL, profileDto).addObject("sectorList", sectorDtoList);
    }
}
