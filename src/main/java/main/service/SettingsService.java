package main.service;

import main.model.GlobalSetting;
import main.model.api.response.SettingsResponse;
import main.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    public SettingsResponse getGlobalSettings() {
        SettingsResponse settingsResponse = new SettingsResponse();
        for (GlobalSetting globalSetting : settingsRepository.findAll()) {
            settingsResponse.setValue(globalSetting.getId(), globalSetting.isValue());
        }
        return settingsResponse;
    }
}
