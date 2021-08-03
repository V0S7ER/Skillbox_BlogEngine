package main.controller;

import main.api.response.InitResponse;
import main.api.response.SettingsResponse;
import main.api.response.TagResponse;
import main.repository.SettingsRepository;
import main.repository.TagRepository;
import main.service.SettingsService;
import main.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    @Autowired
    private SettingsRepository settingsRepository;

    @Autowired
    private TagRepository tagRepository;

    private final InitResponse initResponse;

    public ApiGeneralController(InitResponse initResponse) {
        this.initResponse = initResponse;
    }

    @GetMapping("/init")
    private ResponseEntity<InitResponse> init() {
        return new ResponseEntity<>(initResponse, HttpStatus.OK);
    }

    @GetMapping("/settings")
    private ResponseEntity<SettingsResponse> settings() {
        return new ResponseEntity<>(SettingsService.getGlobalSettings(settingsRepository), HttpStatus.OK);
    }

    @GetMapping("/tag")
    private ResponseEntity<TagResponse> tag(String query) {
        if (query == null) {
            query = "";
        }
        return new ResponseEntity<>(TagService.getTagResponse(query, tagRepository), HttpStatus.OK);
    }

}
