package br.com.itech.icount_api.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperUtils {

    private static ModelMapper defaultModelMapper = modelMapper();

    public static ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    public static void map(Object source, Object destination) {
        defaultModelMapper.map(source, destination);
    }
}
