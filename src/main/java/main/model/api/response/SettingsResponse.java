package main.model.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SettingsResponse {
    @JsonProperty("MULTIUSER_MODE")
    private boolean multiuserMode;
    @JsonProperty("POST_PREMODERATION")
    private boolean postPremoderation;
    @JsonProperty("STATISTICS_IS_PUBLIC")
    private boolean statisticsIsPublic;

    public void setValue(int id, boolean value) {
        switch (id) {
            case 1:
                setMultiuserMode(value);
                break;
            case 2:
                setPostPremoderation(value);
                break;
            case 3:
                setStatisticsIsPublic(value);
                break;
        }
    }

}
