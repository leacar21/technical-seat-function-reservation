package com.leacar21.technical.seat.function.reservation.converters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.leacar21.technical.seat.function.reservation.constants.BeanNames;

public abstract class AbstractConverter<D, E> {

    @Qualifier(BeanNames.STRICT_MODEL_MAPPER)
    @Autowired
    protected ModelMapper strictModelMapper;

    // ------------------

    protected abstract D commonToDTO(E source);

    protected abstract E commonToEntity(D source);

    // ------------------

    public D toDTO(E entity) {
        if (entity == null) {
            return null;
        }
        return this.commonToDTO(entity);
    }

    public E toEntity(D dto) {
        if (dto == null) {
            return null;
        }
        return this.commonToEntity(dto);
    }

    public List<D> toDTO(List<E> entities) {
        return (List<D>) this.mapToDTOAsCollection(entities, new ArrayList<D>());
    }

    public Set<D> toDTO(Set<E> entities) {
        return (Set<D>) this.mapToDTOAsCollection(entities, new LinkedHashSet<D>());
    }

    public List<E> toEntity(List<D> dtos) {
        return (List<E>) this.mapToEntityAsCollection(dtos, new ArrayList<E>());
    }

    public Set<E> toEntity(Set<D> dtos) {
        return (Set<E>) this.mapToEntityAsCollection(dtos, new LinkedHashSet<E>());
    }

    // ------------------

    private Collection<D> mapToDTOAsCollection(Collection<E> source, Collection<D> destination) {
        if (source == null) {
            return null;
        }
        source.stream().forEach(i -> destination.add(this.toDTO(i)));
        return destination;
    }

    private Collection<E> mapToEntityAsCollection(Collection<D> source, Collection<E> destination) {
        if (source == null) {
            return null;
        }
        source.stream().forEach(i -> destination.add(this.toEntity(i)));
        return destination;
    }
}