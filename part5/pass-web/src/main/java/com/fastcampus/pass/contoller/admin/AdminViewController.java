package com.fastcampus.pass.contoller.admin;

import com.fastcampus.pass.service.packaze.PackageService;
import com.fastcampus.pass.service.pass.BulkPassService;
import com.fastcampus.pass.service.statistics.StatisticsService;
import com.fastcampus.pass.service.user.UserGroupMappingService;
import com.fastcampus.pass.util.LocalDateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminViewController {
    private final BulkPassService bulkPassService;
    private final PackageService packageService;
    private final UserGroupMappingService userGroupMappingService;
    private final StatisticsService statisticsService;

    @GetMapping
    public String home(@RequestParam(value = "to", required = false) String toString, Model model) {
        LocalDateTime to = Strings.isEmpty(toString) ? LocalDateTime.now() : LocalDateTimeUtils.parseDate(toString);

        model.addAttribute("chartData", statisticsService.makeChartData(to));
        return "admin/index";
    }

    @GetMapping("/bulk-pass")
    public String registerBulkPass(Model model) {
        model.addAttribute("bulkPasses", bulkPassService.getAllBulkPasses());
        model.addAttribute("packages", packageService.getAllPackages());
        model.addAttribute("userGroupIds", userGroupMappingService.getAllUserGroupIds());
        model.addAttribute("request", new BulkPassRequest());

        return "admin/bulk-pass";
    }

    @PostMapping("/bulk-pass")
    public String addBulkPass(@ModelAttribute("request") BulkPassRequest request, Model model) {
        bulkPassService.addBulkPass(request);
        return "redirect:/admin/bulk-pass";
    }
}
