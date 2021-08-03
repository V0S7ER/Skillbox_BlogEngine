package main.service;

import main.api.response.SettingsResponse;
import main.model.GlobalSetting;
import main.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

@Service
public class SettingsService {

    @Autowired
    private static SettingsRepository settingsRepository;

    private SettingsService() {

    }

    public static SettingsResponse getGlobalSettings(SettingsRepository settingsRepository) {
        SettingsResponse settingsResponse = new SettingsResponse();
        Iterator<GlobalSetting> settingsIterator = settingsRepository.findAll().iterator();
        while (settingsIterator.hasNext()) {
            GlobalSetting globalSetting = settingsIterator.next();
            settingsResponse.setValue(globalSetting.getId(), globalSetting.isValue());
        }
        return settingsResponse;
    }
}
