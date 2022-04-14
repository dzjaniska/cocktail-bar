package com.scnsoft.cocktails.mapper;

import com.scnsoft.cocktails.dao.LabelRepository;
import com.scnsoft.cocktails.dto.LabelDTO;
import com.scnsoft.cocktails.entity.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LabelMapper {

    private final LabelRepository labelRepository;

    public Label toEntity(LabelDTO dto) {
        if(dto == null) {
            return null;
        }
        Label out;
        if(dto.getId() == null) {
            out = new Label();
        } else {
            out = labelRepository.getById(dto.getId());
        }
        out.setLabelEn(dto.getLabelEn());
        out.setLabelRu(dto.getLabelRu());
        return out;
    }
}
