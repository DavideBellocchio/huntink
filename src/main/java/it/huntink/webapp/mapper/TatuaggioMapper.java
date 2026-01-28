package it.huntink.webapp.mapper;

import it.huntink.webapp.model.dto.validator.TatuaggioValidatorDto;
import it.huntink.webapp.model.dto.TatuaggioDto;
import it.huntink.webapp.model.entity.Tatuaggio;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TatuaggioMapper {

    TatuaggioDto toResponseDto(Tatuaggio t);
    Tatuaggio toEntity(TatuaggioValidatorDto t);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(TatuaggioDto tDto, @MappingTarget Tatuaggio t);
}
