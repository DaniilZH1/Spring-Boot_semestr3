package MySpringBoot2Dbase.controller;

import MySpringBoot2Dbase.entity.StudyGroup;
import MySpringBoot2Dbase.service.StudyGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudyGroupPageController {
    private final StudyGroupService studyGroupService;

    public StudyGroupPageController(StudyGroupService studyGroupService) {
        this.studyGroupService = studyGroupService;
    }

    @GetMapping("/groups")
    public String listGroups(Model model) {
        model.addAttribute("groups", studyGroupService.findAll());
        return "list-groups";
    }

    @GetMapping("/addGroupForm")
    public String addGroupForm(Model model) {
        model.addAttribute("studyGroup", new StudyGroup());
        return "add-group-form";
    }

    @PostMapping("/saveGroup")
    public String saveGroup(@ModelAttribute("studyGroup") StudyGroup studyGroup) {
        studyGroupService.save(studyGroup);
        return "redirect:/groups";
    }

    @GetMapping("/showUpdateGroupForm")
    public String showUpdateForm(@RequestParam Integer groupId, Model model) {
        StudyGroup studyGroup = studyGroupService.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Study group not found: " + groupId));
        model.addAttribute("studyGroup", studyGroup);
        return "add-group-form";
    }

    @GetMapping("/deleteGroup")
    public String deleteGroup(@RequestParam Integer groupId) {
        studyGroupService.deleteById(groupId);
        return "redirect:/groups";
    }
}
