package com.inz.korepetycje.service;

import com.inz.korepetycje.exception.ResourceNotFoundException;
import com.inz.korepetycje.model.Opinion;
import com.inz.korepetycje.model.User;
import com.inz.korepetycje.payload.OpinionRequest;
import com.inz.korepetycje.payload.OpinionResponse;
import com.inz.korepetycje.repository.OpinionRepository;
import com.inz.korepetycje.repository.UserRepository;
import com.inz.korepetycje.utils.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OpinionService {
    private OpinionRepository opinionRepository;
    private UserRepository userRepository;

    public OpinionService(OpinionRepository opinionRepository, UserRepository userRepository) {
        this.opinionRepository = opinionRepository;
        this.userRepository = userRepository;
    }

    public List<OpinionResponse> getOpinionsOf(String username){
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        List<Opinion> opinions = opinionRepository.findAllByUser(user.getId());

        Map<Long, User> creatorMap = getOpinionCreatorMap(opinions);

        List<OpinionResponse> opinionResponses = opinions.stream()
            .map(opinion -> ModelMapper.mapOpinionToOpinionResponse(opinion, creatorMap.get(opinion.getCreatedBy())))
            .collect(Collectors.toList());

        return opinionResponses;
    }

    private Map<Long, User> getOpinionCreatorMap(List<Opinion> opinions) {

        List<Long> creatorIds = opinions.stream()
            .map(Opinion::getCreatedBy)
            .distinct()
            .collect(Collectors.toList());

        List<User> creators = userRepository.findByIdIn(creatorIds);
        Map<Long, User> creatorMap = creators.stream()
            .collect(Collectors.toMap(User::getId, Function.identity()));

        return creatorMap;
    }

    public Opinion createOpinion(OpinionRequest opinionRequest) {
        Opinion opinion = new Opinion();
        opinion.setRating(opinionRequest.getRating());
        opinion.setContent(opinionRequest.getContent());

        User user = userRepository.findByUsername(opinionRequest.getUserName())
            .orElseThrow(() -> new ResourceNotFoundException("User", "username", opinionRequest.getUserName()));

        opinion.setUser(user);

        return opinionRepository.save(opinion);
    }
}
