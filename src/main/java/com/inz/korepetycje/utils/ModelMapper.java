package com.inz.korepetycje.utils;

import com.inz.korepetycje.model.Advertisement;
import com.inz.korepetycje.model.User;
import com.inz.korepetycje.payload.AdvertisementResponse;
import com.inz.korepetycje.payload.UserSummary;

public class ModelMapper {

    public static AdvertisementResponse mapAdvertisementToAdvertisementResponse(Advertisement advertisement,
                                                                                User creator){
        AdvertisementResponse response = new AdvertisementResponse();
        response.setId(advertisement.getId());
        response.setDescription(advertisement.getDescription());
        response.setDurationInMinutes(advertisement.getDurationInMinutes());
        response.setPrice(advertisement.getPrice());
        response.setTitle(advertisement.getTitle());

        UserSummary userSummary = new UserSummary(creator.getId(),
            creator.getUsername(),
            creator.getFirstName(),
            creator.getLastName());
        response.setCreatedBy(userSummary);
        return response;
    }
}
