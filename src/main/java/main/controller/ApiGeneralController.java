package main.controller;

import lombok.RequiredArgsConstructor;
import main.model.api.response.InitResponse;
import main.model.api.response.SettingsResponse;
import main.model.api.response.TagResponse;
import main.service.SettingsService;
import main.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiGeneralController {


    private final SettingsService settingsService;
    private final TagService tagService;
    private final InitResponse initResponse;

    @GetMapping("/init")
    private ResponseEntity<InitResponse> init() {
        return new ResponseEntity<>(initResponse, HttpStatus.OK);
    }

    @GetMapping("/settings")
    private ResponseEntity<SettingsResponse> settings() {
        return new ResponseEntity<>(settingsService.getGlobalSettings(), HttpStatus.OK);
    }

    @GetMapping("/tag")
    private ResponseEntity<TagResponse> tag(String query) {
        if (query == null) {
            query = "";
        }
        return new ResponseEntity<>(tagService.getTagResponse(query), HttpStatus.OK);
    }

}
