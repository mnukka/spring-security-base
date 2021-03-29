package ee.company.crm.application.web.profile;

import ee.company.crm.domain.service.sector.SectorService;
import ee.company.crm.domain.service.user.profile.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private final ProfileService profileService;
    private final SectorService sectorService;

    private final static String PROFILE_VIEW = "profile";
    private final static String INDEX_VIEW = "index";
    private final static String MODEL = "profileModel";

    public ProfileController(ProfileService profileService, SectorService sectorService) {
        this.profileService = profileService;
        this.sectorService = sectorService;
    }

    @RequestMapping(value = {"", "/", "index"})
    public ModelAndView index() {
        return new ModelAndView(INDEX_VIEW, MODEL, fetchProfileOrCreateEmpty());
    }

    @RequestMapping(value = "profile/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid final @ModelAttribute(MODEL) ProfileDto profileDto, final BindingResult result, final RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return createViewModel(profileDto).addObject("formErrors", result.getAllErrors());
        }

        profileService.upsert(profileDto);
        redirect.addFlashAttribute("globalMessage", "Profile updated");

        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value = "profile/view")
    public ModelAndView view() {
        ProfileDto profileDto = fetchProfileOrCreateEmpty();
        return createViewModel(profileDto);
    }

    private ProfileDto fetchProfileOrCreateEmpty() {
        Optional<ProfileDto> profile = profileService.find();
        return profile.orElse(new ProfileDto());
    }

    private ModelAndView createViewModel(ProfileDto profileDto) {
        List<SectorDto> sectorDtoList = sectorService.findAllSectors();
        return new ModelAndView(PROFILE_VIEW, MODEL, profileDto).addObject("sectorList", sectorDtoList);
    }
}
